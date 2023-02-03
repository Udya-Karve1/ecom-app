package com.sk.rk.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.sk.rk.repository.vendor"
        , entityManagerFactoryRef = "vendorEntityManagerFactory"
        , transactionManagerRef = "vendorSqlPlatformTransactionManager")
public class VendorConfig {
    @Primary
    @Bean
    @ConfigurationProperties("vendor.datasource")
    public DataSourceProperties vendorDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource vendorSqlDataSource(@Qualifier("vendorDataSourceProperties")
                                              DataSourceProperties vendorDataSourceProperties) {
        return vendorDataSourceProperties
                .initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean vendorEntityManagerFactory(
            @Qualifier("vendorSqlDataSource") DataSource pgsqlDataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(pgsqlDataSource)
                .packages("com.sk.rk.repository.vendor")
                .persistenceUnit("vendorSql")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager vendorSqlPlatformTransactionManager(
            @Qualifier("vendorEntityManagerFactory") EntityManagerFactory vendorEntityManagerFactory) {
        return new JpaTransactionManager(vendorEntityManagerFactory);
    }
}
