package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name ="tbl_link_section_teacher")
public class LinkSectionTeacher extends AuditableEntity {
    @OneToOne
    private User teacher;
    private Section section;
    private Boolean isClassTeacher;
}
