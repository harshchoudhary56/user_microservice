package com.apple.inc.user.database.mongodb;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Programmatic Liquibase runner for MongoDB in the user microservice.
 *
 * <p>Spring Boot's auto-configured Liquibase only supports JDBC datasources.
 * This config runs Liquibase manually using the {@code liquibase-mongodb}
 * extension which connects via the MongoDB sync driver URI.</p>
 *
 * <p>The MySQL Liquibase migrations continue to run via Spring Boot's
 * standard auto-configuration — this config is only for MongoDB.</p>
 */
@Slf4j
@Configuration
public class LiquibaseMongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public CommandLineRunner liquibaseMongoRunner() {
        return args -> {
            log.info("Running Liquibase MongoDB migrations...");
            try {
                Database database = DatabaseFactory.getInstance()
                        .openDatabase(mongoUri, null, null, null, new ClassLoaderResourceAccessor());

                try (Liquibase liquibase = new Liquibase(
                        "db/changelog/mongo/mongo-master-changelog.xml",
                        new ClassLoaderResourceAccessor(),
                        database)) {
                    liquibase.update("");
                }

                log.info("Liquibase MongoDB migrations completed successfully.");
            } catch (Exception e) {
                log.error("Liquibase MongoDB migration failed", e);
                throw new RuntimeException("Liquibase MongoDB migration failed", e);
            }
        };
    }
}

