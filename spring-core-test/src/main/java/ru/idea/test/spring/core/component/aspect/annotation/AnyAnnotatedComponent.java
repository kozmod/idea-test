package ru.idea.test.spring.core.component.aspect.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.idea.test.spring.core.aspect.anotation.TestAnnotation;

@Component
public class AnyAnnotatedComponent implements AnnotatedBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnyAnnotatedComponent.class);

    @TestAnnotation
    @Override
    public void method1() {
        LOGGER.info("method1");
        method2();
    }

    @TestAnnotation
    @Override
    public void method2() {
        LOGGER.info("method2");
    }
}
