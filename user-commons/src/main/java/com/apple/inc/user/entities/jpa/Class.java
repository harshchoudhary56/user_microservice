package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_class")
public class Class extends AuditableEntity {

    @OneToOne
    private Department department;

    private String name;

    private String academicYear;
}
