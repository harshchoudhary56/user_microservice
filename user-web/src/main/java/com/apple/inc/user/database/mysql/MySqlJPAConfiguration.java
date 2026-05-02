package com.apple.inc.user.database.mysql;

import com.apple.inc.user.constants.BeanConstants;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = BeanConstants.MYSQL_ENTITY_MANAGER_FACTORY,
        transactionManagerRef = BeanConstants.MYSQL_TRANSACTION_MANAGER,
        basePackages = "com.apple.inc.user.repository.mysql"
)
public class MySqlJPAConfiguration {

    @Bean(name = BeanConstants.MYSQL_ENTITY_MANAGER_FACTORY)
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                                                                         @Qualifier(BeanConstants.MYSQL_DATASOURCE) DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.apple.inc.user.entities.jpa")
                .build();
    }

    @Bean(name = BeanConstants.MYSQL_TRANSACTION_MANAGER)
    PlatformTransactionManager transactionManager(@Qualifier(BeanConstants.MYSQL_ENTITY_MANAGER_FACTORY) LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        assert entityManagerFactory.getObject() != null;
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
