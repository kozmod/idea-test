package ru.idea.test.spring.core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.idea.test.spring.core.component.simple.AnyInjectableStringDelegateBean;
import ru.idea.test.spring.core.conf.AnyConfig;
import ru.idea.test.spring.core.conf.AnyScanConfig;
import ru.idea.test.spring.core.entity.AnyBean;

public class SpringContextTest {

    @Test
    public void shouldLoadAnyConfigAndGetFoo() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnyConfig.class);

        AnyBean anyBean =  applicationContext.getBean(AnyBean.class);
        anyBean.addPrefix("FOO");
    }

    @Test
    public void shouldLoadAnyScanConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnyScanConfig.class);

        AnyInjectableStringDelegateBean delegate =  applicationContext.getBean(AnyInjectableStringDelegateBean.class);
        delegate.execute("FOO");
    }
}
