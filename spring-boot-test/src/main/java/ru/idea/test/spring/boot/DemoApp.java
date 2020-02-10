package ru.idea.test.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.idea.test.spring.boot.demo.DemoConfig;
import ru.idea.test.spring.boot.product.ProductConfig;
import ru.idea.test.spring.boot.scope.ScopeConfig;

@SpringBootApplication(scanBasePackages = {
        "ru.idea.test.spring.boot.runner"
})
@Import({
        DemoConfig.class,
        ProductConfig.class,
        ScopeConfig.class
})
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }
}