package com.example.coursework;

import com.example.coursework.exception.TransferNotFoundException;
import com.example.coursework.model.Confirmation;
import com.example.coursework.model.Status;
import com.example.coursework.model.Transfer;
import com.example.coursework.repository.TransferRepository;
import com.example.coursework.service.TransferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServiceTest {

    private TransferRepository repository;
    private TransferService service;

    @BeforeEach
    public void beforeEach() {
        this.repository = Mockito.mock(TransferRepository.class);
        this.service = new TransferService(repository);
    }

    @Test
    public void transferSaveTest() {
        var transfer = Mockito.mock(Transfer.class);
        service.transfer(transfer);
        Mockito.verify(repository, Mockito.only()).add(transfer);
    }

    @Test
    public void confirmationTest() {
        var transfer = Mockito.mock(Transfer.class);
        Mockito.doCallRealMethod().when(transfer).completed(Mockito.any());
        Mockito.when(transfer.getStatus()).thenCallRealMethod();
        Mockito.when(repository.get(Mockito.any())).thenReturn(transfer);
        var confirmation = Mockito.mock(Confirmation.class);
        service.confirmOperation(confirmation);
        Assertions.assertEquals(Status.COMPLETED, transfer.getStatus());
    }

    @Test
    public void confirmationAgainErrorTest() {
        var transfer = Mockito.mock(Transfer.class);
        Mockito.doCallRealMethod().when(transfer).completed(Mockito.any());
        Mockito.when(transfer.getStatus()).thenCallRealMethod();
        Mockito.when(repository.get(Mockito.any())).thenReturn(transfer);
        var confirmation = Mockito.mock(Confirmation.class);
        service.confirmOperation(confirmation);
        Assertions.assertThrows(IllegalStateException.class, () -> service.confirmOperation(confirmation));
    }

    @Test
    public void confirmationTransferNotFoundTest() {
        var confirmation = Mockito.mock(Confirmation.class);
        Assertions.assertThrows(TransferNotFoundException.class, () -> service.confirmOperation(confirmation));
    }
}
