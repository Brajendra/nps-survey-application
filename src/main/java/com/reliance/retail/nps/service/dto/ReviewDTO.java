package com.reliance.retail.nps.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.reliance.retail.nps.domain.Review} entity.
 */
public class ReviewDTO implements Serializable {

    private Long id;

    private Integer rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReviewDTO)) {
            return false;
        }

        ReviewDTO reviewDTO = (ReviewDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, reviewDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReviewDTO{" +
            "id=" + getId() +
            ", rating=" + getRating() +
            "}";
    }
}
