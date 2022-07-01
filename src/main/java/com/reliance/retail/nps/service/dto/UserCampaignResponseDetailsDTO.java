package com.reliance.retail.nps.service.dto;

import java.util.List;

public class UserCampaignResponseDetailsDTO {




    UserCampaignDTO userCampaign;
    List<UserAnswersDTO> userAnswers;

    public UserCampaignDTO getUserCampaign() {
        return userCampaign;
    }

    public void setUserCampaign(UserCampaignDTO userCampaign) {
        this.userCampaign = userCampaign;
    }

    public List<UserAnswersDTO> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswersDTO> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
