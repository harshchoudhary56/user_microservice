package com.apple.inc.user.repository.mongo;

import com.apple.inc.user.entities.mongo.ApiRecordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiRecordRepository extends MongoRepository<ApiRecordEntity, Long> {

    @Query("{ 'cridId': ?0 }")
    Optional<ApiRecordEntity> findByCridId(String crid);

}
