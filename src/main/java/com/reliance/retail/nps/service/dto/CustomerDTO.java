package com.reliance.retail.nps.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.reliance.retail.nps.domain.Customer} entity.
 */
public class CustomerDTO implements Serializable {

    private Long id;

    private String firstName;

    private ReviewDTO customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ReviewDTO getCustomer() {
        return customer;
    }

    public void setCustomer(ReviewDTO customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerDTO)) {
            return false;
        }

        CustomerDTO customerDTO = (CustomerDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, customerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", customer=" + getCustomer() +
            "}";
    }
}
