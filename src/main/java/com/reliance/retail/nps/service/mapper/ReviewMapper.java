package com.reliance.retail.nps.service.mapper;

import com.reliance.retail.nps.domain.Review;
import com.reliance.retail.nps.service.dto.ReviewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Review} and its DTO {@link ReviewDTO}.
 */
@Mapper(componentModel = "spring")
public interface ReviewMapper extends EntityMapper<ReviewDTO, Review> {}
