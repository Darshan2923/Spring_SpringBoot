package com.kafka_demo.kafka_demo.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Student {

    private int id;
    private String firstname;
    private String lastname;
}
