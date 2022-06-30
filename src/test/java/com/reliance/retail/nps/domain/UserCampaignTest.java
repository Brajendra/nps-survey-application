package com.reliance.retail.nps.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.reliance.retail.nps.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserCampaignTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCampaign.class);
        UserCampaign userCampaign1 = new UserCampaign();
        userCampaign1.setId(1L);
        UserCampaign userCampaign2 = new UserCampaign();
        userCampaign2.setId(userCampaign1.getId());
        assertThat(userCampaign1).isEqualTo(userCampaign2);
        userCampaign2.setId(2L);
        assertThat(userCampaign1).isNotEqualTo(userCampaign2);
        userCampaign1.setId(null);
        assertThat(userCampaign1).isNotEqualTo(userCampaign2);
    }
}
