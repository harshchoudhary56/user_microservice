package com.apple.inc.user.services;

import com.apple.inc.user.entities.mongo.ApiRecordEntity;

import java.util.Optional;

public interface IApiRecordEntityDao {

    ApiRecordEntity save(ApiRecordEntity apiRecordEntity);

    ApiRecordEntity update(ApiRecordEntity apiRecordEntity);

    Optional<ApiRecordEntity> find(String crid);
}
