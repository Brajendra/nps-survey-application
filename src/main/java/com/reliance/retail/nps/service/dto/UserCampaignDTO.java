package com.reliance.retail.nps.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.reliance.retail.nps.domain.UserCampaign} entity.
 */
public class UserCampaignDTO implements Serializable {

    private Long id;

    @NotNull
    private String userId;

    private Integer attemptQuestionCount;

    private String eventId;

    private String eventType;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private CampaignDTO campaign;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAttemptQuestionCount() {
        return attemptQuestionCount;
    }

    public void setAttemptQuestionCount(Integer attemptQuestionCount) {
        this.attemptQuestionCount = attemptQuestionCount;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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

    public CampaignDTO getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignDTO campaign) {
        this.campaign = campaign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCampaignDTO)) {
            return false;
        }

        UserCampaignDTO userCampaignDTO = (UserCampaignDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userCampaignDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserCampaignDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", attemptQuestionCount=" + getAttemptQuestionCount() +
            ", eventId='" + getEventId() + "'" +
            ", eventType='" + getEventType() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", campaign=" + getCampaign() +
            "}";
    }
}
