package ru.idea.test.spring.boot.web.bench;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "ru.idea.test.spring.boot.web.bench"
})

public class BenchApp {
    public static void main(String[] args) {
        SpringApplication.run(BenchApp.class, args);
    }
}