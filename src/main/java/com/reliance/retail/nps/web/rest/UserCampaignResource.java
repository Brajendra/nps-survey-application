package com.reliance.retail.nps.web.rest;

import com.reliance.retail.nps.repository.UserCampaignRepository;
import com.reliance.retail.nps.service.UserCampaignService;
import com.reliance.retail.nps.service.dto.UserCampaignDTO;
import com.reliance.retail.nps.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
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

/**
 * REST controller for managing {@link com.reliance.retail.nps.domain.UserCampaign}.
 */
@RestController
@RequestMapping("/api")
public class UserCampaignResource {

    private final Logger log = LoggerFactory.getLogger(UserCampaignResource.class);

    private static final String ENTITY_NAME = "userCampaign";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserCampaignService userCampaignService;

    private final UserCampaignRepository userCampaignRepository;

    public UserCampaignResource(UserCampaignService userCampaignService, UserCampaignRepository userCampaignRepository) {
        this.userCampaignService = userCampaignService;
        this.userCampaignRepository = userCampaignRepository;
    }

    /**
     * {@code POST  /user-campaigns} : Create a new userCampaign.
     *
     * @param userCampaignDTO the userCampaignDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userCampaignDTO, or with status {@code 400 (Bad Request)} if the userCampaign has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-campaigns")
    public ResponseEntity<UserCampaignDTO> createUserCampaign(@Valid @RequestBody UserCampaignDTO userCampaignDTO)
        throws URISyntaxException {
        log.debug("REST request to save UserCampaign : {}", userCampaignDTO);
        if (userCampaignDTO.getId() != null) {
            throw new BadRequestAlertException("A new userCampaign cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserCampaignDTO result = userCampaignService.save(userCampaignDTO);
        return ResponseEntity
            .created(new URI("/api/user-campaigns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-campaigns/:id} : Updates an existing userCampaign.
     *
     * @param id the id of the userCampaignDTO to save.
     * @param userCampaignDTO the userCampaignDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userCampaignDTO,
     * or with status {@code 400 (Bad Request)} if the userCampaignDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userCampaignDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-campaigns/{id}")
    public ResponseEntity<UserCampaignDTO> updateUserCampaign(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody UserCampaignDTO userCampaignDTO
    ) throws URISyntaxException {
        log.debug("REST request to update UserCampaign : {}, {}", id, userCampaignDTO);
        if (userCampaignDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userCampaignDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userCampaignRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UserCampaignDTO result = userCampaignService.update(userCampaignDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userCampaignDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-campaigns/:id} : Partial updates given fields of an existing userCampaign, field will ignore if it is null
     *
     * @param id the id of the userCampaignDTO to save.
     * @param userCampaignDTO the userCampaignDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userCampaignDTO,
     * or with status {@code 400 (Bad Request)} if the userCampaignDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userCampaignDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userCampaignDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-campaigns/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserCampaignDTO> partialUpdateUserCampaign(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody UserCampaignDTO userCampaignDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserCampaign partially : {}, {}", id, userCampaignDTO);
        if (userCampaignDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userCampaignDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userCampaignRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserCampaignDTO> result = userCampaignService.partialUpdate(userCampaignDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userCampaignDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /user-campaigns} : get all the userCampaigns.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userCampaigns in body.
     */
    @GetMapping("/user-campaigns")
    public ResponseEntity<List<UserCampaignDTO>> getAllUserCampaigns(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false) String filter
    ) {

        log.debug("REST request to get a page of UserCampaigns");
        Page<UserCampaignDTO> page = userCampaignService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-campaigns/:id} : get the "id" userCampaign.
     *
     * @param id the id of the userCampaignDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userCampaignDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-campaigns/{id}")
    public ResponseEntity<UserCampaignDTO> getUserCampaign(@PathVariable Long id) {
        log.debug("REST request to get UserCampaign : {}", id);
        Optional<UserCampaignDTO> userCampaignDTO = userCampaignService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userCampaignDTO);
    }

    /**
     * {@code DELETE  /user-campaigns/:id} : delete the "id" userCampaign.
     *
     * @param id the id of the userCampaignDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-campaigns/{id}")
    public ResponseEntity<Void> deleteUserCampaign(@PathVariable Long id) {
        log.debug("REST request to delete UserCampaign : {}", id);
        userCampaignService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
