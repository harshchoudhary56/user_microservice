package com.apple.inc.user.services.impl;

import com.apple.inc.user.entities.mongo.ApiRecordEntity;
import com.apple.inc.user.repository.mongo.ApiRecordRepository;
import com.apple.inc.user.services.IApiRecordEntityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiRecordEntityDao implements IApiRecordEntityDao {

    private final ApiRecordRepository repository;

    @Override
    public ApiRecordEntity save(ApiRecordEntity apiRecordEntity) {
        return repository.save(apiRecordEntity);
    }

    @Override
    public ApiRecordEntity update(ApiRecordEntity apiRecordEntity) {
        return repository.save(apiRecordEntity);
    }

    @Override
    public Optional<ApiRecordEntity> find(String crid) {
        return repository.findByCridId(crid);
    }
}
