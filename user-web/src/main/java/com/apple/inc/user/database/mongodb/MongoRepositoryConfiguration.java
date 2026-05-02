package com.apple.inc.user.database.mongodb;

import com.apple.inc.user.constants.BeanConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableMongoRepositories(
        mongoTemplateRef = BeanConstants.MONGO_TEMPLATE,
        basePackages = "com.apple.inc.user.repository.mongo"
)
public class MongoRepositoryConfiguration {
}
