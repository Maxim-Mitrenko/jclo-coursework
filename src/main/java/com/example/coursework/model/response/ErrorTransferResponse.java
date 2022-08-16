package com.example.coursework.model.response;

import java.util.Objects;

public class ErrorTransferResponse {

    private final String message;
    private final int id;

    public ErrorTransferResponse(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorTransferResponse that = (ErrorTransferResponse) o;
        return id == that.id && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, id);
    }
}
