package com.myapp.myapp;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {
    public void compile() {
        System.out.println("compiling the code");
    }
}
