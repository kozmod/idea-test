package ru.idea.test.spring.boot.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @Value("${spring.custom.rs-to-root-get}")
    private String reRoot;

    @RequestMapping(value = "/c")
    @ResponseBody
    public String hello() {
        return reRoot;
    }
}
