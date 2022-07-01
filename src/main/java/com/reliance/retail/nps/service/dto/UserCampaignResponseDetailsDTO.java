package com.reliance.retail.nps.service.dto;

import java.util.List;

public class UserCampaignResponseDetailsDTO {


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public Integer getAttemptQuestionCount() {
        return attemptQuestionCount;
    }

    public void setAttemptQuestionCount(Integer attemptQuestionCount) {
        this.attemptQuestionCount = attemptQuestionCount;
    }

    private Integer attemptQuestionCount;
    private List<UserAnswersDTO> userAnswers;

    public List<UserAnswersDTO> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswersDTO> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
