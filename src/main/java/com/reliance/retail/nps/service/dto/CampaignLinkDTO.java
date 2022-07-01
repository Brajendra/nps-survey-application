package com.reliance.retail.nps.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.reliance.retail.nps.domain.CampaignLink} entity.
 */
public class CampaignLinkDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    private String userInfo;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private UserCampaignDTO userCampaign;

    private CampaignDTO campaign;

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

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
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

    public UserCampaignDTO getUserCampaign() {
        return userCampaign;
    }

    public void setUserCampaign(UserCampaignDTO userCampaign) {
        this.userCampaign = userCampaign;
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
        if (!(o instanceof CampaignLinkDTO)) {
            return false;
        }

        CampaignLinkDTO campaignLinkDTO = (CampaignLinkDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, campaignLinkDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CampaignLinkDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", userInfo='" + getUserInfo() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", userCampaign=" + getUserCampaign() +
            ", campaign=" + getCampaign() +
            "}";
    }
}
