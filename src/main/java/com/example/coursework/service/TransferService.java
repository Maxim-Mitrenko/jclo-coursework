package com.example.coursework.service;

import com.example.coursework.exception.TransferNotFoundException;
import com.example.coursework.model.Confirmation;
import com.example.coursework.model.Status;
import com.example.coursework.model.Transfer;
import com.example.coursework.model.response.SuccessTransferResponse;
import com.example.coursework.repository.TransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransferService {

    private final TransferRepository repository;

    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }

    public SuccessTransferResponse transfer(Transfer transfer) {
        repository.add(transfer);
        log.info("Платеж " + transfer + " создан. Необходимо подтверждение!");
        return new SuccessTransferResponse(transfer.getOperationId());
    }

    public SuccessTransferResponse confirmOperation(Confirmation confirmation) {
        var pay = repository.get(confirmation.getOperationId());
        if (pay == null) throw new TransferNotFoundException("Перевод " + confirmation.getOperationId() + " не найден!");
        if (Status.COMPLETED.equals(pay.getStatus())) throw new IllegalStateException("Перевод " + pay + " уже подтвержден и выполнен!");
        pay.completed(confirmation);
        log.info("Платёж " + pay + " был подтверждён и успешно выполнен!");
        return new SuccessTransferResponse(pay.getOperationId());
    }
}
