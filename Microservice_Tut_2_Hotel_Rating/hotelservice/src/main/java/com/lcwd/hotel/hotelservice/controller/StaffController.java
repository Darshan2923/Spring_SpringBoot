package com.lcwd.hotel.hotelservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @GetMapping
    public ResponseEntity<List<String>> getStaff() {
        return new ResponseEntity<>(Arrays.asList("Ram", "Shyaam", "Seeta", "Krishna"), HttpStatus.OK);
    }
}
