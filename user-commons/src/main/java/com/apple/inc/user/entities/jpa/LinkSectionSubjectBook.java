package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_link_section_subject_ book")
public class LinkSectionSubjectBook extends AuditableEntity {

    @OneToOne
    private Section section;

    @OneToOne
    private Subject subject;

    @OneToOne
    private Book book;
}
