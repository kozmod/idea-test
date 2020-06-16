package ru.idea.test.spring.core.aspect.anotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AnyAnnotationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnyAnnotationAspect.class);

    @Pointcut("@annotation(ru.idea.test.spring.core.aspect.anotation.TestAnnotation)")
    public void annotated() {}

    @Before("annotated()")
    public void printABit(JoinPoint jp) {
        LOGGER.info("After " + jp.toString());
    }
}
