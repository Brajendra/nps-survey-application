package com.reliance.retail.nps.service;

import com.reliance.retail.nps.service.dto.LinkDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.reliance.retail.nps.domain.Link}.
 */
public interface LinkService {
    /**
     * Save a link.
     *
     * @param linkDTO the entity to save.
     * @return the persisted entity.
     */
    LinkDTO save(LinkDTO linkDTO);

    /**
     * Updates a link.
     *
     * @param linkDTO the entity to update.
     * @return the persisted entity.
     */
    LinkDTO update(LinkDTO linkDTO);

    /**
     * Partially updates a link.
     *
     * @param linkDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LinkDTO> partialUpdate(LinkDTO linkDTO);

    /**
     * Get all the links.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LinkDTO> findAll(Pageable pageable);

    /**
     * Get the "id" link.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LinkDTO> findOne(Long id);

    /**
     * Delete the "id" link.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
