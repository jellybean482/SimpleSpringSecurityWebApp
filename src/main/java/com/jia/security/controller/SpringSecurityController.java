package com.jia.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Spring MVC controller class
 */
@RestController
public class SpringSecurityController {

    @GetMapping
    public String home() {
        String result = "Hello";
        return result;
    }

    @GetMapping("/user")
    public String user() {
        return "Hello, User!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello, Admin!";
    }
}
