package ru.idea.test.spring.core.postprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class AnyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static Logger LOGGER = LoggerFactory.getLogger(AnyBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        LOGGER.warn("BF: " + beanFactory);
    }
}
