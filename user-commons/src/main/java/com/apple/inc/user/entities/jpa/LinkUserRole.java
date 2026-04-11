package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_link_user_role")
public class LinkUserRole extends AuditableEntity {

    @OneToOne
    private User user;

    @OneToOne
    private Role role;
}
