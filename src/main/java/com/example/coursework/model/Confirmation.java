package com.example.coursework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Confirmation {

    @NotBlank
    private final String operationId;
    @Size(min = 4)
    @NotBlank
    private final String code;

    public Confirmation(
            @JsonProperty("operationId")
            String operationId,
            @JsonProperty("code")
            String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public String getOperationId() {
        return operationId;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return operationId;
    }
}
