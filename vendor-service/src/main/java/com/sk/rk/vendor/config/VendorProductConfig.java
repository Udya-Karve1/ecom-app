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
@EnableJpaRepositories(basePackages = "com.sk.rk.repository.product"
        , entityManagerFactoryRef = "vendorProductEntityManagerFactory"
        , transactionManagerRef = "vendorProductSqlPlatformTransactionManager")
public class VendorProductConfig {
    @Primary
    @Bean
    @ConfigurationProperties("vendor_product.datasource")
    public DataSourceProperties vendorProductDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource vendorProductSqlDataSource(@Qualifier("vendorProductDataSourceProperties")
                                          DataSourceProperties vendorProductDataSourceProperties) {
        return vendorProductDataSourceProperties
                .initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean vendorProductEntityManagerFactory(
            @Qualifier("vendorProductSqlDataSource") DataSource pgsqlDataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(pgsqlDataSource)
                .packages("com.sk.rk.repository.product")
                .persistenceUnit("vendorProductSql")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager vendorProductSqlPlatformTransactionManager(
            @Qualifier("vendorProductEntityManagerFactory") EntityManagerFactory vendorProductEntityManagerFactory) {
        return new JpaTransactionManager(vendorProductEntityManagerFactory);
    }
}
