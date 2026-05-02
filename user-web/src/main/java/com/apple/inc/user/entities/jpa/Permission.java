package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.Table;
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_permission")
public class Permission extends AuditableEntity {
    private String name;
    private String description;
}
