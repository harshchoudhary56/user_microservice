package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_book")
public class Book extends AuditableEntity {

    private String title;
    private String publication;

    @Column(unique = true, nullable = false)
    private String isbn;
}
