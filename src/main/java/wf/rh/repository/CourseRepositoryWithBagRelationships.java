package wf.rh.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import wf.rh.domain.Course;

public interface CourseRepositoryWithBagRelationships {
    Optional<Course> fetchBagRelationships(Optional<Course> course);

    List<Course> fetchBagRelationships(List<Course> courses);

    Page<Course> fetchBagRelationships(Page<Course> courses);
}
