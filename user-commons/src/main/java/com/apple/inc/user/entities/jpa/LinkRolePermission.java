package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_link_role_permission")
public class LinkRolePermission extends AuditableEntity {

    @OneToOne
    private Role role;

    @OneToOne
    private Permission permission;
}
