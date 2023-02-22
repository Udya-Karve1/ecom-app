package com.sk.rk.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class GroupedTransactionManager  {
    @Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager transactionManager(
            @Qualifier("vendorSqlPlatformTransactionManager")
                PlatformTransactionManager vendorTransactionManager
            , @Qualifier("subscriptionSqlPlatformTransactionManager")
                PlatformTransactionManager subscriptionTransactionManager
    ){
        return new ChainedTransactionManager(vendorTransactionManager, subscriptionTransactionManager);
    }
}
