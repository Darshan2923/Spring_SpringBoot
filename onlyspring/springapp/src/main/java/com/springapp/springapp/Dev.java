package com.springapp.springapp;

public class Dev {
    private int age;
    private Laptop laptop;

    public Dev(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Laptop getLap() {
        return laptop;
    }

    public void build() {
        laptop.compile();
        System.out.println("Making an awesome project");
    }
}
