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
import wf.rh.domain.ToDo;
import wf.rh.domain.enumeration.StateToDo;
import wf.rh.repository.ToDoRepository;
import wf.rh.service.dto.ToDoDTO;
import wf.rh.service.mapper.ToDoMapper;

/**
 * Integration tests for the {@link ToDoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ToDoResourceIT {

    private static final Long DEFAULT_ID_2_EMPLOYEE = 1L;
    private static final Long UPDATED_ID_2_EMPLOYEE = 2L;

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final StateToDo DEFAULT_STATE = StateToDo.NEW;
    private static final StateToDo UPDATED_STATE = StateToDo.CHECK;

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/to-dos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ToDoMapper toDoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restToDoMockMvc;

    private ToDo toDo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ToDo createEntity(EntityManager em) {
        ToDo toDo = new ToDo()
            .id2Employee(DEFAULT_ID_2_EMPLOYEE)
            .date(DEFAULT_DATE)
            .description(DEFAULT_DESCRIPTION)
            .state(DEFAULT_STATE)
            .link(DEFAULT_LINK)
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
        return toDo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ToDo createUpdatedEntity(EntityManager em) {
        ToDo toDo = new ToDo()
            .id2Employee(UPDATED_ID_2_EMPLOYEE)
            .date(UPDATED_DATE)
            .description(UPDATED_DESCRIPTION)
            .state(UPDATED_STATE)
            .link(UPDATED_LINK)
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
        return toDo;
    }

    @BeforeEach
    public void initTest() {
        toDo = createEntity(em);
    }

    @Test
    @Transactional
    void createToDo() throws Exception {
        int databaseSizeBeforeCreate = toDoRepository.findAll().size();
        // Create the ToDo
        ToDoDTO toDoDTO = toDoMapper.toDto(toDo);
        restToDoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toDoDTO)))
            .andExpect(status().isCreated());

        // Validate the ToDo in the database
        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeCreate + 1);
        ToDo testToDo = toDoList.get(toDoList.size() - 1);
        assertThat(testToDo.getId2Employee()).isEqualTo(DEFAULT_ID_2_EMPLOYEE);
        assertThat(testToDo.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testToDo.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testToDo.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testToDo.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testToDo.getExtra1()).isEqualTo(DEFAULT_EXTRA_1);
        assertThat(testToDo.getExtra2()).isEqualTo(DEFAULT_EXTRA_2);
        assertThat(testToDo.getExtra3()).isEqualTo(DEFAULT_EXTRA_3);
        assertThat(testToDo.getExtra4()).isEqualTo(DEFAULT_EXTRA_4);
        assertThat(testToDo.getExtra5()).isEqualTo(DEFAULT_EXTRA_5);
        assertThat(testToDo.getExtra6()).isEqualTo(DEFAULT_EXTRA_6);
        assertThat(testToDo.getExtra7()).isEqualTo(DEFAULT_EXTRA_7);
        assertThat(testToDo.getExtra8()).isEqualTo(DEFAULT_EXTRA_8);
        assertThat(testToDo.getExtra9()).isEqualTo(DEFAULT_EXTRA_9);
        assertThat(testToDo.getExtra10()).isEqualTo(DEFAULT_EXTRA_10);
        assertThat(testToDo.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testToDo.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testToDo.getEdited()).isEqualTo(DEFAULT_EDITED);
        assertThat(testToDo.getEditedAt()).isEqualTo(DEFAULT_EDITED_AT);
    }

    @Test
    @Transactional
    void createToDoWithExistingId() throws Exception {
        // Create the ToDo with an existing ID
        toDo.setId(1L);
        ToDoDTO toDoDTO = toDoMapper.toDto(toDo);

        int databaseSizeBeforeCreate = toDoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restToDoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toDoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ToDo in the database
        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = toDoRepository.findAll().size();
        // set the field null
        toDo.setDescription(null);

        // Create the ToDo, which fails.
        ToDoDTO toDoDTO = toDoMapper.toDto(toDo);

        restToDoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toDoDTO)))
            .andExpect(status().isBadRequest());

        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllToDos() throws Exception {
        // Initialize the database
        toDoRepository.saveAndFlush(toDo);

        // Get all the toDoList
        restToDoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(toDo.getId().intValue())))
            .andExpect(jsonPath("$.[*].id2Employee").value(hasItem(DEFAULT_ID_2_EMPLOYEE.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
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
    void getToDo() throws Exception {
        // Initialize the database
        toDoRepository.saveAndFlush(toDo);

        // Get the toDo
        restToDoMockMvc
            .perform(get(ENTITY_API_URL_ID, toDo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(toDo.getId().intValue()))
            .andExpect(jsonPath("$.id2Employee").value(DEFAULT_ID_2_EMPLOYEE.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK))
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
    void getNonExistingToDo() throws Exception {
        // Get the toDo
        restToDoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingToDo() throws Exception {
        // Initialize the database
        toDoRepository.saveAndFlush(toDo);

        int databaseSizeBeforeUpdate = toDoRepository.findAll().size();

        // Update the toDo
        ToDo updatedToDo = toDoRepository.findById(toDo.getId()).get();
        // Disconnect from session so that the updates on updatedToDo are not directly saved in db
        em.detach(updatedToDo);
        updatedToDo
            .id2Employee(UPDATED_ID_2_EMPLOYEE)
            .date(UPDATED_DATE)
            .description(UPDATED_DESCRIPTION)
            .state(UPDATED_STATE)
            .link(UPDATED_LINK)
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
        ToDoDTO toDoDTO = toDoMapper.toDto(updatedToDo);

        restToDoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, toDoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toDoDTO))
            )
            .andExpect(status().isOk());

        // Validate the ToDo in the database
        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeUpdate);
        ToDo testToDo = toDoList.get(toDoList.size() - 1);
        assertThat(testToDo.getId2Employee()).isEqualTo(UPDATED_ID_2_EMPLOYEE);
        assertThat(testToDo.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testToDo.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testToDo.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testToDo.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testToDo.getExtra1()).isEqualTo(UPDATED_EXTRA_1);
        assertThat(testToDo.getExtra2()).isEqualTo(UPDATED_EXTRA_2);
        assertThat(testToDo.getExtra3()).isEqualTo(UPDATED_EXTRA_3);
        assertThat(testToDo.getExtra4()).isEqualTo(UPDATED_EXTRA_4);
        assertThat(testToDo.getExtra5()).isEqualTo(UPDATED_EXTRA_5);
        assertThat(testToDo.getExtra6()).isEqualTo(UPDATED_EXTRA_6);
        assertThat(testToDo.getExtra7()).isEqualTo(UPDATED_EXTRA_7);
        assertThat(testToDo.getExtra8()).isEqualTo(UPDATED_EXTRA_8);
        assertThat(testToDo.getExtra9()).isEqualTo(UPDATED_EXTRA_9);
        assertThat(testToDo.getExtra10()).isEqualTo(UPDATED_EXTRA_10);
        assertThat(testToDo.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testToDo.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testToDo.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testToDo.getEditedAt()).isEqualTo(UPDATED_EDITED_AT);
    }

    @Test
    @Transactional
    void putNonExistingToDo() throws Exception {
        int databaseSizeBeforeUpdate = toDoRepository.findAll().size();
        toDo.setId(count.incrementAndGet());

        // Create the ToDo
        ToDoDTO toDoDTO = toDoMapper.toDto(toDo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restToDoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, toDoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toDoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToDo in the database
        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchToDo() throws Exception {
        int databaseSizeBeforeUpdate = toDoRepository.findAll().size();
        toDo.setId(count.incrementAndGet());

        // Create the ToDo
        ToDoDTO toDoDTO = toDoMapper.toDto(toDo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToDoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toDoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToDo in the database
        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamToDo() throws Exception {
        int databaseSizeBeforeUpdate = toDoRepository.findAll().size();
        toDo.setId(count.incrementAndGet());

        // Create the ToDo
        ToDoDTO toDoDTO = toDoMapper.toDto(toDo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToDoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toDoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ToDo in the database
        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateToDoWithPatch() throws Exception {
        // Initialize the database
        toDoRepository.saveAndFlush(toDo);

        int databaseSizeBeforeUpdate = toDoRepository.findAll().size();

        // Update the toDo using partial update
        ToDo partialUpdatedToDo = new ToDo();
        partialUpdatedToDo.setId(toDo.getId());

        partialUpdatedToDo
            .description(UPDATED_DESCRIPTION)
            .state(UPDATED_STATE)
            .extra1(UPDATED_EXTRA_1)
            .extra4(UPDATED_EXTRA_4)
            .extra5(UPDATED_EXTRA_5)
            .extra6(UPDATED_EXTRA_6)
            .extra7(UPDATED_EXTRA_7)
            .extra8(UPDATED_EXTRA_8)
            .extra10(UPDATED_EXTRA_10)
            .createdAt(UPDATED_CREATED_AT)
            .edited(UPDATED_EDITED)
            .editedAt(UPDATED_EDITED_AT);

        restToDoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedToDo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedToDo))
            )
            .andExpect(status().isOk());

        // Validate the ToDo in the database
        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeUpdate);
        ToDo testToDo = toDoList.get(toDoList.size() - 1);
        assertThat(testToDo.getId2Employee()).isEqualTo(DEFAULT_ID_2_EMPLOYEE);
        assertThat(testToDo.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testToDo.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testToDo.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testToDo.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testToDo.getExtra1()).isEqualTo(UPDATED_EXTRA_1);
        assertThat(testToDo.getExtra2()).isEqualTo(DEFAULT_EXTRA_2);
        assertThat(testToDo.getExtra3()).isEqualTo(DEFAULT_EXTRA_3);
        assertThat(testToDo.getExtra4()).isEqualTo(UPDATED_EXTRA_4);
        assertThat(testToDo.getExtra5()).isEqualTo(UPDATED_EXTRA_5);
        assertThat(testToDo.getExtra6()).isEqualTo(UPDATED_EXTRA_6);
        assertThat(testToDo.getExtra7()).isEqualTo(UPDATED_EXTRA_7);
        assertThat(testToDo.getExtra8()).isEqualTo(UPDATED_EXTRA_8);
        assertThat(testToDo.getExtra9()).isEqualTo(DEFAULT_EXTRA_9);
        assertThat(testToDo.getExtra10()).isEqualTo(UPDATED_EXTRA_10);
        assertThat(testToDo.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testToDo.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testToDo.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testToDo.getEditedAt()).isEqualTo(UPDATED_EDITED_AT);
    }

    @Test
    @Transactional
    void fullUpdateToDoWithPatch() throws Exception {
        // Initialize the database
        toDoRepository.saveAndFlush(toDo);

        int databaseSizeBeforeUpdate = toDoRepository.findAll().size();

        // Update the toDo using partial update
        ToDo partialUpdatedToDo = new ToDo();
        partialUpdatedToDo.setId(toDo.getId());

        partialUpdatedToDo
            .id2Employee(UPDATED_ID_2_EMPLOYEE)
            .date(UPDATED_DATE)
            .description(UPDATED_DESCRIPTION)
            .state(UPDATED_STATE)
            .link(UPDATED_LINK)
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

        restToDoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedToDo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedToDo))
            )
            .andExpect(status().isOk());

        // Validate the ToDo in the database
        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeUpdate);
        ToDo testToDo = toDoList.get(toDoList.size() - 1);
        assertThat(testToDo.getId2Employee()).isEqualTo(UPDATED_ID_2_EMPLOYEE);
        assertThat(testToDo.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testToDo.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testToDo.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testToDo.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testToDo.getExtra1()).isEqualTo(UPDATED_EXTRA_1);
        assertThat(testToDo.getExtra2()).isEqualTo(UPDATED_EXTRA_2);
        assertThat(testToDo.getExtra3()).isEqualTo(UPDATED_EXTRA_3);
        assertThat(testToDo.getExtra4()).isEqualTo(UPDATED_EXTRA_4);
        assertThat(testToDo.getExtra5()).isEqualTo(UPDATED_EXTRA_5);
        assertThat(testToDo.getExtra6()).isEqualTo(UPDATED_EXTRA_6);
        assertThat(testToDo.getExtra7()).isEqualTo(UPDATED_EXTRA_7);
        assertThat(testToDo.getExtra8()).isEqualTo(UPDATED_EXTRA_8);
        assertThat(testToDo.getExtra9()).isEqualTo(UPDATED_EXTRA_9);
        assertThat(testToDo.getExtra10()).isEqualTo(UPDATED_EXTRA_10);
        assertThat(testToDo.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testToDo.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testToDo.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testToDo.getEditedAt()).isEqualTo(UPDATED_EDITED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingToDo() throws Exception {
        int databaseSizeBeforeUpdate = toDoRepository.findAll().size();
        toDo.setId(count.incrementAndGet());

        // Create the ToDo
        ToDoDTO toDoDTO = toDoMapper.toDto(toDo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restToDoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, toDoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(toDoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToDo in the database
        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchToDo() throws Exception {
        int databaseSizeBeforeUpdate = toDoRepository.findAll().size();
        toDo.setId(count.incrementAndGet());

        // Create the ToDo
        ToDoDTO toDoDTO = toDoMapper.toDto(toDo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToDoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(toDoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToDo in the database
        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamToDo() throws Exception {
        int databaseSizeBeforeUpdate = toDoRepository.findAll().size();
        toDo.setId(count.incrementAndGet());

        // Create the ToDo
        ToDoDTO toDoDTO = toDoMapper.toDto(toDo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToDoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(toDoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ToDo in the database
        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteToDo() throws Exception {
        // Initialize the database
        toDoRepository.saveAndFlush(toDo);

        int databaseSizeBeforeDelete = toDoRepository.findAll().size();

        // Delete the toDo
        restToDoMockMvc
            .perform(delete(ENTITY_API_URL_ID, toDo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ToDo> toDoList = toDoRepository.findAll();
        assertThat(toDoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
