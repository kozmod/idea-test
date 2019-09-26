package ru.idea.test.spring.core.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.idea.test.spring.core.bean.AnyAspect;
import ru.idea.test.spring.core.entity.AnyBean;

@Configuration
@EnableAspectJAutoProxy
public class AnyConfig {

    @Bean
    public AnyBean anyBean() {
        return new AnyBean().setValue("test value");
    }

    @Bean
    public AnyBeanPostProcessor anyPostProcessor() {
        return new AnyBeanPostProcessor();
    }

    @Bean
    public AnyBeanFactoryPostProcessor anyBeanFactoryPostProcessor() {
        return new AnyBeanFactoryPostProcessor();
    }

    @Bean
    public AnyAspect anyAspect() {
        return new AnyAspect();
    }
}
