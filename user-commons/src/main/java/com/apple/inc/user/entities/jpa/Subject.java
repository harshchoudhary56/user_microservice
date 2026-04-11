package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_subject")
public class Subject extends AuditableEntity {

    private Long id;
    private String name;
    private String code;
    private String description;
}
