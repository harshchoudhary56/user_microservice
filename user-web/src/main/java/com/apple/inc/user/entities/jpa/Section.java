package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_section")
public class Section extends AuditableEntity {
    private String name;
    @OneToOne
    private Class aClass;
}
