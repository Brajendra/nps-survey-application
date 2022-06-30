package com.reliance.retail.nps.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserCampaignMapperTest {

    private UserCampaignMapper userCampaignMapper;

    @BeforeEach
    public void setUp() {
        userCampaignMapper = new UserCampaignMapperImpl();
    }
}
