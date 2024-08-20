package com.simplewebapp.simplewebproj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String greet() {
        return "Welcome!!!";
    }

    @GetMapping("/about")
    public String about() {
        return "WE DONT TEACH WE EDUCATE!!!";
    }
}
