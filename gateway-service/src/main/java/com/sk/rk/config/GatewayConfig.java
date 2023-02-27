package com.sk.rk.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
@Configuration
public class GatewayConfig  implements ApplicationContextAware {

    public static ApplicationContext applicationContext = null;

    protected static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static Object getBean(Class<?> t) {
        return applicationContext.getBean(t);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (GatewayConfig.applicationContext == null) {
            GatewayConfig.applicationContext = applicationContext;
        }
    }
}
