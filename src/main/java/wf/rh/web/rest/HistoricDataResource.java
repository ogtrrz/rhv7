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
import wf.rh.repository.HistoricDataRepository;
import wf.rh.service.HistoricDataService;
import wf.rh.service.dto.HistoricDataDTO;
import wf.rh.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link wf.rh.domain.HistoricData}.
 */
@RestController
@RequestMapping("/api")
public class HistoricDataResource {

    private final Logger log = LoggerFactory.getLogger(HistoricDataResource.class);

    private static final String ENTITY_NAME = "historicData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistoricDataService historicDataService;

    private final HistoricDataRepository historicDataRepository;

    public HistoricDataResource(HistoricDataService historicDataService, HistoricDataRepository historicDataRepository) {
        this.historicDataService = historicDataService;
        this.historicDataRepository = historicDataRepository;
    }

    /**
     * {@code POST  /historic-data} : Create a new historicData.
     *
     * @param historicDataDTO the historicDataDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historicDataDTO, or with status {@code 400 (Bad Request)} if the historicData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/historic-data")
    public ResponseEntity<HistoricDataDTO> createHistoricData(@Valid @RequestBody HistoricDataDTO historicDataDTO)
        throws URISyntaxException {
        log.debug("REST request to save HistoricData : {}", historicDataDTO);
        if (historicDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new historicData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistoricDataDTO result = historicDataService.save(historicDataDTO);
        return ResponseEntity
            .created(new URI("/api/historic-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /historic-data/:id} : Updates an existing historicData.
     *
     * @param id the id of the historicDataDTO to save.
     * @param historicDataDTO the historicDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historicDataDTO,
     * or with status {@code 400 (Bad Request)} if the historicDataDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the historicDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/historic-data/{id}")
    public ResponseEntity<HistoricDataDTO> updateHistoricData(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody HistoricDataDTO historicDataDTO
    ) throws URISyntaxException {
        log.debug("REST request to update HistoricData : {}, {}", id, historicDataDTO);
        if (historicDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, historicDataDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!historicDataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HistoricDataDTO result = historicDataService.update(historicDataDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, historicDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /historic-data/:id} : Partial updates given fields of an existing historicData, field will ignore if it is null
     *
     * @param id the id of the historicDataDTO to save.
     * @param historicDataDTO the historicDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historicDataDTO,
     * or with status {@code 400 (Bad Request)} if the historicDataDTO is not valid,
     * or with status {@code 404 (Not Found)} if the historicDataDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the historicDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/historic-data/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HistoricDataDTO> partialUpdateHistoricData(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody HistoricDataDTO historicDataDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update HistoricData partially : {}, {}", id, historicDataDTO);
        if (historicDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, historicDataDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!historicDataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HistoricDataDTO> result = historicDataService.partialUpdate(historicDataDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, historicDataDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /historic-data} : get all the historicData.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historicData in body.
     */
    @GetMapping("/historic-data")
    public ResponseEntity<List<HistoricDataDTO>> getAllHistoricData(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of HistoricData");
        Page<HistoricDataDTO> page = historicDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /historic-data/:id} : get the "id" historicData.
     *
     * @param id the id of the historicDataDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historicDataDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/historic-data/{id}")
    public ResponseEntity<HistoricDataDTO> getHistoricData(@PathVariable Long id) {
        log.debug("REST request to get HistoricData : {}", id);
        Optional<HistoricDataDTO> historicDataDTO = historicDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(historicDataDTO);
    }

    /**
     * {@code DELETE  /historic-data/:id} : delete the "id" historicData.
     *
     * @param id the id of the historicDataDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/historic-data/{id}")
    public ResponseEntity<Void> deleteHistoricData(@PathVariable Long id) {
        log.debug("REST request to delete HistoricData : {}", id);
        historicDataService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
