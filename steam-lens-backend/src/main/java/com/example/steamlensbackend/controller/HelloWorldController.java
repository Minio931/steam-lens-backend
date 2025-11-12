package com.example.steamlensbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/twoja-stara")
public class HelloWorldController {

    @GetMapping
    public String greeting(@RequestParam(defaultValue = "World") String name) {
        return "Hello " + name;
    }
}
