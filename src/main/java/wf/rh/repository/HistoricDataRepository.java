package wf.rh.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import wf.rh.domain.HistoricData;

/**
 * Spring Data JPA repository for the HistoricData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoricDataRepository extends JpaRepository<HistoricData, Long> {}
