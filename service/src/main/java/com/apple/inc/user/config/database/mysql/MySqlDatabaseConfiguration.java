package com.apple.inc.user.config.database.mysql;

import com.apple.inc.user.constants.BeanConstants;
import com.apple.inc.user.constants.DatabaseConstants;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MySqlDatabaseConfiguration {

    @Bean(name = BeanConstants.MYSQL_DATASOURCE_PROPERTIES)
    @ConfigurationProperties(prefix = DatabaseConstants.MYSQL)
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = BeanConstants.MYSQL_DATASOURCE)
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
}
