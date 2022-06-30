package com.reliance.retail.nps.repository;

import com.reliance.retail.nps.domain.Question;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Question entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {


    Optional<List<Question>> findByCampaignId(final Long campaignId);

}
