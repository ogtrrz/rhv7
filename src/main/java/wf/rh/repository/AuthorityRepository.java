package wf.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wf.rh.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
