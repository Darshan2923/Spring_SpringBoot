package com.syncode.syncode.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class GeneralException extends RuntimeException {

    private String message;
    private HttpStatus status;

    public GeneralException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

}
