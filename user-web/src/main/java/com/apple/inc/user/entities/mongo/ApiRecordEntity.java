package com.apple.inc.user.entities.mongo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tbl_api_record")
@EqualsAndHashCode(callSuper = true)
public class ApiRecordEntity extends MongoBaseEntity {

    private String crid;

    private String requestId;

    private String requestApi;

    private String request;

    private String response;

    private String httpStatusCode;

    private String environment;
}
