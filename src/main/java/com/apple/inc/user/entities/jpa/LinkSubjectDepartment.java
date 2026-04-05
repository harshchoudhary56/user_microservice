package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_link_subject_department")
public class LinkSubjectDepartment extends AuditableEntity {

    @OneToOne
    private Subject subject;

    @OneToOne
    private Department department;
}
