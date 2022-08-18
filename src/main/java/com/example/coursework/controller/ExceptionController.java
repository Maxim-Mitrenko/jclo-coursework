package com.example.coursework.controller;

import com.example.coursework.exception.TransferNotFoundException;
import com.example.coursework.model.response.ErrorTransferResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorTransferResponse> endCard(IllegalArgumentException e) {
        log.error(e.toString());
        var response = new ErrorTransferResponse(e.getMessage(), 400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<ErrorTransferResponse> wrongEndTill(DateTimeException e) {
        var message = "Неверный срок действия";
        log.error(e.toString() + " " + message);
        var response = new ErrorTransferResponse(message, 400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorTransferResponse> notEndTill(NumberFormatException e) {
        var message = "Введены в поле срок действия не цифры";
        log.error(e.toString() + " " + message);
        var response = new ErrorTransferResponse(message, 400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferNotFoundException.class)
    public ResponseEntity<ErrorTransferResponse> notFound(TransferNotFoundException e) {
        log.error(e.toString());
        var response = new ErrorTransferResponse(e.getMessage(), 400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorTransferResponse> alreadyDone(IllegalStateException e) {
        log.error(e.toString());
        var response = new ErrorTransferResponse(e.getMessage(), 400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ErrorTransferResponse> wrongFormatDate(StringIndexOutOfBoundsException e) {
        log.error(e + "Неверный формат даты");
        var response = new ErrorTransferResponse(e.getMessage(), 400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
