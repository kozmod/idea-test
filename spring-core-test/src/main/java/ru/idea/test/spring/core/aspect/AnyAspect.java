package ru.idea.test.spring.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.idea.test.spring.core.conf.AnyPostProcessorConfig;
import ru.idea.test.spring.core.utils.CommonUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
public class AnyAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnyPostProcessorConfig.class);

    @Pointcut("execution(public * ru.idea.test.spring.core.component.aspect.simple.AnyBean.*(..))")
    public void callAnyBean() {
    }

    @Pointcut("within(ru.idea.test.spring.core.component.*)")
    public void callAnyBeanPackage() {
    }

    @Before("callAnyBeanPackage()")
    public void beforeCallAtMethod1(JoinPoint jp) {
        final String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        LOGGER.info("Before: " + jp.toString() + ",\n args=[" + args + "]");
    }

    @After("callAnyBeanPackage()")
    public void afterCallAt(JoinPoint jp) {
        LOGGER.info("After " + jp.toString());
    }

    @AfterThrowing(pointcut = "callAnyBeanPackage()", throwing = "ex")
    public void logAfterThrowingAllMethods(JoinPoint jp, Exception ex) throws Throwable {
        LOGGER.error("After throwing exception by service method: " + jp.toString() + ",\n exception: "+ CommonUtils.getStackTrace(ex));
    }


}
