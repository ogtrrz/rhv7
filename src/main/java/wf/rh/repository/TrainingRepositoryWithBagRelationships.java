package wf.rh.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import wf.rh.domain.Training;

public interface TrainingRepositoryWithBagRelationships {
    Optional<Training> fetchBagRelationships(Optional<Training> training);

    List<Training> fetchBagRelationships(List<Training> trainings);

    Page<Training> fetchBagRelationships(Page<Training> trainings);
}
