package com.example.api01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    
    @GetMapping("hello")
    public String hello() {
        return "Hello! I am your first API built using Spring Boot! ";
    } 
    
}
