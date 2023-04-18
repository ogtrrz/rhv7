package wf.rh.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import wf.rh.repository.ToDoRepository;
import wf.rh.service.ToDoService;
import wf.rh.service.dto.ToDoDTO;
import wf.rh.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link wf.rh.domain.ToDo}.
 */
@RestController
@RequestMapping("/api")
public class ToDoResource {

    private final Logger log = LoggerFactory.getLogger(ToDoResource.class);

    private static final String ENTITY_NAME = "toDo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ToDoService toDoService;

    private final ToDoRepository toDoRepository;

    public ToDoResource(ToDoService toDoService, ToDoRepository toDoRepository) {
        this.toDoService = toDoService;
        this.toDoRepository = toDoRepository;
    }

    /**
     * {@code POST  /to-dos} : Create a new toDo.
     *
     * @param toDoDTO the toDoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new toDoDTO, or with status {@code 400 (Bad Request)} if the toDo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/to-dos")
    public ResponseEntity<ToDoDTO> createToDo(@Valid @RequestBody ToDoDTO toDoDTO) throws URISyntaxException {
        log.debug("REST request to save ToDo : {}", toDoDTO);
        if (toDoDTO.getId() != null) {
            throw new BadRequestAlertException("A new toDo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ToDoDTO result = toDoService.save(toDoDTO);
        return ResponseEntity
            .created(new URI("/api/to-dos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /to-dos/:id} : Updates an existing toDo.
     *
     * @param id the id of the toDoDTO to save.
     * @param toDoDTO the toDoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated toDoDTO,
     * or with status {@code 400 (Bad Request)} if the toDoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the toDoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/to-dos/{id}")
    public ResponseEntity<ToDoDTO> updateToDo(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ToDoDTO toDoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ToDo : {}, {}", id, toDoDTO);
        if (toDoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, toDoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!toDoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ToDoDTO result = toDoService.update(toDoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, toDoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /to-dos/:id} : Partial updates given fields of an existing toDo, field will ignore if it is null
     *
     * @param id the id of the toDoDTO to save.
     * @param toDoDTO the toDoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated toDoDTO,
     * or with status {@code 400 (Bad Request)} if the toDoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the toDoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the toDoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/to-dos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ToDoDTO> partialUpdateToDo(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ToDoDTO toDoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ToDo partially : {}, {}", id, toDoDTO);
        if (toDoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, toDoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!toDoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ToDoDTO> result = toDoService.partialUpdate(toDoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, toDoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /to-dos} : get all the toDos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of toDos in body.
     */
    @GetMapping("/to-dos")
    public ResponseEntity<List<ToDoDTO>> getAllToDos(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ToDos");
        Page<ToDoDTO> page = toDoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /to-dos/:id} : get the "id" toDo.
     *
     * @param id the id of the toDoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the toDoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/to-dos/{id}")
    public ResponseEntity<ToDoDTO> getToDo(@PathVariable Long id) {
        log.debug("REST request to get ToDo : {}", id);
        Optional<ToDoDTO> toDoDTO = toDoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(toDoDTO);
    }

    /**
     * {@code DELETE  /to-dos/:id} : delete the "id" toDo.
     *
     * @param id the id of the toDoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/to-dos/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        log.debug("REST request to delete ToDo : {}", id);
        toDoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
