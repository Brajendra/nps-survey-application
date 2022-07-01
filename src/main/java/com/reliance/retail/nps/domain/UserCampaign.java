package com.reliance.retail.nps.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * A UserCampaign.
 */
@Entity
@Table(name = "user_campaign")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EntityListeners(AuditingEntityListener.class)
public class UserCampaign implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "attempt_question_count")
    private Integer attemptQuestionCount;

    @Column(name = "event_id")
    private String eventId;

    @Column(name = "event_type")
    private String eventType;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDate createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @JsonIgnoreProperties(value = { "userCampaign", "campaign" }, allowSetters = true)
    @OneToOne(mappedBy = "userCampaign")
    private CampaignLink campaignLink;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserCampaign id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public UserCampaign code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getAttemptQuestionCount() {
        return this.attemptQuestionCount;
    }

    public UserCampaign attemptQuestionCount(Integer attemptQuestionCount) {
        this.setAttemptQuestionCount(attemptQuestionCount);
        return this;
    }

    public void setAttemptQuestionCount(Integer attemptQuestionCount) {
        this.attemptQuestionCount = attemptQuestionCount;
    }

    public String getEventId() {
        return this.eventId;
    }

    public UserCampaign eventId(String eventId) {
        this.setEventId(eventId);
        return this;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return this.eventType;
    }

    public UserCampaign eventType(String eventType) {
        this.setEventType(eventType);
        return this;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public UserCampaign createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public UserCampaign updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CampaignLink getCampaignLink() {
        return this.campaignLink;
    }

    public void setCampaignLink(CampaignLink campaignLink) {
        if (this.campaignLink != null) {
            this.campaignLink.setUserCampaign(null);
        }
        if (campaignLink != null) {
            campaignLink.setUserCampaign(this);
        }
        this.campaignLink = campaignLink;
    }

    public UserCampaign campaignLink(CampaignLink campaignLink) {
        this.setCampaignLink(campaignLink);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCampaign)) {
            return false;
        }
        return id != null && id.equals(((UserCampaign) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserCampaign{" +
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
