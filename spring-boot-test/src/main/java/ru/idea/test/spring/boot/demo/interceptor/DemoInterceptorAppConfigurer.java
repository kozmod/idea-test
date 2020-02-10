package ru.idea.test.spring.boot.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class DemoInterceptorAppConfigurer implements WebMvcConfigurer {

    private final DemoInterceptor interceptor;

    public DemoInterceptorAppConfigurer(DemoInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}
