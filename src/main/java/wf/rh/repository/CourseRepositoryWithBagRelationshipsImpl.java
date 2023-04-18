package wf.rh.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import wf.rh.domain.Course;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class CourseRepositoryWithBagRelationshipsImpl implements CourseRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Course> fetchBagRelationships(Optional<Course> course) {
        return course.map(this::fetchTrainings).map(this::fetchRequirents);
    }

    @Override
    public Page<Course> fetchBagRelationships(Page<Course> courses) {
        return new PageImpl<>(fetchBagRelationships(courses.getContent()), courses.getPageable(), courses.getTotalElements());
    }

    @Override
    public List<Course> fetchBagRelationships(List<Course> courses) {
        return Optional.of(courses).map(this::fetchTrainings).map(this::fetchRequirents).orElse(Collections.emptyList());
    }

    Course fetchTrainings(Course result) {
        return entityManager
            .createQuery("select course from Course course left join fetch course.trainings where course is :course", Course.class)
            .setParameter("course", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Course> fetchTrainings(List<Course> courses) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, courses.size()).forEach(index -> order.put(courses.get(index).getId(), index));
        List<Course> result = entityManager
            .createQuery(
                "select distinct course from Course course left join fetch course.trainings where course in :courses",
                Course.class
            )
            .setParameter("courses", courses)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Course fetchRequirents(Course result) {
        return entityManager
            .createQuery("select course from Course course left join fetch course.requirents where course is :course", Course.class)
            .setParameter("course", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Course> fetchRequirents(List<Course> courses) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, courses.size()).forEach(index -> order.put(courses.get(index).getId(), index));
        List<Course> result = entityManager
            .createQuery(
                "select distinct course from Course course left join fetch course.requirents where course in :courses",
                Course.class
            )
            .setParameter("courses", courses)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
