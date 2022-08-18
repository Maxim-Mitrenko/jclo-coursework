package com.example.coursework.controller;

import com.example.coursework.model.Confirmation;
import com.example.coursework.model.Transfer;
import com.example.coursework.model.response.SuccessTransferResponse;
import com.example.coursework.service.TransferService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public SuccessTransferResponse transfer(@RequestBody @Valid Transfer transfer) {
        return service.transfer(transfer);
    }

    @PostMapping("/confirmOperation")
    public SuccessTransferResponse confirmOperation(@RequestBody @Valid Confirmation confirmation) {
        return service.confirmOperation(confirmation);
    }
}
