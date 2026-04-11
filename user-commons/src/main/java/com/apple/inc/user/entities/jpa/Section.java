package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_section")
public class Section extends AuditableEntity {

    private String name;

    @OneToOne
    private Class aClass;
}
