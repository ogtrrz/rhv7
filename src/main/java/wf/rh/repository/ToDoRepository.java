package wf.rh.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import wf.rh.domain.ToDo;

/**
 * Spring Data JPA repository for the ToDo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {}
