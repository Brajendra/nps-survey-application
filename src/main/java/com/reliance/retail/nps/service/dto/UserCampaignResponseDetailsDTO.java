package com.reliance.retail.nps.service.dto;

import java.util.List;

public class UserCampaignResponseDetailsDTO {

    private String code;
    private Integer attemptQuestionCount;
    List<UserAnswersDTO> userAnswers;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getAttemptQuestionCount() {
        return attemptQuestionCount;
    }

    public void setAttemptQuestionCount(Integer attemptQuestionCount) {
        this.attemptQuestionCount = attemptQuestionCount;
    }


    public List<UserAnswersDTO> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswersDTO> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
