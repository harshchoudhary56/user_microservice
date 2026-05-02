package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_link_section_subject_book")
public class LinkSectionSubjectBook extends AuditableEntity {
    @OneToOne
    private Section section;
    private Subject subject;
    private Book book;
}
