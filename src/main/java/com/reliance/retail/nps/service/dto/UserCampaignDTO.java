package com.reliance.retail.nps.service.dto;

import com.reliance.retail.nps.domain.CampaignLink;

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
    private String code;

    private Integer attemptQuestionCount;

    private String eventId;

    private String eventType;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    public CampaignLink getCampaignLink() {
        return campaignLink;
    }

    public void setCampaignLink(CampaignLink campaignLink) {
        this.campaignLink = campaignLink;
    }

    private CampaignLink campaignLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
            ", code='" + getCode() + "'" +
            ", attemptQuestionCount=" + getAttemptQuestionCount() +
            ", eventId='" + getEventId() + "'" +
            ", eventType='" + getEventType() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
