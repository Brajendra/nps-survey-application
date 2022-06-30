package com.reliance.retail.nps.service.mapper;

import com.reliance.retail.nps.domain.Campaign;
import com.reliance.retail.nps.domain.Link;
import com.reliance.retail.nps.service.dto.CampaignDTO;
import com.reliance.retail.nps.service.dto.LinkDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Link} and its DTO {@link LinkDTO}.
 */
@Mapper(componentModel = "spring")
public interface LinkMapper extends EntityMapper<LinkDTO, Link> {
    @Mapping(target = "campaign", source = "campaign", qualifiedByName = "campaignId")
    LinkDTO toDto(Link s);

    @Named("campaignId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CampaignDTO toDtoCampaignId(Campaign campaign);
}
