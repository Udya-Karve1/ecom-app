package com.sk.rk.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateListenerConfig {

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    @PostConstruct
    protected void setup() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.appendListeners(EventType.PRE_INSERT, CommonDBListener.INSTANCE);
        registry.appendListeners(EventType.PRE_UPDATE, CommonDBListener.INSTANCE);
    }
}
