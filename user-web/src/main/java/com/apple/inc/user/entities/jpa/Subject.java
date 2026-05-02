package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.Table;
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_subject")
public class Subject extends AuditableEntity {
    private String name;
    private String code;
    private String description;
}
