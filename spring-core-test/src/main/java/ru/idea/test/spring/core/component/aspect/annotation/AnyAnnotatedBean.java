package ru.idea.test.spring.core.component.aspect.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.idea.test.spring.core.aspect.anotation.TestAnnotation;

public class AnyAnnotatedBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnyAnnotatedBean.class);

    @TestAnnotation
    public void method1() {
        LOGGER.info("method1");
        method2();
    }

    @TestAnnotation
    public void method2() {
        LOGGER.info("method2");
    }
}
