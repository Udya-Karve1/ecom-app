package com.sk.rk.config;


import java.util.HashMap;

import javax.sql.DataSource;


import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.sk.rk.repository.vendor.VendorDataSourceProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.sk.rk.repository.vendor", entityManagerFactoryRef = "Vendor", transactionManagerRef = "transactionManager")
@EnableConfigurationProperties(VendorDataSourceProperty.class)
public class VendorConfig {
    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Autowired
    private VendorDataSourceProperty vendorDataSourceProperty;

    @Bean(name = "customerDataSource", initMethod = "init", destroyMethod = "close")
    public DataSource customerDataSource() {
        MysqlXADataSource h2XaDataSource = new MysqlXADataSource();

        h2XaDataSource.setURL(vendorDataSourceProperty.getUrl());
        h2XaDataSource.setUser(vendorDataSourceProperty.getUsername());
        h2XaDataSource.setPassword(vendorDataSourceProperty.getPassword());
        h2XaDataSource.setUseSSL(false);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(h2XaDataSource);
        xaDataSource.setUniqueResourceName("xads1");
        return xaDataSource;
    }

    @Bean(name = "customerEntityManager")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean customerEntityManager() throws Throwable {

        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(customerDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.example.domain.customer");
        entityManager.setPersistenceUnitName("customerPersistenceUnit");
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }
}
