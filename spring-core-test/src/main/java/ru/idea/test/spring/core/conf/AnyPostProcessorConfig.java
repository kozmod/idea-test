package ru.idea.test.spring.core.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.idea.test.spring.core.aspect.AnyAspect;
import ru.idea.test.spring.core.component.aspect.simple.AnyBean;
import ru.idea.test.spring.core.postprocessor.AnyBeanFactoryPostProcessor;
import ru.idea.test.spring.core.postprocessor.AnyBeanPostProcessor;

@Configuration
@EnableAspectJAutoProxy
public class AnyPostProcessorConfig {

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
