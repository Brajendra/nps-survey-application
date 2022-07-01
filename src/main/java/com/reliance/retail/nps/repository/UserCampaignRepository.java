package com.reliance.retail.nps.repository;

import com.reliance.retail.nps.domain.UserCampaign;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the UserCampaign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserCampaignRepository extends JpaRepository<UserCampaign, Long> {

    Optional<Boolean> existsByCode(final String code);
}
