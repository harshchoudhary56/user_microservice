package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_link_subject_department")
public class LinkSubjectDepartment extends AuditableEntity {
    @OneToOne
    private Subject subject;
    private Department department;
}
