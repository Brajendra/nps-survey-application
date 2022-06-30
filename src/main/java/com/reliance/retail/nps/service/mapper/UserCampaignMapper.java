package com.reliance.retail.nps.service.mapper;

import com.reliance.retail.nps.domain.Campaign;
import com.reliance.retail.nps.domain.UserCampaign;
import com.reliance.retail.nps.service.dto.CampaignDTO;
import com.reliance.retail.nps.service.dto.UserCampaignDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserCampaign} and its DTO {@link UserCampaignDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserCampaignMapper extends EntityMapper<UserCampaignDTO, UserCampaign> {
    @Mapping(target = "campaign", source = "campaign", qualifiedByName = "campaignId")
    UserCampaignDTO toDto(UserCampaign s);

    @Named("campaignId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CampaignDTO toDtoCampaignId(Campaign campaign);
}
