package com.example.api02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ApiController {
    
    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello, " + name + "! I am your Second API built using Spring Boot! ";
    } 
    
}
