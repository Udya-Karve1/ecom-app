package com.sk.rk.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.sk.rk.repository.subscription.VendorSubscriptionDatasourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.sk.rk.repository.subscription", entityManagerFactoryRef = "VendorSubscription", transactionManagerRef = "transactionManager")
@EnableConfigurationProperties(VendorSubscriptionDatasourceProperties.class)
public class SubscriptionConfig {
    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Autowired
    private VendorSubscriptionDatasourceProperties orderDatasourceProperties;

    @Bean(name = "orderDataSource", initMethod = "init", destroyMethod = "close")
    public DataSource orderDataSource() {
        MysqlXADataSource h2XaDataSource = new MysqlXADataSource();
        h2XaDataSource.setURL(orderDatasourceProperties.getUrl());
        h2XaDataSource.setPassword(orderDatasourceProperties.getPassword());
        h2XaDataSource.setUser(orderDatasourceProperties.getUsername());
        h2XaDataSource.setUseSSL(false);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(h2XaDataSource);
        xaDataSource.setUniqueResourceName("xads2");
        return xaDataSource;
    }

    @Bean(name = "orderEntityManager")
    public LocalContainerEntityManagerFactoryBean orderEntityManager() throws Throwable {

        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(orderDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.example.domain.order");
        entityManager.setPersistenceUnitName("orderPersistenceUnit");
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }
}
