package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_permission")
public class Permission extends AuditableEntity {

    private String name;
    private String description;
}
