package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "tbl_address")
@EqualsAndHashCode(callSuper = true)
public class Address extends AuditableEntity {

    @OneToOne
    private User user;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
