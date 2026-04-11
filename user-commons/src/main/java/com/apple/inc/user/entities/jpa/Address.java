package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_address")
public class Address extends AuditableEntity {

    @OneToOne
    private User user;

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
