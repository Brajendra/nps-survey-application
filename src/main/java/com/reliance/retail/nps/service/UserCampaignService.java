package com.reliance.retail.nps.service;

import com.reliance.retail.nps.service.dto.UserCampaignDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.reliance.retail.nps.domain.UserCampaign}.
 */
public interface UserCampaignService {
    /**
     * Save a userCampaign.
     *
     * @param userCampaignDTO the entity to save.
     * @return the persisted entity.
     */
    UserCampaignDTO save(UserCampaignDTO userCampaignDTO);

    /**
     * Updates a userCampaign.
     *
     * @param userCampaignDTO the entity to update.
     * @return the persisted entity.
     */
    UserCampaignDTO update(UserCampaignDTO userCampaignDTO);

    /**
     * Partially updates a userCampaign.
     *
     * @param userCampaignDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserCampaignDTO> partialUpdate(UserCampaignDTO userCampaignDTO);

    /**
     * Get all the userCampaigns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserCampaignDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userCampaign.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserCampaignDTO> findOne(Long id);

    /**
     * Delete the "id" userCampaign.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
