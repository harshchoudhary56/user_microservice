package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_session")
public class Session extends AuditableEntity {

    private String accessToken;
    private String refreshToken;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
    private String deviceInfo;

    @OneToOne
    private User user;
}
