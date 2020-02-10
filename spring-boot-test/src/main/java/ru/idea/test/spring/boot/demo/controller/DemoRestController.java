package ru.idea.test.spring.boot.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

    @Value("${spring.custom.rs-to-root-get}")
    private String reRoot;

    @RequestMapping(value = "/rc")
    public String hello() {
        return reRoot;
    }
}
