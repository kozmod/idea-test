package ru.idea.test.spring.core;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.idea.test.spring.core.component.aspect.annotation.AnnotatedBean;
import ru.idea.test.spring.core.component.aspect.annotation.AnyAnnotatedBean;
import ru.idea.test.spring.core.component.aspect.annotation.AnyAnnotatedComponent;
import ru.idea.test.spring.core.component.aspect.simple.AnyBean;
import ru.idea.test.spring.core.component.simple.AnyInjectableStringDelegateBean;
import ru.idea.test.spring.core.conf.AnyAopConfig;
import ru.idea.test.spring.core.conf.AnyPostProcessorConfig;
import ru.idea.test.spring.core.conf.AnyScanConfig;

public class SpringProxyTest {

    @Test
    public void shouldLoadAnyAopConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnyAopConfig.class);

        AnyAnnotatedBean anyBean =  applicationContext.getBean(AnyAnnotatedBean.class);
        anyBean.method1();

        AnnotatedBean anyAnnotatedComponent =  applicationContext.getBean(AnnotatedBean.class);
        anyAnnotatedComponent.method1();
    }
}
