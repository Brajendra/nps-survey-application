package com.reliance.retail.nps.service.mapper;

import com.reliance.retail.nps.domain.Customer;
import com.reliance.retail.nps.domain.Review;
import com.reliance.retail.nps.service.dto.CustomerDTO;
import com.reliance.retail.nps.service.dto.ReviewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "reviewId")
    CustomerDTO toDto(Customer s);

    @Named("reviewId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ReviewDTO toDtoReviewId(Review review);
}
