package wf.rh.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wf.rh.domain.HistoricData;
import wf.rh.repository.HistoricDataRepository;
import wf.rh.service.dto.HistoricDataDTO;
import wf.rh.service.mapper.HistoricDataMapper;

/**
 * Service Implementation for managing {@link HistoricData}.
 */
@Service
@Transactional
public class HistoricDataService {

    private final Logger log = LoggerFactory.getLogger(HistoricDataService.class);

    private final HistoricDataRepository historicDataRepository;

    private final HistoricDataMapper historicDataMapper;

    public HistoricDataService(HistoricDataRepository historicDataRepository, HistoricDataMapper historicDataMapper) {
        this.historicDataRepository = historicDataRepository;
        this.historicDataMapper = historicDataMapper;
    }

    /**
     * Save a historicData.
     *
     * @param historicDataDTO the entity to save.
     * @return the persisted entity.
     */
    public HistoricDataDTO save(HistoricDataDTO historicDataDTO) {
        log.debug("Request to save HistoricData : {}", historicDataDTO);
        HistoricData historicData = historicDataMapper.toEntity(historicDataDTO);
        historicData = historicDataRepository.save(historicData);
        return historicDataMapper.toDto(historicData);
    }

    /**
     * Update a historicData.
     *
     * @param historicDataDTO the entity to save.
     * @return the persisted entity.
     */
    public HistoricDataDTO update(HistoricDataDTO historicDataDTO) {
        log.debug("Request to update HistoricData : {}", historicDataDTO);
        HistoricData historicData = historicDataMapper.toEntity(historicDataDTO);
        historicData = historicDataRepository.save(historicData);
        return historicDataMapper.toDto(historicData);
    }

    /**
     * Partially update a historicData.
     *
     * @param historicDataDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<HistoricDataDTO> partialUpdate(HistoricDataDTO historicDataDTO) {
        log.debug("Request to partially update HistoricData : {}", historicDataDTO);

        return historicDataRepository
            .findById(historicDataDTO.getId())
            .map(existingHistoricData -> {
                historicDataMapper.partialUpdate(existingHistoricData, historicDataDTO);

                return existingHistoricData;
            })
            .map(historicDataRepository::save)
            .map(historicDataMapper::toDto);
    }

    /**
     * Get all the historicData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HistoricDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HistoricData");
        return historicDataRepository.findAll(pageable).map(historicDataMapper::toDto);
    }

    /**
     * Get one historicData by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HistoricDataDTO> findOne(Long id) {
        log.debug("Request to get HistoricData : {}", id);
        return historicDataRepository.findById(id).map(historicDataMapper::toDto);
    }

    /**
     * Delete the historicData by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete HistoricData : {}", id);
        historicDataRepository.deleteById(id);
    }
}
