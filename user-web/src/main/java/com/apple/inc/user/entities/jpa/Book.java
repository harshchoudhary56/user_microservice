package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Data
@Entity
@Table(name = "tbl_book")
@EqualsAndHashCode(callSuper = true)
public class Book extends AuditableEntity {

    private String title;
    private String publication;

    @Column(unique = true, nullable = false)
    private String isbn;
}
