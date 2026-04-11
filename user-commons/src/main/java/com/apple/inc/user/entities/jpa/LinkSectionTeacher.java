package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="tbl_link_section_teacher")
public class LinkSectionTeacher extends AuditableEntity {

    @OneToOne
    private User teacher;

    @OneToOne
    private Section section;

    private Boolean isClassTeacher;
}
