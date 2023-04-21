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
import wf.rh.domain.Requirents;
import wf.rh.domain.enumeration.Kind;
import wf.rh.repository.RequirentsRepository;
import wf.rh.service.dto.RequirentsDTO;
import wf.rh.service.mapper.RequirentsMapper;

/**
 * Integration tests for the {@link RequirentsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RequirentsResourceIT {

    private static final Long DEFAULT_ID_2_COURSE = 1L;
    private static final Long UPDATED_ID_2_COURSE = 2L;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_EXPIRATION_IN_MONTH = 1;
    private static final Integer UPDATED_EXPIRATION_IN_MONTH = 2;

    private static final Kind DEFAULT_KIND = Kind.CERTIFICATE;
    private static final Kind UPDATED_KIND = Kind.ONTHEJOB;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_1 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_1 = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_2 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_2 = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_3 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_3 = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_4 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_4 = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_5 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_5 = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_6 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_6 = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_7 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_7 = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_8 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_8 = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_9 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_9 = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_10 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_10 = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED = "AAAAAAAAAA";
    private static final String UPDATED_CREATED = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_EDITED = "AAAAAAAAAA";
    private static final String UPDATED_EDITED = "BBBBBBBBBB";

    private static final Instant DEFAULT_EDITED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EDITED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/requirents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RequirentsRepository requirentsRepository;

    @Autowired
    private RequirentsMapper requirentsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequirentsMockMvc;

    private Requirents requirents;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Requirents createEntity(EntityManager em) {
        Requirents requirents = new Requirents()
            .id2Course(DEFAULT_ID_2_COURSE)
            .code(DEFAULT_CODE)
            .expirationInMonth(DEFAULT_EXPIRATION_IN_MONTH)
            .kind(DEFAULT_KIND)
            .description(DEFAULT_DESCRIPTION)
            .extra1(DEFAULT_EXTRA_1)
            .extra2(DEFAULT_EXTRA_2)
            .extra3(DEFAULT_EXTRA_3)
            .extra4(DEFAULT_EXTRA_4)
            .extra5(DEFAULT_EXTRA_5)
            .extra6(DEFAULT_EXTRA_6)
            .extra7(DEFAULT_EXTRA_7)
            .extra8(DEFAULT_EXTRA_8)
            .extra9(DEFAULT_EXTRA_9)
            .extra10(DEFAULT_EXTRA_10)
            .created(DEFAULT_CREATED)
            .createdAt(DEFAULT_CREATED_AT)
            .edited(DEFAULT_EDITED)
            .editedAt(DEFAULT_EDITED_AT);
        return requirents;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Requirents createUpdatedEntity(EntityManager em) {
        Requirents requirents = new Requirents()
            .id2Course(UPDATED_ID_2_COURSE)
            .code(UPDATED_CODE)
            .expirationInMonth(UPDATED_EXPIRATION_IN_MONTH)
            .kind(UPDATED_KIND)
            .description(UPDATED_DESCRIPTION)
            .extra1(UPDATED_EXTRA_1)
            .extra2(UPDATED_EXTRA_2)
            .extra3(UPDATED_EXTRA_3)
            .extra4(UPDATED_EXTRA_4)
            .extra5(UPDATED_EXTRA_5)
            .extra6(UPDATED_EXTRA_6)
            .extra7(UPDATED_EXTRA_7)
            .extra8(UPDATED_EXTRA_8)
            .extra9(UPDATED_EXTRA_9)
            .extra10(UPDATED_EXTRA_10)
            .created(UPDATED_CREATED)
            .createdAt(UPDATED_CREATED_AT)
            .edited(UPDATED_EDITED)
            .editedAt(UPDATED_EDITED_AT);
        return requirents;
    }

    @BeforeEach
    public void initTest() {
        requirents = createEntity(em);
    }

    @Test
    @Transactional
    void createRequirents() throws Exception {
        int databaseSizeBeforeCreate = requirentsRepository.findAll().size();
        // Create the Requirents
        RequirentsDTO requirentsDTO = requirentsMapper.toDto(requirents);
        restRequirentsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requirentsDTO)))
            .andExpect(status().isCreated());

        // Validate the Requirents in the database
        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeCreate + 1);
        Requirents testRequirents = requirentsList.get(requirentsList.size() - 1);
        assertThat(testRequirents.getId2Course()).isEqualTo(DEFAULT_ID_2_COURSE);
        assertThat(testRequirents.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRequirents.getExpirationInMonth()).isEqualTo(DEFAULT_EXPIRATION_IN_MONTH);
        assertThat(testRequirents.getKind()).isEqualTo(DEFAULT_KIND);
        assertThat(testRequirents.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRequirents.getExtra1()).isEqualTo(DEFAULT_EXTRA_1);
        assertThat(testRequirents.getExtra2()).isEqualTo(DEFAULT_EXTRA_2);
        assertThat(testRequirents.getExtra3()).isEqualTo(DEFAULT_EXTRA_3);
        assertThat(testRequirents.getExtra4()).isEqualTo(DEFAULT_EXTRA_4);
        assertThat(testRequirents.getExtra5()).isEqualTo(DEFAULT_EXTRA_5);
        assertThat(testRequirents.getExtra6()).isEqualTo(DEFAULT_EXTRA_6);
        assertThat(testRequirents.getExtra7()).isEqualTo(DEFAULT_EXTRA_7);
        assertThat(testRequirents.getExtra8()).isEqualTo(DEFAULT_EXTRA_8);
        assertThat(testRequirents.getExtra9()).isEqualTo(DEFAULT_EXTRA_9);
        assertThat(testRequirents.getExtra10()).isEqualTo(DEFAULT_EXTRA_10);
        assertThat(testRequirents.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testRequirents.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testRequirents.getEdited()).isEqualTo(DEFAULT_EDITED);
        assertThat(testRequirents.getEditedAt()).isEqualTo(DEFAULT_EDITED_AT);
    }

    @Test
    @Transactional
    void createRequirentsWithExistingId() throws Exception {
        // Create the Requirents with an existing ID
        requirents.setId(1L);
        RequirentsDTO requirentsDTO = requirentsMapper.toDto(requirents);

        int databaseSizeBeforeCreate = requirentsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequirentsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requirentsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Requirents in the database
        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = requirentsRepository.findAll().size();
        // set the field null
        requirents.setCode(null);

        // Create the Requirents, which fails.
        RequirentsDTO requirentsDTO = requirentsMapper.toDto(requirents);

        restRequirentsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requirentsDTO)))
            .andExpect(status().isBadRequest());

        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllRequirents() throws Exception {
        // Initialize the database
        requirentsRepository.saveAndFlush(requirents);

        // Get all the requirentsList
        restRequirentsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requirents.getId().intValue())))
            .andExpect(jsonPath("$.[*].id2Course").value(hasItem(DEFAULT_ID_2_COURSE.intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].expirationInMonth").value(hasItem(DEFAULT_EXPIRATION_IN_MONTH)))
            .andExpect(jsonPath("$.[*].kind").value(hasItem(DEFAULT_KIND.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].extra1").value(hasItem(DEFAULT_EXTRA_1)))
            .andExpect(jsonPath("$.[*].extra2").value(hasItem(DEFAULT_EXTRA_2)))
            .andExpect(jsonPath("$.[*].extra3").value(hasItem(DEFAULT_EXTRA_3)))
            .andExpect(jsonPath("$.[*].extra4").value(hasItem(DEFAULT_EXTRA_4)))
            .andExpect(jsonPath("$.[*].extra5").value(hasItem(DEFAULT_EXTRA_5)))
            .andExpect(jsonPath("$.[*].extra6").value(hasItem(DEFAULT_EXTRA_6)))
            .andExpect(jsonPath("$.[*].extra7").value(hasItem(DEFAULT_EXTRA_7)))
            .andExpect(jsonPath("$.[*].extra8").value(hasItem(DEFAULT_EXTRA_8)))
            .andExpect(jsonPath("$.[*].extra9").value(hasItem(DEFAULT_EXTRA_9)))
            .andExpect(jsonPath("$.[*].extra10").value(hasItem(DEFAULT_EXTRA_10)))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].edited").value(hasItem(DEFAULT_EDITED)))
            .andExpect(jsonPath("$.[*].editedAt").value(hasItem(DEFAULT_EDITED_AT.toString())));
    }

    @Test
    @Transactional
    void getRequirents() throws Exception {
        // Initialize the database
        requirentsRepository.saveAndFlush(requirents);

        // Get the requirents
        restRequirentsMockMvc
            .perform(get(ENTITY_API_URL_ID, requirents.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requirents.getId().intValue()))
            .andExpect(jsonPath("$.id2Course").value(DEFAULT_ID_2_COURSE.intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.expirationInMonth").value(DEFAULT_EXPIRATION_IN_MONTH))
            .andExpect(jsonPath("$.kind").value(DEFAULT_KIND.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.extra1").value(DEFAULT_EXTRA_1))
            .andExpect(jsonPath("$.extra2").value(DEFAULT_EXTRA_2))
            .andExpect(jsonPath("$.extra3").value(DEFAULT_EXTRA_3))
            .andExpect(jsonPath("$.extra4").value(DEFAULT_EXTRA_4))
            .andExpect(jsonPath("$.extra5").value(DEFAULT_EXTRA_5))
            .andExpect(jsonPath("$.extra6").value(DEFAULT_EXTRA_6))
            .andExpect(jsonPath("$.extra7").value(DEFAULT_EXTRA_7))
            .andExpect(jsonPath("$.extra8").value(DEFAULT_EXTRA_8))
            .andExpect(jsonPath("$.extra9").value(DEFAULT_EXTRA_9))
            .andExpect(jsonPath("$.extra10").value(DEFAULT_EXTRA_10))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.edited").value(DEFAULT_EDITED))
            .andExpect(jsonPath("$.editedAt").value(DEFAULT_EDITED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRequirents() throws Exception {
        // Get the requirents
        restRequirentsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRequirents() throws Exception {
        // Initialize the database
        requirentsRepository.saveAndFlush(requirents);

        int databaseSizeBeforeUpdate = requirentsRepository.findAll().size();

        // Update the requirents
        Requirents updatedRequirents = requirentsRepository.findById(requirents.getId()).get();
        // Disconnect from session so that the updates on updatedRequirents are not directly saved in db
        em.detach(updatedRequirents);
        updatedRequirents
            .id2Course(UPDATED_ID_2_COURSE)
            .code(UPDATED_CODE)
            .expirationInMonth(UPDATED_EXPIRATION_IN_MONTH)
            .kind(UPDATED_KIND)
            .description(UPDATED_DESCRIPTION)
            .extra1(UPDATED_EXTRA_1)
            .extra2(UPDATED_EXTRA_2)
            .extra3(UPDATED_EXTRA_3)
            .extra4(UPDATED_EXTRA_4)
            .extra5(UPDATED_EXTRA_5)
            .extra6(UPDATED_EXTRA_6)
            .extra7(UPDATED_EXTRA_7)
            .extra8(UPDATED_EXTRA_8)
            .extra9(UPDATED_EXTRA_9)
            .extra10(UPDATED_EXTRA_10)
            .created(UPDATED_CREATED)
            .createdAt(UPDATED_CREATED_AT)
            .edited(UPDATED_EDITED)
            .editedAt(UPDATED_EDITED_AT);
        RequirentsDTO requirentsDTO = requirentsMapper.toDto(updatedRequirents);

        restRequirentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, requirentsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requirentsDTO))
            )
            .andExpect(status().isOk());

        // Validate the Requirents in the database
        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeUpdate);
        Requirents testRequirents = requirentsList.get(requirentsList.size() - 1);
        assertThat(testRequirents.getId2Course()).isEqualTo(UPDATED_ID_2_COURSE);
        assertThat(testRequirents.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRequirents.getExpirationInMonth()).isEqualTo(UPDATED_EXPIRATION_IN_MONTH);
        assertThat(testRequirents.getKind()).isEqualTo(UPDATED_KIND);
        assertThat(testRequirents.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRequirents.getExtra1()).isEqualTo(UPDATED_EXTRA_1);
        assertThat(testRequirents.getExtra2()).isEqualTo(UPDATED_EXTRA_2);
        assertThat(testRequirents.getExtra3()).isEqualTo(UPDATED_EXTRA_3);
        assertThat(testRequirents.getExtra4()).isEqualTo(UPDATED_EXTRA_4);
        assertThat(testRequirents.getExtra5()).isEqualTo(UPDATED_EXTRA_5);
        assertThat(testRequirents.getExtra6()).isEqualTo(UPDATED_EXTRA_6);
        assertThat(testRequirents.getExtra7()).isEqualTo(UPDATED_EXTRA_7);
        assertThat(testRequirents.getExtra8()).isEqualTo(UPDATED_EXTRA_8);
        assertThat(testRequirents.getExtra9()).isEqualTo(UPDATED_EXTRA_9);
        assertThat(testRequirents.getExtra10()).isEqualTo(UPDATED_EXTRA_10);
        assertThat(testRequirents.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testRequirents.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testRequirents.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testRequirents.getEditedAt()).isEqualTo(UPDATED_EDITED_AT);
    }

    @Test
    @Transactional
    void putNonExistingRequirents() throws Exception {
        int databaseSizeBeforeUpdate = requirentsRepository.findAll().size();
        requirents.setId(count.incrementAndGet());

        // Create the Requirents
        RequirentsDTO requirentsDTO = requirentsMapper.toDto(requirents);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequirentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, requirentsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requirentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Requirents in the database
        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRequirents() throws Exception {
        int databaseSizeBeforeUpdate = requirentsRepository.findAll().size();
        requirents.setId(count.incrementAndGet());

        // Create the Requirents
        RequirentsDTO requirentsDTO = requirentsMapper.toDto(requirents);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequirentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requirentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Requirents in the database
        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRequirents() throws Exception {
        int databaseSizeBeforeUpdate = requirentsRepository.findAll().size();
        requirents.setId(count.incrementAndGet());

        // Create the Requirents
        RequirentsDTO requirentsDTO = requirentsMapper.toDto(requirents);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequirentsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requirentsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Requirents in the database
        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRequirentsWithPatch() throws Exception {
        // Initialize the database
        requirentsRepository.saveAndFlush(requirents);

        int databaseSizeBeforeUpdate = requirentsRepository.findAll().size();

        // Update the requirents using partial update
        Requirents partialUpdatedRequirents = new Requirents();
        partialUpdatedRequirents.setId(requirents.getId());

        partialUpdatedRequirents
            .id2Course(UPDATED_ID_2_COURSE)
            .expirationInMonth(UPDATED_EXPIRATION_IN_MONTH)
            .kind(UPDATED_KIND)
            .description(UPDATED_DESCRIPTION)
            .extra1(UPDATED_EXTRA_1)
            .extra3(UPDATED_EXTRA_3)
            .extra6(UPDATED_EXTRA_6)
            .extra8(UPDATED_EXTRA_8)
            .createdAt(UPDATED_CREATED_AT)
            .edited(UPDATED_EDITED)
            .editedAt(UPDATED_EDITED_AT);

        restRequirentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequirents.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequirents))
            )
            .andExpect(status().isOk());

        // Validate the Requirents in the database
        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeUpdate);
        Requirents testRequirents = requirentsList.get(requirentsList.size() - 1);
        assertThat(testRequirents.getId2Course()).isEqualTo(UPDATED_ID_2_COURSE);
        assertThat(testRequirents.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRequirents.getExpirationInMonth()).isEqualTo(UPDATED_EXPIRATION_IN_MONTH);
        assertThat(testRequirents.getKind()).isEqualTo(UPDATED_KIND);
        assertThat(testRequirents.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRequirents.getExtra1()).isEqualTo(UPDATED_EXTRA_1);
        assertThat(testRequirents.getExtra2()).isEqualTo(DEFAULT_EXTRA_2);
        assertThat(testRequirents.getExtra3()).isEqualTo(UPDATED_EXTRA_3);
        assertThat(testRequirents.getExtra4()).isEqualTo(DEFAULT_EXTRA_4);
        assertThat(testRequirents.getExtra5()).isEqualTo(DEFAULT_EXTRA_5);
        assertThat(testRequirents.getExtra6()).isEqualTo(UPDATED_EXTRA_6);
        assertThat(testRequirents.getExtra7()).isEqualTo(DEFAULT_EXTRA_7);
        assertThat(testRequirents.getExtra8()).isEqualTo(UPDATED_EXTRA_8);
        assertThat(testRequirents.getExtra9()).isEqualTo(DEFAULT_EXTRA_9);
        assertThat(testRequirents.getExtra10()).isEqualTo(DEFAULT_EXTRA_10);
        assertThat(testRequirents.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testRequirents.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testRequirents.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testRequirents.getEditedAt()).isEqualTo(UPDATED_EDITED_AT);
    }

    @Test
    @Transactional
    void fullUpdateRequirentsWithPatch() throws Exception {
        // Initialize the database
        requirentsRepository.saveAndFlush(requirents);

        int databaseSizeBeforeUpdate = requirentsRepository.findAll().size();

        // Update the requirents using partial update
        Requirents partialUpdatedRequirents = new Requirents();
        partialUpdatedRequirents.setId(requirents.getId());

        partialUpdatedRequirents
            .id2Course(UPDATED_ID_2_COURSE)
            .code(UPDATED_CODE)
            .expirationInMonth(UPDATED_EXPIRATION_IN_MONTH)
            .kind(UPDATED_KIND)
            .description(UPDATED_DESCRIPTION)
            .extra1(UPDATED_EXTRA_1)
            .extra2(UPDATED_EXTRA_2)
            .extra3(UPDATED_EXTRA_3)
            .extra4(UPDATED_EXTRA_4)
            .extra5(UPDATED_EXTRA_5)
            .extra6(UPDATED_EXTRA_6)
            .extra7(UPDATED_EXTRA_7)
            .extra8(UPDATED_EXTRA_8)
            .extra9(UPDATED_EXTRA_9)
            .extra10(UPDATED_EXTRA_10)
            .created(UPDATED_CREATED)
            .createdAt(UPDATED_CREATED_AT)
            .edited(UPDATED_EDITED)
            .editedAt(UPDATED_EDITED_AT);

        restRequirentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequirents.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequirents))
            )
            .andExpect(status().isOk());

        // Validate the Requirents in the database
        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeUpdate);
        Requirents testRequirents = requirentsList.get(requirentsList.size() - 1);
        assertThat(testRequirents.getId2Course()).isEqualTo(UPDATED_ID_2_COURSE);
        assertThat(testRequirents.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRequirents.getExpirationInMonth()).isEqualTo(UPDATED_EXPIRATION_IN_MONTH);
        assertThat(testRequirents.getKind()).isEqualTo(UPDATED_KIND);
        assertThat(testRequirents.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRequirents.getExtra1()).isEqualTo(UPDATED_EXTRA_1);
        assertThat(testRequirents.getExtra2()).isEqualTo(UPDATED_EXTRA_2);
        assertThat(testRequirents.getExtra3()).isEqualTo(UPDATED_EXTRA_3);
        assertThat(testRequirents.getExtra4()).isEqualTo(UPDATED_EXTRA_4);
        assertThat(testRequirents.getExtra5()).isEqualTo(UPDATED_EXTRA_5);
        assertThat(testRequirents.getExtra6()).isEqualTo(UPDATED_EXTRA_6);
        assertThat(testRequirents.getExtra7()).isEqualTo(UPDATED_EXTRA_7);
        assertThat(testRequirents.getExtra8()).isEqualTo(UPDATED_EXTRA_8);
        assertThat(testRequirents.getExtra9()).isEqualTo(UPDATED_EXTRA_9);
        assertThat(testRequirents.getExtra10()).isEqualTo(UPDATED_EXTRA_10);
        assertThat(testRequirents.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testRequirents.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testRequirents.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testRequirents.getEditedAt()).isEqualTo(UPDATED_EDITED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingRequirents() throws Exception {
        int databaseSizeBeforeUpdate = requirentsRepository.findAll().size();
        requirents.setId(count.incrementAndGet());

        // Create the Requirents
        RequirentsDTO requirentsDTO = requirentsMapper.toDto(requirents);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequirentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, requirentsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requirentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Requirents in the database
        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRequirents() throws Exception {
        int databaseSizeBeforeUpdate = requirentsRepository.findAll().size();
        requirents.setId(count.incrementAndGet());

        // Create the Requirents
        RequirentsDTO requirentsDTO = requirentsMapper.toDto(requirents);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequirentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requirentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Requirents in the database
        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRequirents() throws Exception {
        int databaseSizeBeforeUpdate = requirentsRepository.findAll().size();
        requirents.setId(count.incrementAndGet());

        // Create the Requirents
        RequirentsDTO requirentsDTO = requirentsMapper.toDto(requirents);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequirentsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(requirentsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Requirents in the database
        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRequirents() throws Exception {
        // Initialize the database
        requirentsRepository.saveAndFlush(requirents);

        int databaseSizeBeforeDelete = requirentsRepository.findAll().size();

        // Delete the requirents
        restRequirentsMockMvc
            .perform(delete(ENTITY_API_URL_ID, requirents.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Requirents> requirentsList = requirentsRepository.findAll();
        assertThat(requirentsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
