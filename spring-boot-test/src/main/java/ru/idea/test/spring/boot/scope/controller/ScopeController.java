package ru.idea.test.spring.boot.scope.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.idea.test.spring.boot.scope.bean.MessageGenerator;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ScopeController {

    private static Logger LOGGER = LoggerFactory.getLogger(ScopeController.class);

    @Resource(name = "requestScopedBean")
    private MessageGenerator requestScopedBean;

    @Resource(name = "sessionScopedBean")
    private MessageGenerator sessionScopedBean;

    @Resource(name = "applicationScopedBean")
    private MessageGenerator applicationScopedBean;


    @PutMapping(value = "/scopes/request/{message}")
    public ResponseEntity<Object> putRequestScopeMessage(@PathVariable("message") String message) {
        final Map<String, String> messages = currentAndPreviousMessages(message, requestScopedBean);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PutMapping(value = "/scopes/session/{message}")
    public ResponseEntity<Object> putSessionScopeMessage(@PathVariable("message") String message) {
        final Map<String, String> messages = currentAndPreviousMessages(message, sessionScopedBean);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PutMapping(value = "/scopes/application/{message}")
    public ResponseEntity<Object> putApplicationScopeMessage(@PathVariable("message") String message) {
        final Map<String, String> messages = currentAndPreviousMessages(message, applicationScopedBean);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    private static Map<String, String> currentAndPreviousMessages(String currentMessage, MessageGenerator bean) {
        final Map<String, String> messages = new HashMap<>(2, 1.5F);
        messages.put("previousMessage", bean.getMessage());
        bean.setMessage(currentMessage);
        messages.put("currentMessage", bean.getMessage());
        return Collections.unmodifiableMap(messages);
    }
}
