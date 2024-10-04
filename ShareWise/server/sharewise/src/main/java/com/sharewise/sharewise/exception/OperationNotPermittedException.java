package com.sharewise.sharewise.exception;

public class OperationNotPermittedException extends RuntimeException {

    public OperationNotPermittedException() {

    }

    public OperationNotPermittedException(String msg) {
        super(msg);

    }
}
