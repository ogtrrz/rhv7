package wf.rh.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wf.rh.domain.Training;
import wf.rh.repository.TrainingRepository;
import wf.rh.service.dto.TrainingDTO;
import wf.rh.service.mapper.TrainingMapper;

/**
 * Service Implementation for managing {@link Training}.
 */
@Service
@Transactional
public class TrainingService {

    private final Logger log = LoggerFactory.getLogger(TrainingService.class);

    private final TrainingRepository trainingRepository;

    private final TrainingMapper trainingMapper;

    public TrainingService(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    /**
     * Save a training.
     *
     * @param trainingDTO the entity to save.
     * @return the persisted entity.
     */
    public TrainingDTO save(TrainingDTO trainingDTO) {
        log.debug("Request to save Training : {}", trainingDTO);
        Training training = trainingMapper.toEntity(trainingDTO);
        training = trainingRepository.save(training);
        return trainingMapper.toDto(training);
    }

    /**
     * Update a training.
     *
     * @param trainingDTO the entity to save.
     * @return the persisted entity.
     */
    public TrainingDTO update(TrainingDTO trainingDTO) {
        log.debug("Request to update Training : {}", trainingDTO);
        Training training = trainingMapper.toEntity(trainingDTO);
        training = trainingRepository.save(training);
        return trainingMapper.toDto(training);
    }

    /**
     * Partially update a training.
     *
     * @param trainingDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TrainingDTO> partialUpdate(TrainingDTO trainingDTO) {
        log.debug("Request to partially update Training : {}", trainingDTO);

        return trainingRepository
            .findById(trainingDTO.getId())
            .map(existingTraining -> {
                trainingMapper.partialUpdate(existingTraining, trainingDTO);

                return existingTraining;
            })
            .map(trainingRepository::save)
            .map(trainingMapper::toDto);
    }

    /**
     * Get all the trainings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TrainingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Trainings");
        return trainingRepository.findAll(pageable).map(trainingMapper::toDto);
    }

    /**
     * Get all the trainings with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<TrainingDTO> findAllWithEagerRelationships(Pageable pageable) {
        return trainingRepository.findAllWithEagerRelationships(pageable).map(trainingMapper::toDto);
    }

    /**
     * Get one training by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TrainingDTO> findOne(Long id) {
        log.debug("Request to get Training : {}", id);
        return trainingRepository.findOneWithEagerRelationships(id).map(trainingMapper::toDto);
    }

    /**
     * Delete the training by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Training : {}", id);
        trainingRepository.deleteById(id);
    }
}
