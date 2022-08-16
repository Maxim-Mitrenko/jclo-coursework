package com.example.coursework.repository;

import com.example.coursework.model.Transfer;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransferRepository {

    private final Map<String, Transfer> transfers = new ConcurrentHashMap<>();

    public Transfer get(String operationId) {
        return transfers.get(operationId);
    }

    public void add(Transfer transfer) {
        transfers.put(transfer.getOperationId(), transfer);
    }

    @Override
    public String toString() {
        return transfers.toString();
    }
}
