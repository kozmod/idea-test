package ru.idea.test.spring.core;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.idea.test.spring.core.conf.AnyConfig;
import ru.idea.test.spring.core.entity.AnyBean;

public class SpringContextTest {

    @Test
    public void shouldDoThx() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnyConfig.class);

        AnyBean anyBean =  applicationContext.getBean(AnyBean.class);
        anyBean.addPrefix("FOO");
    }
}
