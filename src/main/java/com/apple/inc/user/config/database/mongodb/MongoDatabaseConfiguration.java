package com.apple.inc.user.config.database.mongodb;

import com.apple.inc.user.constants.BeanConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDatabaseConfiguration {

    @Bean(name = BeanConstants.MONGODB_DATASOURCE)
    public MongoDatabaseFactory mongoDatabaseFactory(MongoProperties properties) {
        return new SimpleMongoClientDatabaseFactory(properties.getUri());
    }

    @Bean(name = BeanConstants.MONGO_TEMPLATE)
    public MongoTemplate mongoTemplate(@Qualifier(BeanConstants.MONGODB_DATASOURCE) MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }

    @Bean(name = BeanConstants.MONGODB_TRANSACTION_MANAGER)
    public MongoTransactionManager transactionManager(@Qualifier(BeanConstants.MONGODB_DATASOURCE) MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }
}
