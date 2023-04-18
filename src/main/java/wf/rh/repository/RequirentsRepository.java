package wf.rh.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import wf.rh.domain.Requirents;

/**
 * Spring Data JPA repository for the Requirents entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequirentsRepository extends JpaRepository<Requirents, Long> {}
