package com.reliance.retail.nps.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.reliance.retail.nps.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserCampaignDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCampaignDTO.class);
        UserCampaignDTO userCampaignDTO1 = new UserCampaignDTO();
        userCampaignDTO1.setId(1L);
        UserCampaignDTO userCampaignDTO2 = new UserCampaignDTO();
        assertThat(userCampaignDTO1).isNotEqualTo(userCampaignDTO2);
        userCampaignDTO2.setId(userCampaignDTO1.getId());
        assertThat(userCampaignDTO1).isEqualTo(userCampaignDTO2);
        userCampaignDTO2.setId(2L);
        assertThat(userCampaignDTO1).isNotEqualTo(userCampaignDTO2);
        userCampaignDTO1.setId(null);
        assertThat(userCampaignDTO1).isNotEqualTo(userCampaignDTO2);
    }
}
