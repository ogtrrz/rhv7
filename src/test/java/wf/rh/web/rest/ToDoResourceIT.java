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
            .link(DEFAULT_LINK);
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
            .link(UPDATED_LINK);
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
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)));
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
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK));
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
            .link(UPDATED_LINK);
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

        partialUpdatedToDo.description(UPDATED_DESCRIPTION).state(UPDATED_STATE);

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
            .link(UPDATED_LINK);

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
