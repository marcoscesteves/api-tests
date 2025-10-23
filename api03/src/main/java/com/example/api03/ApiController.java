package com.example.api03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
public class ApiController {
    
    @GetMapping("hello/{name}")
    public Greeting hello(@PathVariable String name) {
        return new Greeting(
            name,
            "Hello, " + name + "! I am your third API built using Spring Boot!"
        );
    } 

    public record Greeting(String name, String message) {}
    
}
