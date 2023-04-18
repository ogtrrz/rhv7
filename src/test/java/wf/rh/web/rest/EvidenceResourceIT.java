package wf.rh.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import wf.rh.IntegrationTest;
import wf.rh.domain.Evidence;
import wf.rh.repository.EvidenceRepository;
import wf.rh.service.dto.EvidenceDTO;
import wf.rh.service.mapper.EvidenceMapper;

/**
 * Integration tests for the {@link EvidenceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EvidenceResourceIT {

    private static final Long DEFAULT_ID_2_TRINING = 1L;
    private static final Long UPDATED_ID_2_TRINING = 2L;

    private static final Long DEFAULT_ID_2_REQUIRENTS = 1L;
    private static final Long UPDATED_ID_2_REQUIRENTS = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_EXPIRATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPIRATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/evidences";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvidenceRepository evidenceRepository;

    @Autowired
    private EvidenceMapper evidenceMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEvidenceMockMvc;

    private Evidence evidence;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Evidence createEntity(EntityManager em) {
        Evidence evidence = new Evidence()
            .id2Trining(DEFAULT_ID_2_TRINING)
            .id2Requirents(DEFAULT_ID_2_REQUIRENTS)
            .description(DEFAULT_DESCRIPTION)
            .expiration(DEFAULT_EXPIRATION)
            .link(DEFAULT_LINK);
        return evidence;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Evidence createUpdatedEntity(EntityManager em) {
        Evidence evidence = new Evidence()
            .id2Trining(UPDATED_ID_2_TRINING)
            .id2Requirents(UPDATED_ID_2_REQUIRENTS)
            .description(UPDATED_DESCRIPTION)
            .expiration(UPDATED_EXPIRATION)
            .link(UPDATED_LINK);
        return evidence;
    }

    @BeforeEach
    public void initTest() {
        evidence = createEntity(em);
    }

    @Test
    @Transactional
    void createEvidence() throws Exception {
        int databaseSizeBeforeCreate = evidenceRepository.findAll().size();
        // Create the Evidence
        EvidenceDTO evidenceDTO = evidenceMapper.toDto(evidence);
        restEvidenceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(evidenceDTO)))
            .andExpect(status().isCreated());

        // Validate the Evidence in the database
        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeCreate + 1);
        Evidence testEvidence = evidenceList.get(evidenceList.size() - 1);
        assertThat(testEvidence.getId2Trining()).isEqualTo(DEFAULT_ID_2_TRINING);
        assertThat(testEvidence.getId2Requirents()).isEqualTo(DEFAULT_ID_2_REQUIRENTS);
        assertThat(testEvidence.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testEvidence.getExpiration()).isEqualTo(DEFAULT_EXPIRATION);
        assertThat(testEvidence.getLink()).isEqualTo(DEFAULT_LINK);
    }

    @Test
    @Transactional
    void createEvidenceWithExistingId() throws Exception {
        // Create the Evidence with an existing ID
        evidence.setId(1L);
        EvidenceDTO evidenceDTO = evidenceMapper.toDto(evidence);

        int databaseSizeBeforeCreate = evidenceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEvidenceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(evidenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Evidence in the database
        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = evidenceRepository.findAll().size();
        // set the field null
        evidence.setDescription(null);

        // Create the Evidence, which fails.
        EvidenceDTO evidenceDTO = evidenceMapper.toDto(evidence);

        restEvidenceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(evidenceDTO)))
            .andExpect(status().isBadRequest());

        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllEvidences() throws Exception {
        // Initialize the database
        evidenceRepository.saveAndFlush(evidence);

        // Get all the evidenceList
        restEvidenceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evidence.getId().intValue())))
            .andExpect(jsonPath("$.[*].id2Trining").value(hasItem(DEFAULT_ID_2_TRINING.intValue())))
            .andExpect(jsonPath("$.[*].id2Requirents").value(hasItem(DEFAULT_ID_2_REQUIRENTS.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].expiration").value(hasItem(DEFAULT_EXPIRATION.toString())))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)));
    }

    @Test
    @Transactional
    void getEvidence() throws Exception {
        // Initialize the database
        evidenceRepository.saveAndFlush(evidence);

        // Get the evidence
        restEvidenceMockMvc
            .perform(get(ENTITY_API_URL_ID, evidence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evidence.getId().intValue()))
            .andExpect(jsonPath("$.id2Trining").value(DEFAULT_ID_2_TRINING.intValue()))
            .andExpect(jsonPath("$.id2Requirents").value(DEFAULT_ID_2_REQUIRENTS.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.expiration").value(DEFAULT_EXPIRATION.toString()))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK));
    }

    @Test
    @Transactional
    void getNonExistingEvidence() throws Exception {
        // Get the evidence
        restEvidenceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEvidence() throws Exception {
        // Initialize the database
        evidenceRepository.saveAndFlush(evidence);

        int databaseSizeBeforeUpdate = evidenceRepository.findAll().size();

        // Update the evidence
        Evidence updatedEvidence = evidenceRepository.findById(evidence.getId()).get();
        // Disconnect from session so that the updates on updatedEvidence are not directly saved in db
        em.detach(updatedEvidence);
        updatedEvidence
            .id2Trining(UPDATED_ID_2_TRINING)
            .id2Requirents(UPDATED_ID_2_REQUIRENTS)
            .description(UPDATED_DESCRIPTION)
            .expiration(UPDATED_EXPIRATION)
            .link(UPDATED_LINK);
        EvidenceDTO evidenceDTO = evidenceMapper.toDto(updatedEvidence);

        restEvidenceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, evidenceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(evidenceDTO))
            )
            .andExpect(status().isOk());

        // Validate the Evidence in the database
        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeUpdate);
        Evidence testEvidence = evidenceList.get(evidenceList.size() - 1);
        assertThat(testEvidence.getId2Trining()).isEqualTo(UPDATED_ID_2_TRINING);
        assertThat(testEvidence.getId2Requirents()).isEqualTo(UPDATED_ID_2_REQUIRENTS);
        assertThat(testEvidence.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testEvidence.getExpiration()).isEqualTo(UPDATED_EXPIRATION);
        assertThat(testEvidence.getLink()).isEqualTo(UPDATED_LINK);
    }

    @Test
    @Transactional
    void putNonExistingEvidence() throws Exception {
        int databaseSizeBeforeUpdate = evidenceRepository.findAll().size();
        evidence.setId(count.incrementAndGet());

        // Create the Evidence
        EvidenceDTO evidenceDTO = evidenceMapper.toDto(evidence);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEvidenceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, evidenceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(evidenceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Evidence in the database
        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEvidence() throws Exception {
        int databaseSizeBeforeUpdate = evidenceRepository.findAll().size();
        evidence.setId(count.incrementAndGet());

        // Create the Evidence
        EvidenceDTO evidenceDTO = evidenceMapper.toDto(evidence);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvidenceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(evidenceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Evidence in the database
        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEvidence() throws Exception {
        int databaseSizeBeforeUpdate = evidenceRepository.findAll().size();
        evidence.setId(count.incrementAndGet());

        // Create the Evidence
        EvidenceDTO evidenceDTO = evidenceMapper.toDto(evidence);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvidenceMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(evidenceDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Evidence in the database
        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEvidenceWithPatch() throws Exception {
        // Initialize the database
        evidenceRepository.saveAndFlush(evidence);

        int databaseSizeBeforeUpdate = evidenceRepository.findAll().size();

        // Update the evidence using partial update
        Evidence partialUpdatedEvidence = new Evidence();
        partialUpdatedEvidence.setId(evidence.getId());

        partialUpdatedEvidence.id2Requirents(UPDATED_ID_2_REQUIRENTS).description(UPDATED_DESCRIPTION);

        restEvidenceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEvidence.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEvidence))
            )
            .andExpect(status().isOk());

        // Validate the Evidence in the database
        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeUpdate);
        Evidence testEvidence = evidenceList.get(evidenceList.size() - 1);
        assertThat(testEvidence.getId2Trining()).isEqualTo(DEFAULT_ID_2_TRINING);
        assertThat(testEvidence.getId2Requirents()).isEqualTo(UPDATED_ID_2_REQUIRENTS);
        assertThat(testEvidence.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testEvidence.getExpiration()).isEqualTo(DEFAULT_EXPIRATION);
        assertThat(testEvidence.getLink()).isEqualTo(DEFAULT_LINK);
    }

    @Test
    @Transactional
    void fullUpdateEvidenceWithPatch() throws Exception {
        // Initialize the database
        evidenceRepository.saveAndFlush(evidence);

        int databaseSizeBeforeUpdate = evidenceRepository.findAll().size();

        // Update the evidence using partial update
        Evidence partialUpdatedEvidence = new Evidence();
        partialUpdatedEvidence.setId(evidence.getId());

        partialUpdatedEvidence
            .id2Trining(UPDATED_ID_2_TRINING)
            .id2Requirents(UPDATED_ID_2_REQUIRENTS)
            .description(UPDATED_DESCRIPTION)
            .expiration(UPDATED_EXPIRATION)
            .link(UPDATED_LINK);

        restEvidenceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEvidence.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEvidence))
            )
            .andExpect(status().isOk());

        // Validate the Evidence in the database
        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeUpdate);
        Evidence testEvidence = evidenceList.get(evidenceList.size() - 1);
        assertThat(testEvidence.getId2Trining()).isEqualTo(UPDATED_ID_2_TRINING);
        assertThat(testEvidence.getId2Requirents()).isEqualTo(UPDATED_ID_2_REQUIRENTS);
        assertThat(testEvidence.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testEvidence.getExpiration()).isEqualTo(UPDATED_EXPIRATION);
        assertThat(testEvidence.getLink()).isEqualTo(UPDATED_LINK);
    }

    @Test
    @Transactional
    void patchNonExistingEvidence() throws Exception {
        int databaseSizeBeforeUpdate = evidenceRepository.findAll().size();
        evidence.setId(count.incrementAndGet());

        // Create the Evidence
        EvidenceDTO evidenceDTO = evidenceMapper.toDto(evidence);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEvidenceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, evidenceDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(evidenceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Evidence in the database
        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEvidence() throws Exception {
        int databaseSizeBeforeUpdate = evidenceRepository.findAll().size();
        evidence.setId(count.incrementAndGet());

        // Create the Evidence
        EvidenceDTO evidenceDTO = evidenceMapper.toDto(evidence);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvidenceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(evidenceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Evidence in the database
        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEvidence() throws Exception {
        int databaseSizeBeforeUpdate = evidenceRepository.findAll().size();
        evidence.setId(count.incrementAndGet());

        // Create the Evidence
        EvidenceDTO evidenceDTO = evidenceMapper.toDto(evidence);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvidenceMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(evidenceDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Evidence in the database
        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEvidence() throws Exception {
        // Initialize the database
        evidenceRepository.saveAndFlush(evidence);

        int databaseSizeBeforeDelete = evidenceRepository.findAll().size();

        // Delete the evidence
        restEvidenceMockMvc
            .perform(delete(ENTITY_API_URL_ID, evidence.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Evidence> evidenceList = evidenceRepository.findAll();
        assertThat(evidenceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
