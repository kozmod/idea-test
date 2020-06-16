package ru.idea.test.spring.core.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.idea.test.spring.core.aspect.AnyAspect;
import ru.idea.test.spring.core.aspect.anotation.AnyAnnotationAspect;
import ru.idea.test.spring.core.component.aspect.annotation.AnyAnnotatedBean;
import ru.idea.test.spring.core.component.aspect.simple.AnyBean;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false)
@ComponentScan("ru.idea.test.spring.core.component.aspect.annotation")
public class AnyAopConfig {

    @Bean
    public AnyBean anyBean() {
        return new AnyBean().setValue("test value");
    }

    @Bean
    public AnyAnnotatedBean anyAnnotatedBean() {
        return new AnyAnnotatedBean();
    }

    @Bean
    public AnyAspect anyAspect() {
        return new AnyAspect();
    }

    @Bean
    public AnyAnnotationAspect anyAnnotationAspect() {
        return new AnyAnnotationAspect();
    }
}
