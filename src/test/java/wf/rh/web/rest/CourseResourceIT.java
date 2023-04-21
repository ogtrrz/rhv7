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
import wf.rh.domain.Course;
import wf.rh.domain.enumeration.TypeCourse;
import wf.rh.repository.CourseRepository;
import wf.rh.service.CourseService;
import wf.rh.service.dto.CourseDTO;
import wf.rh.service.mapper.CourseMapper;

/**
 * Integration tests for the {@link CourseResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class CourseResourceIT {

    private static final Long DEFAULT_ID_2_JOB = 1L;
    private static final Long UPDATED_ID_2_JOB = 2L;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_EXPIRATION_IN_MONTH = 1;
    private static final Integer UPDATED_EXPIRATION_IN_MONTH = 2;

    private static final TypeCourse DEFAULT_TYPE_COURSE = TypeCourse.PRESENT;
    private static final TypeCourse UPDATED_TYPE_COURSE = TypeCourse.REMOTE;

    private static final String DEFAULT_AUTORIZATION_BY = "AAAAAAAAAA";
    private static final String UPDATED_AUTORIZATION_BY = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURATION_AUTHORIZATION_IN_MONTH = 1;
    private static final Integer UPDATED_DURATION_AUTHORIZATION_IN_MONTH = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/courses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CourseRepository courseRepository;

    @Mock
    private CourseRepository courseRepositoryMock;

    @Autowired
    private CourseMapper courseMapper;

    @Mock
    private CourseService courseServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCourseMockMvc;

    private Course course;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Course createEntity(EntityManager em) {
        Course course = new Course()
            .id2Job(DEFAULT_ID_2_JOB)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .expirationInMonth(DEFAULT_EXPIRATION_IN_MONTH)
            .typeCourse(DEFAULT_TYPE_COURSE)
            .autorizationBy(DEFAULT_AUTORIZATION_BY)
            .durationAuthorizationInMonth(DEFAULT_DURATION_AUTHORIZATION_IN_MONTH)
            .description(DEFAULT_DESCRIPTION)
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
        return course;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Course createUpdatedEntity(EntityManager em) {
        Course course = new Course()
            .id2Job(UPDATED_ID_2_JOB)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .expirationInMonth(UPDATED_EXPIRATION_IN_MONTH)
            .typeCourse(UPDATED_TYPE_COURSE)
            .autorizationBy(UPDATED_AUTORIZATION_BY)
            .durationAuthorizationInMonth(UPDATED_DURATION_AUTHORIZATION_IN_MONTH)
            .description(UPDATED_DESCRIPTION)
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
        return course;
    }

    @BeforeEach
    public void initTest() {
        course = createEntity(em);
    }

    @Test
    @Transactional
    void createCourse() throws Exception {
        int databaseSizeBeforeCreate = courseRepository.findAll().size();
        // Create the Course
        CourseDTO courseDTO = courseMapper.toDto(course);
        restCourseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isCreated());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeCreate + 1);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getId2Job()).isEqualTo(DEFAULT_ID_2_JOB);
        assertThat(testCourse.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCourse.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCourse.getExpirationInMonth()).isEqualTo(DEFAULT_EXPIRATION_IN_MONTH);
        assertThat(testCourse.getTypeCourse()).isEqualTo(DEFAULT_TYPE_COURSE);
        assertThat(testCourse.getAutorizationBy()).isEqualTo(DEFAULT_AUTORIZATION_BY);
        assertThat(testCourse.getDurationAuthorizationInMonth()).isEqualTo(DEFAULT_DURATION_AUTHORIZATION_IN_MONTH);
        assertThat(testCourse.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCourse.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testCourse.getExtra1()).isEqualTo(DEFAULT_EXTRA_1);
        assertThat(testCourse.getExtra2()).isEqualTo(DEFAULT_EXTRA_2);
        assertThat(testCourse.getExtra3()).isEqualTo(DEFAULT_EXTRA_3);
        assertThat(testCourse.getExtra4()).isEqualTo(DEFAULT_EXTRA_4);
        assertThat(testCourse.getExtra5()).isEqualTo(DEFAULT_EXTRA_5);
        assertThat(testCourse.getExtra6()).isEqualTo(DEFAULT_EXTRA_6);
        assertThat(testCourse.getExtra7()).isEqualTo(DEFAULT_EXTRA_7);
        assertThat(testCourse.getExtra8()).isEqualTo(DEFAULT_EXTRA_8);
        assertThat(testCourse.getExtra9()).isEqualTo(DEFAULT_EXTRA_9);
        assertThat(testCourse.getExtra10()).isEqualTo(DEFAULT_EXTRA_10);
        assertThat(testCourse.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testCourse.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCourse.getEdited()).isEqualTo(DEFAULT_EDITED);
        assertThat(testCourse.getEditedAt()).isEqualTo(DEFAULT_EDITED_AT);
    }

    @Test
    @Transactional
    void createCourseWithExistingId() throws Exception {
        // Create the Course with an existing ID
        course.setId(1L);
        CourseDTO courseDTO = courseMapper.toDto(course);

        int databaseSizeBeforeCreate = courseRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseRepository.findAll().size();
        // set the field null
        course.setCode(null);

        // Create the Course, which fails.
        CourseDTO courseDTO = courseMapper.toDto(course);

        restCourseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isBadRequest());

        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseRepository.findAll().size();
        // set the field null
        course.setName(null);

        // Create the Course, which fails.
        CourseDTO courseDTO = courseMapper.toDto(course);

        restCourseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isBadRequest());

        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCourses() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        // Get all the courseList
        restCourseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(course.getId().intValue())))
            .andExpect(jsonPath("$.[*].id2Job").value(hasItem(DEFAULT_ID_2_JOB.intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].expirationInMonth").value(hasItem(DEFAULT_EXPIRATION_IN_MONTH)))
            .andExpect(jsonPath("$.[*].typeCourse").value(hasItem(DEFAULT_TYPE_COURSE.toString())))
            .andExpect(jsonPath("$.[*].autorizationBy").value(hasItem(DEFAULT_AUTORIZATION_BY)))
            .andExpect(jsonPath("$.[*].durationAuthorizationInMonth").value(hasItem(DEFAULT_DURATION_AUTHORIZATION_IN_MONTH)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
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

    @SuppressWarnings({ "unchecked" })
    void getAllCoursesWithEagerRelationshipsIsEnabled() throws Exception {
        when(courseServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCourseMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(courseServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCoursesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(courseServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCourseMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(courseRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        // Get the course
        restCourseMockMvc
            .perform(get(ENTITY_API_URL_ID, course.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(course.getId().intValue()))
            .andExpect(jsonPath("$.id2Job").value(DEFAULT_ID_2_JOB.intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.expirationInMonth").value(DEFAULT_EXPIRATION_IN_MONTH))
            .andExpect(jsonPath("$.typeCourse").value(DEFAULT_TYPE_COURSE.toString()))
            .andExpect(jsonPath("$.autorizationBy").value(DEFAULT_AUTORIZATION_BY))
            .andExpect(jsonPath("$.durationAuthorizationInMonth").value(DEFAULT_DURATION_AUTHORIZATION_IN_MONTH))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
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
    void getNonExistingCourse() throws Exception {
        // Get the course
        restCourseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        int databaseSizeBeforeUpdate = courseRepository.findAll().size();

        // Update the course
        Course updatedCourse = courseRepository.findById(course.getId()).get();
        // Disconnect from session so that the updates on updatedCourse are not directly saved in db
        em.detach(updatedCourse);
        updatedCourse
            .id2Job(UPDATED_ID_2_JOB)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .expirationInMonth(UPDATED_EXPIRATION_IN_MONTH)
            .typeCourse(UPDATED_TYPE_COURSE)
            .autorizationBy(UPDATED_AUTORIZATION_BY)
            .durationAuthorizationInMonth(UPDATED_DURATION_AUTHORIZATION_IN_MONTH)
            .description(UPDATED_DESCRIPTION)
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
        CourseDTO courseDTO = courseMapper.toDto(updatedCourse);

        restCourseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, courseDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(courseDTO))
            )
            .andExpect(status().isOk());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getId2Job()).isEqualTo(UPDATED_ID_2_JOB);
        assertThat(testCourse.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCourse.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCourse.getExpirationInMonth()).isEqualTo(UPDATED_EXPIRATION_IN_MONTH);
        assertThat(testCourse.getTypeCourse()).isEqualTo(UPDATED_TYPE_COURSE);
        assertThat(testCourse.getAutorizationBy()).isEqualTo(UPDATED_AUTORIZATION_BY);
        assertThat(testCourse.getDurationAuthorizationInMonth()).isEqualTo(UPDATED_DURATION_AUTHORIZATION_IN_MONTH);
        assertThat(testCourse.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourse.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testCourse.getExtra1()).isEqualTo(UPDATED_EXTRA_1);
        assertThat(testCourse.getExtra2()).isEqualTo(UPDATED_EXTRA_2);
        assertThat(testCourse.getExtra3()).isEqualTo(UPDATED_EXTRA_3);
        assertThat(testCourse.getExtra4()).isEqualTo(UPDATED_EXTRA_4);
        assertThat(testCourse.getExtra5()).isEqualTo(UPDATED_EXTRA_5);
        assertThat(testCourse.getExtra6()).isEqualTo(UPDATED_EXTRA_6);
        assertThat(testCourse.getExtra7()).isEqualTo(UPDATED_EXTRA_7);
        assertThat(testCourse.getExtra8()).isEqualTo(UPDATED_EXTRA_8);
        assertThat(testCourse.getExtra9()).isEqualTo(UPDATED_EXTRA_9);
        assertThat(testCourse.getExtra10()).isEqualTo(UPDATED_EXTRA_10);
        assertThat(testCourse.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testCourse.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCourse.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testCourse.getEditedAt()).isEqualTo(UPDATED_EDITED_AT);
    }

    @Test
    @Transactional
    void putNonExistingCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();
        course.setId(count.incrementAndGet());

        // Create the Course
        CourseDTO courseDTO = courseMapper.toDto(course);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, courseDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(courseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();
        course.setId(count.incrementAndGet());

        // Create the Course
        CourseDTO courseDTO = courseMapper.toDto(course);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(courseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();
        course.setId(count.incrementAndGet());

        // Create the Course
        CourseDTO courseDTO = courseMapper.toDto(course);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourseMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCourseWithPatch() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        int databaseSizeBeforeUpdate = courseRepository.findAll().size();

        // Update the course using partial update
        Course partialUpdatedCourse = new Course();
        partialUpdatedCourse.setId(course.getId());

        partialUpdatedCourse
            .name(UPDATED_NAME)
            .typeCourse(UPDATED_TYPE_COURSE)
            .autorizationBy(UPDATED_AUTORIZATION_BY)
            .description(UPDATED_DESCRIPTION)
            .extra2(UPDATED_EXTRA_2)
            .extra3(UPDATED_EXTRA_3)
            .extra5(UPDATED_EXTRA_5)
            .extra7(UPDATED_EXTRA_7)
            .extra8(UPDATED_EXTRA_8)
            .extra9(UPDATED_EXTRA_9)
            .extra10(UPDATED_EXTRA_10)
            .created(UPDATED_CREATED)
            .edited(UPDATED_EDITED);

        restCourseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCourse.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCourse))
            )
            .andExpect(status().isOk());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getId2Job()).isEqualTo(DEFAULT_ID_2_JOB);
        assertThat(testCourse.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCourse.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCourse.getExpirationInMonth()).isEqualTo(DEFAULT_EXPIRATION_IN_MONTH);
        assertThat(testCourse.getTypeCourse()).isEqualTo(UPDATED_TYPE_COURSE);
        assertThat(testCourse.getAutorizationBy()).isEqualTo(UPDATED_AUTORIZATION_BY);
        assertThat(testCourse.getDurationAuthorizationInMonth()).isEqualTo(DEFAULT_DURATION_AUTHORIZATION_IN_MONTH);
        assertThat(testCourse.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourse.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testCourse.getExtra1()).isEqualTo(DEFAULT_EXTRA_1);
        assertThat(testCourse.getExtra2()).isEqualTo(UPDATED_EXTRA_2);
        assertThat(testCourse.getExtra3()).isEqualTo(UPDATED_EXTRA_3);
        assertThat(testCourse.getExtra4()).isEqualTo(DEFAULT_EXTRA_4);
        assertThat(testCourse.getExtra5()).isEqualTo(UPDATED_EXTRA_5);
        assertThat(testCourse.getExtra6()).isEqualTo(DEFAULT_EXTRA_6);
        assertThat(testCourse.getExtra7()).isEqualTo(UPDATED_EXTRA_7);
        assertThat(testCourse.getExtra8()).isEqualTo(UPDATED_EXTRA_8);
        assertThat(testCourse.getExtra9()).isEqualTo(UPDATED_EXTRA_9);
        assertThat(testCourse.getExtra10()).isEqualTo(UPDATED_EXTRA_10);
        assertThat(testCourse.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testCourse.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCourse.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testCourse.getEditedAt()).isEqualTo(DEFAULT_EDITED_AT);
    }

    @Test
    @Transactional
    void fullUpdateCourseWithPatch() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        int databaseSizeBeforeUpdate = courseRepository.findAll().size();

        // Update the course using partial update
        Course partialUpdatedCourse = new Course();
        partialUpdatedCourse.setId(course.getId());

        partialUpdatedCourse
            .id2Job(UPDATED_ID_2_JOB)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .expirationInMonth(UPDATED_EXPIRATION_IN_MONTH)
            .typeCourse(UPDATED_TYPE_COURSE)
            .autorizationBy(UPDATED_AUTORIZATION_BY)
            .durationAuthorizationInMonth(UPDATED_DURATION_AUTHORIZATION_IN_MONTH)
            .description(UPDATED_DESCRIPTION)
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

        restCourseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCourse.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCourse))
            )
            .andExpect(status().isOk());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getId2Job()).isEqualTo(UPDATED_ID_2_JOB);
        assertThat(testCourse.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCourse.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCourse.getExpirationInMonth()).isEqualTo(UPDATED_EXPIRATION_IN_MONTH);
        assertThat(testCourse.getTypeCourse()).isEqualTo(UPDATED_TYPE_COURSE);
        assertThat(testCourse.getAutorizationBy()).isEqualTo(UPDATED_AUTORIZATION_BY);
        assertThat(testCourse.getDurationAuthorizationInMonth()).isEqualTo(UPDATED_DURATION_AUTHORIZATION_IN_MONTH);
        assertThat(testCourse.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourse.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testCourse.getExtra1()).isEqualTo(UPDATED_EXTRA_1);
        assertThat(testCourse.getExtra2()).isEqualTo(UPDATED_EXTRA_2);
        assertThat(testCourse.getExtra3()).isEqualTo(UPDATED_EXTRA_3);
        assertThat(testCourse.getExtra4()).isEqualTo(UPDATED_EXTRA_4);
        assertThat(testCourse.getExtra5()).isEqualTo(UPDATED_EXTRA_5);
        assertThat(testCourse.getExtra6()).isEqualTo(UPDATED_EXTRA_6);
        assertThat(testCourse.getExtra7()).isEqualTo(UPDATED_EXTRA_7);
        assertThat(testCourse.getExtra8()).isEqualTo(UPDATED_EXTRA_8);
        assertThat(testCourse.getExtra9()).isEqualTo(UPDATED_EXTRA_9);
        assertThat(testCourse.getExtra10()).isEqualTo(UPDATED_EXTRA_10);
        assertThat(testCourse.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testCourse.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCourse.getEdited()).isEqualTo(UPDATED_EDITED);
        assertThat(testCourse.getEditedAt()).isEqualTo(UPDATED_EDITED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();
        course.setId(count.incrementAndGet());

        // Create the Course
        CourseDTO courseDTO = courseMapper.toDto(course);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, courseDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(courseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();
        course.setId(count.incrementAndGet());

        // Create the Course
        CourseDTO courseDTO = courseMapper.toDto(course);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(courseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();
        course.setId(count.incrementAndGet());

        // Create the Course
        CourseDTO courseDTO = courseMapper.toDto(course);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourseMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(courseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        int databaseSizeBeforeDelete = courseRepository.findAll().size();

        // Delete the course
        restCourseMockMvc
            .perform(delete(ENTITY_API_URL_ID, course.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
