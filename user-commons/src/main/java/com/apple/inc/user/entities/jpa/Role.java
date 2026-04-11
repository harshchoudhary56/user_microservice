package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_role")
public class Role extends AuditableEntity {

    private String name;
    private String description;
}
