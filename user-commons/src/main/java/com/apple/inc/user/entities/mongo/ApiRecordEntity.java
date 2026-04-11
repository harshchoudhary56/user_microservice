package com.apple.inc.user.entities.mongo;

import com.apple.inc.user.entities.jpa.AuditableEntity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Table(name = "tbl_api_record")
@EqualsAndHashCode(callSuper = true)
public class ApiRecordEntity extends AuditableEntity {

    private String crid;

    private String requestId;

    private String requestApi;

    private String request;

    private String response;

    private String httpStatusCode;

    private String environment;
}
