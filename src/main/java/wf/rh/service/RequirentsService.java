package wf.rh.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wf.rh.domain.Requirents;
import wf.rh.repository.RequirentsRepository;
import wf.rh.service.dto.RequirentsDTO;
import wf.rh.service.mapper.RequirentsMapper;

/**
 * Service Implementation for managing {@link Requirents}.
 */
@Service
@Transactional
public class RequirentsService {

    private final Logger log = LoggerFactory.getLogger(RequirentsService.class);

    private final RequirentsRepository requirentsRepository;

    private final RequirentsMapper requirentsMapper;

    public RequirentsService(RequirentsRepository requirentsRepository, RequirentsMapper requirentsMapper) {
        this.requirentsRepository = requirentsRepository;
        this.requirentsMapper = requirentsMapper;
    }

    /**
     * Save a requirents.
     *
     * @param requirentsDTO the entity to save.
     * @return the persisted entity.
     */
    public RequirentsDTO save(RequirentsDTO requirentsDTO) {
        log.debug("Request to save Requirents : {}", requirentsDTO);
        Requirents requirents = requirentsMapper.toEntity(requirentsDTO);
        requirents = requirentsRepository.save(requirents);
        return requirentsMapper.toDto(requirents);
    }

    /**
     * Update a requirents.
     *
     * @param requirentsDTO the entity to save.
     * @return the persisted entity.
     */
    public RequirentsDTO update(RequirentsDTO requirentsDTO) {
        log.debug("Request to update Requirents : {}", requirentsDTO);
        Requirents requirents = requirentsMapper.toEntity(requirentsDTO);
        requirents = requirentsRepository.save(requirents);
        return requirentsMapper.toDto(requirents);
    }

    /**
     * Partially update a requirents.
     *
     * @param requirentsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RequirentsDTO> partialUpdate(RequirentsDTO requirentsDTO) {
        log.debug("Request to partially update Requirents : {}", requirentsDTO);

        return requirentsRepository
            .findById(requirentsDTO.getId())
            .map(existingRequirents -> {
                requirentsMapper.partialUpdate(existingRequirents, requirentsDTO);

                return existingRequirents;
            })
            .map(requirentsRepository::save)
            .map(requirentsMapper::toDto);
    }

    /**
     * Get all the requirents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RequirentsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Requirents");
        return requirentsRepository.findAll(pageable).map(requirentsMapper::toDto);
    }

    /**
     * Get one requirents by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RequirentsDTO> findOne(Long id) {
        log.debug("Request to get Requirents : {}", id);
        return requirentsRepository.findById(id).map(requirentsMapper::toDto);
    }

    /**
     * Delete the requirents by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Requirents : {}", id);
        requirentsRepository.deleteById(id);
    }
}
