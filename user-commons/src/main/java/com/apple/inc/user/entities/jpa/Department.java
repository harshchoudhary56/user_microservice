package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_department")
public class Department extends AuditableEntity {

    private String name;
    private String description;
}
