package com.apple.inc.user.entities.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.apple.inc.user.util.date.DateUtils.now;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class AuditableEntity extends BaseEntity {

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "updated_date")
    private String updatedDate;

    @PrePersist
    public void onPersist() {
        setCreatedDate(now());
        setUpdatedDate(now());
    }

    @PreUpdate
    public void onUpdate() {
        this.setUpdatedDate(now());
    }
}
