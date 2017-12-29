package com.eccom.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${name}")
    private String value;

    @GetMapping("/")
    public String home() {
        return "Hello "+this.value+" !";
    }
}
