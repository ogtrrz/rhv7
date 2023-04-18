package wf.rh.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import wf.rh.domain.Evidence;

/**
 * Spring Data JPA repository for the Evidence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, Long> {}
