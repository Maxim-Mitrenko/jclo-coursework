package com.example.coursework;

import com.example.coursework.model.Amount;
import com.example.coursework.model.Transfer;
import com.example.coursework.repository.TransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepositoryTest {

    private TransferRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new TransferRepository();
    }

    @Test
    public void addGetTest() {
        var transfer = new Transfer("12345678901234456", "12/25", "315", "0987654321098765", new Amount(1, "RUB"));
        repository.add(transfer);
        Assertions.assertEquals(transfer, repository.get(transfer.getOperationId()));
    }

    @Test
    public void notFoundTest() {
        Assertions.assertNull(repository.get(""));
    }
}
