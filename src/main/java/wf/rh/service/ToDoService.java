package wf.rh.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wf.rh.domain.ToDo;
import wf.rh.repository.ToDoRepository;
import wf.rh.service.dto.ToDoDTO;
import wf.rh.service.mapper.ToDoMapper;

/**
 * Service Implementation for managing {@link ToDo}.
 */
@Service
@Transactional
public class ToDoService {

    private final Logger log = LoggerFactory.getLogger(ToDoService.class);

    private final ToDoRepository toDoRepository;

    private final ToDoMapper toDoMapper;

    public ToDoService(ToDoRepository toDoRepository, ToDoMapper toDoMapper) {
        this.toDoRepository = toDoRepository;
        this.toDoMapper = toDoMapper;
    }

    /**
     * Save a toDo.
     *
     * @param toDoDTO the entity to save.
     * @return the persisted entity.
     */
    public ToDoDTO save(ToDoDTO toDoDTO) {
        log.debug("Request to save ToDo : {}", toDoDTO);
        ToDo toDo = toDoMapper.toEntity(toDoDTO);
        toDo = toDoRepository.save(toDo);
        return toDoMapper.toDto(toDo);
    }

    /**
     * Update a toDo.
     *
     * @param toDoDTO the entity to save.
     * @return the persisted entity.
     */
    public ToDoDTO update(ToDoDTO toDoDTO) {
        log.debug("Request to update ToDo : {}", toDoDTO);
        ToDo toDo = toDoMapper.toEntity(toDoDTO);
        toDo = toDoRepository.save(toDo);
        return toDoMapper.toDto(toDo);
    }

    /**
     * Partially update a toDo.
     *
     * @param toDoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ToDoDTO> partialUpdate(ToDoDTO toDoDTO) {
        log.debug("Request to partially update ToDo : {}", toDoDTO);

        return toDoRepository
            .findById(toDoDTO.getId())
            .map(existingToDo -> {
                toDoMapper.partialUpdate(existingToDo, toDoDTO);

                return existingToDo;
            })
            .map(toDoRepository::save)
            .map(toDoMapper::toDto);
    }

    /**
     * Get all the toDos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ToDoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ToDos");
        return toDoRepository.findAll(pageable).map(toDoMapper::toDto);
    }

    /**
     * Get one toDo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ToDoDTO> findOne(Long id) {
        log.debug("Request to get ToDo : {}", id);
        return toDoRepository.findById(id).map(toDoMapper::toDto);
    }

    /**
     * Delete the toDo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ToDo : {}", id);
        toDoRepository.deleteById(id);
    }
}
