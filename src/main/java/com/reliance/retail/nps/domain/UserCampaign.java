package com.reliance.retail.nps.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UserCampaign.
 */
@Entity
@Table(name = "user_campaign")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserCampaign implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "attempt_question_count")
    private Integer attemptQuestionCount;

    @Column(name = "event_id")
    private String eventId;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @JsonIgnoreProperties(value = { "questions", "userCampaign" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Campaign campaign;

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

    public String getUserId() {
        return this.userId;
    }

    public UserCampaign userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Campaign getCampaign() {
        return this.campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public UserCampaign campaign(Campaign campaign) {
        this.setCampaign(campaign);
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
            ", userId='" + getUserId() + "'" +
            ", attemptQuestionCount=" + getAttemptQuestionCount() +
            ", eventId='" + getEventId() + "'" +
            ", eventType='" + getEventType() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
