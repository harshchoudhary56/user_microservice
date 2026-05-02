package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_class")
public class Class extends AuditableEntity {
    @OneToOne
    private Department department;
    private String name;
    private String academicYear;
}
