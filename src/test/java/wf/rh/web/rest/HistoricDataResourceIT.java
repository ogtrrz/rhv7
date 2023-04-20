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
import wf.rh.domain.HistoricData;
import wf.rh.repository.HistoricDataRepository;
import wf.rh.service.dto.HistoricDataDTO;
import wf.rh.service.mapper.HistoricDataMapper;

/**
 * Integration tests for the {@link HistoricDataResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HistoricDataResourceIT {

    private static final Long DEFAULT_ID_2_EMPLOYEE = 1L;
    private static final Long UPDATED_ID_2_EMPLOYEE = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED = "AAAAAAAAAA";
    private static final String UPDATED_CREATED = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_EDITED = "AAAAAAAAAA";
    private static final String UPDATED_EDITED = "BBBBBBBBBB";

    private static final Instant DEFAULT_EDITED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EDITED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/historic-data";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HistoricDataRepository historicDataRepository;

    @Autowired
    private HistoricDataMapper historicDataMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHistoricDataMockMvc;

    private HistoricData historicData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistoricData createEntity(EntityManager em) {
        HistoricData historicData = new HistoricData()
            .id2Employee(DEFAULT_ID_2_EMPLOYEE)
            .name(DEFAULT_NAME)
            .link(DEFAULT_LINK)
            .created(DEFAULT_CREATED)
            .createdAt(DEFAULT_CREATED_AT)
            .edited(DEFAULT_EDITED)
            .editedAt(DEFAULT_EDITED_AT);
        return historicData;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistoricData createUpdatedEntity(EntityManager em) {
        HistoricData historicData = new HistoricData()
            .id2Employee(UPDATED_ID_2_EMPLOYEE)
            .name(UPDATED_NAME)
            .link(UPDATED_LINK)
            .created(UPDATED_CREATED)
            .createdAt(UPDATED_CREATED_AT)
            .edited(UPDATED_EDITED)
            .editedAt(UPDATED_EDITED_AT);
        return historicData;
    }

    @BeforeEach
    public void initTest() {
        historicData = createEntity(em);
    }

    @Test
    @Transactional
    void createHistoricData() throws Exception {
        int databaseSizeBeforeCreate = historicDataRepository.findAll().size();
        // Create the HistoricData
        HistoricDataDTO historicDataDTO = historicDataMapper.toDto(historicData);
        restHistoricDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicDataDTO))
            )
            .andExpect(status().isCreated());

        // Validate the HistoricData in the database
        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeCreate + 1);
        HistoricData testHistoricData = historicDataList.get(historicDataList.size() - 1);
        assertThat(testHistoricData.getId2Employee()).isEqualTo(DEFAULT_ID_2_EMPLOYEE);
        assertThat(testHistoricData.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHistoricData.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testHistoricData.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testHistoricData.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testHistoricData.getEdited()).isEqualTo(DEFAULT_EDITED);
        assertThat(testHistoricData.getEditedAt()).isEqualTo(DEFAULT_EDITED_AT);
    }

    @Test
    @Transactional
    void createHistoricDataWithExistingId() throws Exception {
        // Create the HistoricData with an existing ID
        historicData.setId(1L);
        HistoricDataDTO historicDataDTO = historicDataMapper.toDto(historicData);

        int databaseSizeBeforeCreate = historicDataRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistoricDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoricData in the database
        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicDataRepository.findAll().size();
        // set the field null
        historicData.setName(null);

        // Create the HistoricData, which fails.
        HistoricDataDTO historicDataDTO = historicDataMapper.toDto(historicData);

        restHistoricDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicDataDTO))
            )
            .andExpect(status().isBadRequest());

        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllHistoricData() throws Exception {
        // Initialize the database
        historicDataRepository.saveAndFlush(historicData);

        // Get all the historicDataList
        restHistoricDataMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historicData.getId().intValue())))
            .andExpect(jsonPath("$.[*].id2Employee").value(hasItem(DEFAULT_ID_2_EMPLOYEE.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].edited").value(hasItem(DEFAULT_EDITED)))
            .andExpect(jsonPath("$.[*].editedAt").value(hasItem(DEFAULT_EDITED_AT.toString())));
    }

    @Test
    @Transactional
    void getHistoricData() throws Exception {
        // Initialize the database
        historicDataRepository.saveAndFlush(historicData);

        // Get the historicData
        restHistoricDataMockMvc
            .perform(get(ENTITY_API_URL_ID, historicData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(historicData.getId().intValue()))
            .andExpect(jsonPath("$.id2Employee").value(DEFAULT_ID_2_EMPLOYEE.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.edited").value(DEFAULT_EDITED))
            .andExpect(jsonPath("$.editedAt").value(DEFAULT_EDITED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingHistoricData() throws Exception {
        // Get the historicData
        restHistoricDataMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHistoricData() throws Exception {
        // Initialize the database
        historicDataRepository.saveAndFlush(historicData);

        int databaseSizeBeforeUpdate = historicDataRepository.findAll().size();

        // Update the historicData
        HistoricData updatedHistoricData = historicDataRepository.findById(historicData.getId()).get();
        // Disconnect from session so that the updates on updatedHistoricData are not directly saved in db
        em.detach(updatedHistoricData);
        updatedHistoricData
            .id2Employee(UPDATED_ID_2_EMPLOYEE)
            .name(UPDATED_NAME)
            .link(UPDATED_LINK)
            .created(UPDATED_CREATED)
            .createdAt(UPDATED_CREATED_AT)
            .edited(UPDATED_EDITED)
            .editedAt(UPDATED_EDITED_AT);
        HistoricDataDTO historicDataDTO = historicDataMapper.toDto(updatedHistoricData);

        restHistoricDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, historicDataDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(historicDataDTO))
            )
            .andExpect(status().isOk());

        // Validate the HistoricData in the database
        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeUpdate);
        HistoricData testHistoricData = historicDataList.get(historicDataList.size() - 1);
        assertThat(testHistoricData.getId2Employee()).isEqualTo(UPDATED_ID_2_EMPLOYEE);
        assertThat(testHistoricData.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHistoricData.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testHistoricData.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testHistoricData.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testHistoricData.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testHistoricData.getEditedAt()).isEqualTo(UPDATED_EDITED_AT);
    }

    @Test
    @Transactional
    void putNonExistingHistoricData() throws Exception {
        int databaseSizeBeforeUpdate = historicDataRepository.findAll().size();
        historicData.setId(count.incrementAndGet());

        // Create the HistoricData
        HistoricDataDTO historicDataDTO = historicDataMapper.toDto(historicData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistoricDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, historicDataDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(historicDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoricData in the database
        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHistoricData() throws Exception {
        int databaseSizeBeforeUpdate = historicDataRepository.findAll().size();
        historicData.setId(count.incrementAndGet());

        // Create the HistoricData
        HistoricDataDTO historicDataDTO = historicDataMapper.toDto(historicData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistoricDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(historicDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoricData in the database
        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHistoricData() throws Exception {
        int databaseSizeBeforeUpdate = historicDataRepository.findAll().size();
        historicData.setId(count.incrementAndGet());

        // Create the HistoricData
        HistoricDataDTO historicDataDTO = historicDataMapper.toDto(historicData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistoricDataMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicDataDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HistoricData in the database
        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHistoricDataWithPatch() throws Exception {
        // Initialize the database
        historicDataRepository.saveAndFlush(historicData);

        int databaseSizeBeforeUpdate = historicDataRepository.findAll().size();

        // Update the historicData using partial update
        HistoricData partialUpdatedHistoricData = new HistoricData();
        partialUpdatedHistoricData.setId(historicData.getId());

        partialUpdatedHistoricData.link(UPDATED_LINK).created(UPDATED_CREATED);

        restHistoricDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHistoricData.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHistoricData))
            )
            .andExpect(status().isOk());

        // Validate the HistoricData in the database
        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeUpdate);
        HistoricData testHistoricData = historicDataList.get(historicDataList.size() - 1);
        assertThat(testHistoricData.getId2Employee()).isEqualTo(DEFAULT_ID_2_EMPLOYEE);
        assertThat(testHistoricData.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHistoricData.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testHistoricData.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testHistoricData.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testHistoricData.getEdited()).isEqualTo(DEFAULT_EDITED);
        assertThat(testHistoricData.getEditedAt()).isEqualTo(DEFAULT_EDITED_AT);
    }

    @Test
    @Transactional
    void fullUpdateHistoricDataWithPatch() throws Exception {
        // Initialize the database
        historicDataRepository.saveAndFlush(historicData);

        int databaseSizeBeforeUpdate = historicDataRepository.findAll().size();

        // Update the historicData using partial update
        HistoricData partialUpdatedHistoricData = new HistoricData();
        partialUpdatedHistoricData.setId(historicData.getId());

        partialUpdatedHistoricData
            .id2Employee(UPDATED_ID_2_EMPLOYEE)
            .name(UPDATED_NAME)
            .link(UPDATED_LINK)
            .created(UPDATED_CREATED)
            .createdAt(UPDATED_CREATED_AT)
            .edited(UPDATED_EDITED)
            .editedAt(UPDATED_EDITED_AT);

        restHistoricDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHistoricData.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHistoricData))
            )
            .andExpect(status().isOk());

        // Validate the HistoricData in the database
        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeUpdate);
        HistoricData testHistoricData = historicDataList.get(historicDataList.size() - 1);
        assertThat(testHistoricData.getId2Employee()).isEqualTo(UPDATED_ID_2_EMPLOYEE);
        assertThat(testHistoricData.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHistoricData.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testHistoricData.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testHistoricData.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testHistoricData.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testHistoricData.getEditedAt()).isEqualTo(UPDATED_EDITED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingHistoricData() throws Exception {
        int databaseSizeBeforeUpdate = historicDataRepository.findAll().size();
        historicData.setId(count.incrementAndGet());

        // Create the HistoricData
        HistoricDataDTO historicDataDTO = historicDataMapper.toDto(historicData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistoricDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, historicDataDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(historicDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoricData in the database
        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHistoricData() throws Exception {
        int databaseSizeBeforeUpdate = historicDataRepository.findAll().size();
        historicData.setId(count.incrementAndGet());

        // Create the HistoricData
        HistoricDataDTO historicDataDTO = historicDataMapper.toDto(historicData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistoricDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(historicDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoricData in the database
        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHistoricData() throws Exception {
        int databaseSizeBeforeUpdate = historicDataRepository.findAll().size();
        historicData.setId(count.incrementAndGet());

        // Create the HistoricData
        HistoricDataDTO historicDataDTO = historicDataMapper.toDto(historicData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistoricDataMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(historicDataDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HistoricData in the database
        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHistoricData() throws Exception {
        // Initialize the database
        historicDataRepository.saveAndFlush(historicData);

        int databaseSizeBeforeDelete = historicDataRepository.findAll().size();

        // Delete the historicData
        restHistoricDataMockMvc
            .perform(delete(ENTITY_API_URL_ID, historicData.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HistoricData> historicDataList = historicDataRepository.findAll();
        assertThat(historicDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
