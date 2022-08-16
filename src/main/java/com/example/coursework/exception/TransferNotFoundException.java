package com.example.coursework.exception;

public class TransferNotFoundException extends RuntimeException {

    public TransferNotFoundException(String s) {
        super(s);
    }

    public TransferNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransferNotFoundException(Throwable cause) {
        super(cause);
    }
}
