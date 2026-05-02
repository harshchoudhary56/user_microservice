package com.apple.inc.user.entities.mongo;

import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.apple.inc.user.util.date.DateUtils.now;

@Data
public abstract class MongoBaseEntity {

    @Id
    private String id;

    private String createdDate;

    private String updatedDate;

    @PrePersist
    public void onPersist() {
        setCreatedDate(now());
        setUpdatedDate(now());
    }

    @PostUpdate
    public void onUpdate() {
        setUpdatedDate(now());
    }
}

