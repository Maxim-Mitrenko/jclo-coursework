package com.example.coursework.model.response;

public class SuccessTransferResponse {

    private final String operationId;

    public SuccessTransferResponse(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    @Override
    public String toString() {
        return operationId;
    }
}
