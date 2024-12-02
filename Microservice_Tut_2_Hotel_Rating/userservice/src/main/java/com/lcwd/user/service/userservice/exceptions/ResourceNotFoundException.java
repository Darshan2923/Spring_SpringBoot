package com.lcwd.user.service.userservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    // extra propoerties that you want
    public ResourceNotFoundException() {
        super("Resource not dound on server !!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
