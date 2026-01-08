package com.apple.inc.user.entities;

import com.apple.inc.user.constants.RelationShipType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name  = "tbl_link_parent_student")
public class LinkParentStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User parent;

    @OneToOne
    private User student;

    @Enumerated(EnumType.STRING)
    private RelationShipType relationShipType;
}
