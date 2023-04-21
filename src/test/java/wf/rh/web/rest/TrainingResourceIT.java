package wf.rh.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import wf.rh.IntegrationTest;
import wf.rh.domain.Training;
import wf.rh.repository.TrainingRepository;
import wf.rh.service.TrainingService;
import wf.rh.service.dto.TrainingDTO;
import wf.rh.service.mapper.TrainingMapper;

/**
 * Integration tests for the {@link TrainingResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class TrainingResourceIT {

    private static final Long DEFAULT_ID_2_COURSE = 1L;
    private static final Long UPDATED_ID_2_COURSE = 2L;

    private static final Long DEFAULT_ID_2_EMPLOYEE = 1L;
    private static final Long UPDATED_ID_2_EMPLOYEE = 2L;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_EXPIRY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPIRY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

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

    private static final String ENTITY_API_URL = "/api/trainings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TrainingRepository trainingRepository;

    @Mock
    private TrainingRepository trainingRepositoryMock;

    @Autowired
    private TrainingMapper trainingMapper;

    @Mock
    private TrainingService trainingServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTrainingMockMvc;

    private Training training;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Training createEntity(EntityManager em) {
        Training training = new Training()
            .id2Course(DEFAULT_ID_2_COURSE)
            .id2Employee(DEFAULT_ID_2_EMPLOYEE)
            .code(DEFAULT_CODE)
            .date(DEFAULT_DATE)
            .expiry(DEFAULT_EXPIRY)
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
        return training;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Training createUpdatedEntity(EntityManager em) {
        Training training = new Training()
            .id2Course(UPDATED_ID_2_COURSE)
            .id2Employee(UPDATED_ID_2_EMPLOYEE)
            .code(UPDATED_CODE)
            .date(UPDATED_DATE)
            .expiry(UPDATED_EXPIRY)
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
        return training;
    }

    @BeforeEach
    public void initTest() {
        training = createEntity(em);
    }

    @Test
    @Transactional
    void createTraining() throws Exception {
        int databaseSizeBeforeCreate = trainingRepository.findAll().size();
        // Create the Training
        TrainingDTO trainingDTO = trainingMapper.toDto(training);
        restTrainingMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(trainingDTO)))
            .andExpect(status().isCreated());

        // Validate the Training in the database
        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeCreate + 1);
        Training testTraining = trainingList.get(trainingList.size() - 1);
        assertThat(testTraining.getId2Course()).isEqualTo(DEFAULT_ID_2_COURSE);
        assertThat(testTraining.getId2Employee()).isEqualTo(DEFAULT_ID_2_EMPLOYEE);
        assertThat(testTraining.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTraining.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testTraining.getExpiry()).isEqualTo(DEFAULT_EXPIRY);
        assertThat(testTraining.getExtra1()).isEqualTo(DEFAULT_EXTRA_1);
        assertThat(testTraining.getExtra2()).isEqualTo(DEFAULT_EXTRA_2);
        assertThat(testTraining.getExtra3()).isEqualTo(DEFAULT_EXTRA_3);
        assertThat(testTraining.getExtra4()).isEqualTo(DEFAULT_EXTRA_4);
        assertThat(testTraining.getExtra5()).isEqualTo(DEFAULT_EXTRA_5);
        assertThat(testTraining.getExtra6()).isEqualTo(DEFAULT_EXTRA_6);
        assertThat(testTraining.getExtra7()).isEqualTo(DEFAULT_EXTRA_7);
        assertThat(testTraining.getExtra8()).isEqualTo(DEFAULT_EXTRA_8);
        assertThat(testTraining.getExtra9()).isEqualTo(DEFAULT_EXTRA_9);
        assertThat(testTraining.getExtra10()).isEqualTo(DEFAULT_EXTRA_10);
        assertThat(testTraining.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testTraining.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testTraining.getEdited()).isEqualTo(DEFAULT_EDITED);
        assertThat(testTraining.getEditedAt()).isEqualTo(DEFAULT_EDITED_AT);
    }

    @Test
    @Transactional
    void createTrainingWithExistingId() throws Exception {
        // Create the Training with an existing ID
        training.setId(1L);
        TrainingDTO trainingDTO = trainingMapper.toDto(training);

        int databaseSizeBeforeCreate = trainingRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrainingMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(trainingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Training in the database
        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = trainingRepository.findAll().size();
        // set the field null
        training.setCode(null);

        // Create the Training, which fails.
        TrainingDTO trainingDTO = trainingMapper.toDto(training);

        restTrainingMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(trainingDTO)))
            .andExpect(status().isBadRequest());

        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTrainings() throws Exception {
        // Initialize the database
        trainingRepository.saveAndFlush(training);

        // Get all the trainingList
        restTrainingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(training.getId().intValue())))
            .andExpect(jsonPath("$.[*].id2Course").value(hasItem(DEFAULT_ID_2_COURSE.intValue())))
            .andExpect(jsonPath("$.[*].id2Employee").value(hasItem(DEFAULT_ID_2_EMPLOYEE.intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].expiry").value(hasItem(DEFAULT_EXPIRY.toString())))
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

    @SuppressWarnings({ "unchecked" })
    void getAllTrainingsWithEagerRelationshipsIsEnabled() throws Exception {
        when(trainingServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTrainingMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(trainingServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTrainingsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(trainingServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTrainingMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(trainingRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getTraining() throws Exception {
        // Initialize the database
        trainingRepository.saveAndFlush(training);

        // Get the training
        restTrainingMockMvc
            .perform(get(ENTITY_API_URL_ID, training.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(training.getId().intValue()))
            .andExpect(jsonPath("$.id2Course").value(DEFAULT_ID_2_COURSE.intValue()))
            .andExpect(jsonPath("$.id2Employee").value(DEFAULT_ID_2_EMPLOYEE.intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.expiry").value(DEFAULT_EXPIRY.toString()))
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
    void getNonExistingTraining() throws Exception {
        // Get the training
        restTrainingMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTraining() throws Exception {
        // Initialize the database
        trainingRepository.saveAndFlush(training);

        int databaseSizeBeforeUpdate = trainingRepository.findAll().size();

        // Update the training
        Training updatedTraining = trainingRepository.findById(training.getId()).get();
        // Disconnect from session so that the updates on updatedTraining are not directly saved in db
        em.detach(updatedTraining);
        updatedTraining
            .id2Course(UPDATED_ID_2_COURSE)
            .id2Employee(UPDATED_ID_2_EMPLOYEE)
            .code(UPDATED_CODE)
            .date(UPDATED_DATE)
            .expiry(UPDATED_EXPIRY)
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
        TrainingDTO trainingDTO = trainingMapper.toDto(updatedTraining);

        restTrainingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, trainingDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(trainingDTO))
            )
            .andExpect(status().isOk());

        // Validate the Training in the database
        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeUpdate);
        Training testTraining = trainingList.get(trainingList.size() - 1);
        assertThat(testTraining.getId2Course()).isEqualTo(UPDATED_ID_2_COURSE);
        assertThat(testTraining.getId2Employee()).isEqualTo(UPDATED_ID_2_EMPLOYEE);
        assertThat(testTraining.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTraining.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testTraining.getExpiry()).isEqualTo(UPDATED_EXPIRY);
        assertThat(testTraining.getExtra1()).isEqualTo(UPDATED_EXTRA_1);
        assertThat(testTraining.getExtra2()).isEqualTo(UPDATED_EXTRA_2);
        assertThat(testTraining.getExtra3()).isEqualTo(UPDATED_EXTRA_3);
        assertThat(testTraining.getExtra4()).isEqualTo(UPDATED_EXTRA_4);
        assertThat(testTraining.getExtra5()).isEqualTo(UPDATED_EXTRA_5);
        assertThat(testTraining.getExtra6()).isEqualTo(UPDATED_EXTRA_6);
        assertThat(testTraining.getExtra7()).isEqualTo(UPDATED_EXTRA_7);
        assertThat(testTraining.getExtra8()).isEqualTo(UPDATED_EXTRA_8);
        assertThat(testTraining.getExtra9()).isEqualTo(UPDATED_EXTRA_9);
        assertThat(testTraining.getExtra10()).isEqualTo(UPDATED_EXTRA_10);
        assertThat(testTraining.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testTraining.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testTraining.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testTraining.getEditedAt()).isEqualTo(UPDATED_EDITED_AT);
    }

    @Test
    @Transactional
    void putNonExistingTraining() throws Exception {
        int databaseSizeBeforeUpdate = trainingRepository.findAll().size();
        training.setId(count.incrementAndGet());

        // Create the Training
        TrainingDTO trainingDTO = trainingMapper.toDto(training);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrainingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, trainingDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(trainingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Training in the database
        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTraining() throws Exception {
        int databaseSizeBeforeUpdate = trainingRepository.findAll().size();
        training.setId(count.incrementAndGet());

        // Create the Training
        TrainingDTO trainingDTO = trainingMapper.toDto(training);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrainingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(trainingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Training in the database
        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTraining() throws Exception {
        int databaseSizeBeforeUpdate = trainingRepository.findAll().size();
        training.setId(count.incrementAndGet());

        // Create the Training
        TrainingDTO trainingDTO = trainingMapper.toDto(training);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrainingMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(trainingDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Training in the database
        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTrainingWithPatch() throws Exception {
        // Initialize the database
        trainingRepository.saveAndFlush(training);

        int databaseSizeBeforeUpdate = trainingRepository.findAll().size();

        // Update the training using partial update
        Training partialUpdatedTraining = new Training();
        partialUpdatedTraining.setId(training.getId());

        partialUpdatedTraining
            .id2Course(UPDATED_ID_2_COURSE)
            .id2Employee(UPDATED_ID_2_EMPLOYEE)
            .date(UPDATED_DATE)
            .expiry(UPDATED_EXPIRY)
            .extra2(UPDATED_EXTRA_2)
            .extra4(UPDATED_EXTRA_4)
            .extra5(UPDATED_EXTRA_5)
            .extra6(UPDATED_EXTRA_6)
            .extra9(UPDATED_EXTRA_9)
            .extra10(UPDATED_EXTRA_10)
            .edited(UPDATED_EDITED);

        restTrainingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTraining.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTraining))
            )
            .andExpect(status().isOk());

        // Validate the Training in the database
        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeUpdate);
        Training testTraining = trainingList.get(trainingList.size() - 1);
        assertThat(testTraining.getId2Course()).isEqualTo(UPDATED_ID_2_COURSE);
        assertThat(testTraining.getId2Employee()).isEqualTo(UPDATED_ID_2_EMPLOYEE);
        assertThat(testTraining.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTraining.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testTraining.getExpiry()).isEqualTo(UPDATED_EXPIRY);
        assertThat(testTraining.getExtra1()).isEqualTo(DEFAULT_EXTRA_1);
        assertThat(testTraining.getExtra2()).isEqualTo(UPDATED_EXTRA_2);
        assertThat(testTraining.getExtra3()).isEqualTo(DEFAULT_EXTRA_3);
        assertThat(testTraining.getExtra4()).isEqualTo(UPDATED_EXTRA_4);
        assertThat(testTraining.getExtra5()).isEqualTo(UPDATED_EXTRA_5);
        assertThat(testTraining.getExtra6()).isEqualTo(UPDATED_EXTRA_6);
        assertThat(testTraining.getExtra7()).isEqualTo(DEFAULT_EXTRA_7);
        assertThat(testTraining.getExtra8()).isEqualTo(DEFAULT_EXTRA_8);
        assertThat(testTraining.getExtra9()).isEqualTo(UPDATED_EXTRA_9);
        assertThat(testTraining.getExtra10()).isEqualTo(UPDATED_EXTRA_10);
        assertThat(testTraining.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testTraining.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testTraining.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testTraining.getEditedAt()).isEqualTo(DEFAULT_EDITED_AT);
    }

    @Test
    @Transactional
    void fullUpdateTrainingWithPatch() throws Exception {
        // Initialize the database
        trainingRepository.saveAndFlush(training);

        int databaseSizeBeforeUpdate = trainingRepository.findAll().size();

        // Update the training using partial update
        Training partialUpdatedTraining = new Training();
        partialUpdatedTraining.setId(training.getId());

        partialUpdatedTraining
            .id2Course(UPDATED_ID_2_COURSE)
            .id2Employee(UPDATED_ID_2_EMPLOYEE)
            .code(UPDATED_CODE)
            .date(UPDATED_DATE)
            .expiry(UPDATED_EXPIRY)
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

        restTrainingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTraining.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTraining))
            )
            .andExpect(status().isOk());

        // Validate the Training in the database
        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeUpdate);
        Training testTraining = trainingList.get(trainingList.size() - 1);
        assertThat(testTraining.getId2Course()).isEqualTo(UPDATED_ID_2_COURSE);
        assertThat(testTraining.getId2Employee()).isEqualTo(UPDATED_ID_2_EMPLOYEE);
        assertThat(testTraining.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTraining.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testTraining.getExpiry()).isEqualTo(UPDATED_EXPIRY);
        assertThat(testTraining.getExtra1()).isEqualTo(UPDATED_EXTRA_1);
        assertThat(testTraining.getExtra2()).isEqualTo(UPDATED_EXTRA_2);
        assertThat(testTraining.getExtra3()).isEqualTo(UPDATED_EXTRA_3);
        assertThat(testTraining.getExtra4()).isEqualTo(UPDATED_EXTRA_4);
        assertThat(testTraining.getExtra5()).isEqualTo(UPDATED_EXTRA_5);
        assertThat(testTraining.getExtra6()).isEqualTo(UPDATED_EXTRA_6);
        assertThat(testTraining.getExtra7()).isEqualTo(UPDATED_EXTRA_7);
        assertThat(testTraining.getExtra8()).isEqualTo(UPDATED_EXTRA_8);
        assertThat(testTraining.getExtra9()).isEqualTo(UPDATED_EXTRA_9);
        assertThat(testTraining.getExtra10()).isEqualTo(UPDATED_EXTRA_10);
        assertThat(testTraining.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testTraining.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testTraining.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testTraining.getEditedAt()).isEqualTo(UPDATED_EDITED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingTraining() throws Exception {
        int databaseSizeBeforeUpdate = trainingRepository.findAll().size();
        training.setId(count.incrementAndGet());

        // Create the Training
        TrainingDTO trainingDTO = trainingMapper.toDto(training);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrainingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, trainingDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(trainingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Training in the database
        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTraining() throws Exception {
        int databaseSizeBeforeUpdate = trainingRepository.findAll().size();
        training.setId(count.incrementAndGet());

        // Create the Training
        TrainingDTO trainingDTO = trainingMapper.toDto(training);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrainingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(trainingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Training in the database
        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTraining() throws Exception {
        int databaseSizeBeforeUpdate = trainingRepository.findAll().size();
        training.setId(count.incrementAndGet());

        // Create the Training
        TrainingDTO trainingDTO = trainingMapper.toDto(training);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrainingMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(trainingDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Training in the database
        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTraining() throws Exception {
        // Initialize the database
        trainingRepository.saveAndFlush(training);

        int databaseSizeBeforeDelete = trainingRepository.findAll().size();

        // Delete the training
        restTrainingMockMvc
            .perform(delete(ENTITY_API_URL_ID, training.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Training> trainingList = trainingRepository.findAll();
        assertThat(trainingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
