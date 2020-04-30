package ru.idea.test.spring.boot.web.bench;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public final class BenchController {

    private static final String HELLO = "Hello";
    private static final String WORLD = "World";
    private static final String EXCLAMATION = "!";
    private static final String SPACE = " ";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld() {
        return HELLO + SPACE + WORLD + EXCLAMATION;
    }

    @RequestMapping(value = "/greeting/{parameter}", method = RequestMethod.GET)
    @ResponseBody
    public String helloName(@PathVariable(required = false) String parameter) {
        return HELLO + SPACE + parameter + EXCLAMATION;
    }
}
