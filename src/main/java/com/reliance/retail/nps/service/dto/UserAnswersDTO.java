package com.reliance.retail.nps.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.reliance.retail.nps.domain.UserAnswers} entity.
 */
public class UserAnswersDTO implements Serializable {

    private Long id;

    private String answers;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private QuestionDTO question;

    private UserCampaignDTO userCampaign;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }

    public UserCampaignDTO getUserCampaign() {
        return userCampaign;
    }

    public void setUserCampaign(UserCampaignDTO userCampaign) {
        this.userCampaign = userCampaign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserAnswersDTO)) {
            return false;
        }

        UserAnswersDTO userAnswersDTO = (UserAnswersDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userAnswersDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserAnswersDTO{" +
            "id=" + getId() +
            ", answers='" + getAnswers() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", question=" + getQuestion() +
            ", userCampaign=" + getUserCampaign() +
            "}";
    }
}
