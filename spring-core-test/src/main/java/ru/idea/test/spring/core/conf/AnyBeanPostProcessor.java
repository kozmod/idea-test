package ru.idea.test.spring.core.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class AnyBeanPostProcessor implements BeanPostProcessor {

    private static Logger LOGGER = LoggerFactory.getLogger(AnyBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.warn("Before init bean: " + beanName);
        return bean;
    }
}
