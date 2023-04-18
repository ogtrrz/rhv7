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
import wf.rh.repository.RequirentsRepository;
import wf.rh.service.RequirentsService;
import wf.rh.service.dto.RequirentsDTO;
import wf.rh.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link wf.rh.domain.Requirents}.
 */
@RestController
@RequestMapping("/api")
public class RequirentsResource {

    private final Logger log = LoggerFactory.getLogger(RequirentsResource.class);

    private static final String ENTITY_NAME = "requirents";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequirentsService requirentsService;

    private final RequirentsRepository requirentsRepository;

    public RequirentsResource(RequirentsService requirentsService, RequirentsRepository requirentsRepository) {
        this.requirentsService = requirentsService;
        this.requirentsRepository = requirentsRepository;
    }

    /**
     * {@code POST  /requirents} : Create a new requirents.
     *
     * @param requirentsDTO the requirentsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requirentsDTO, or with status {@code 400 (Bad Request)} if the requirents has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/requirents")
    public ResponseEntity<RequirentsDTO> createRequirents(@Valid @RequestBody RequirentsDTO requirentsDTO) throws URISyntaxException {
        log.debug("REST request to save Requirents : {}", requirentsDTO);
        if (requirentsDTO.getId() != null) {
            throw new BadRequestAlertException("A new requirents cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequirentsDTO result = requirentsService.save(requirentsDTO);
        return ResponseEntity
            .created(new URI("/api/requirents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /requirents/:id} : Updates an existing requirents.
     *
     * @param id the id of the requirentsDTO to save.
     * @param requirentsDTO the requirentsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requirentsDTO,
     * or with status {@code 400 (Bad Request)} if the requirentsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requirentsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/requirents/{id}")
    public ResponseEntity<RequirentsDTO> updateRequirents(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody RequirentsDTO requirentsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Requirents : {}, {}", id, requirentsDTO);
        if (requirentsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requirentsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!requirentsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RequirentsDTO result = requirentsService.update(requirentsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, requirentsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /requirents/:id} : Partial updates given fields of an existing requirents, field will ignore if it is null
     *
     * @param id the id of the requirentsDTO to save.
     * @param requirentsDTO the requirentsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requirentsDTO,
     * or with status {@code 400 (Bad Request)} if the requirentsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the requirentsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the requirentsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/requirents/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RequirentsDTO> partialUpdateRequirents(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RequirentsDTO requirentsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Requirents partially : {}, {}", id, requirentsDTO);
        if (requirentsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requirentsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!requirentsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RequirentsDTO> result = requirentsService.partialUpdate(requirentsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, requirentsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /requirents} : get all the requirents.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requirents in body.
     */
    @GetMapping("/requirents")
    public ResponseEntity<List<RequirentsDTO>> getAllRequirents(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Requirents");
        Page<RequirentsDTO> page = requirentsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /requirents/:id} : get the "id" requirents.
     *
     * @param id the id of the requirentsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requirentsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/requirents/{id}")
    public ResponseEntity<RequirentsDTO> getRequirents(@PathVariable Long id) {
        log.debug("REST request to get Requirents : {}", id);
        Optional<RequirentsDTO> requirentsDTO = requirentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requirentsDTO);
    }

    /**
     * {@code DELETE  /requirents/:id} : delete the "id" requirents.
     *
     * @param id the id of the requirentsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/requirents/{id}")
    public ResponseEntity<Void> deleteRequirents(@PathVariable Long id) {
        log.debug("REST request to delete Requirents : {}", id);
        requirentsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
