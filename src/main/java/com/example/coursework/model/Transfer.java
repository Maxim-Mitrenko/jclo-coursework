package com.example.coursework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.YearMonth;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Transfer {

    private static final AtomicInteger nextId = new AtomicInteger(0);
    @Size(min = 16, max = 16)
    @NotBlank
    private final String cardFromNumber;
    @Size(min = 4, max = 4)
    @NotBlank
    private final String cardFromValidTill;
    @Size(min = 3, max = 3)
    @NotBlank
    private final String cardFromCVV;
    @Size(min = 16, max = 16)
    @NotBlank
    private final String cardToNumber;
    @Valid
    private final Amount amount;
    private final String operationId = String.valueOf(nextId.incrementAndGet());

    private Status status = Status.CREATED;

    public Transfer(
            @JsonProperty("cardFromNumber")
            String cardFromNumber,
            @JsonProperty("cardFromValidTill")
            String cardFromValidTill,
            @JsonProperty("cardFromCVV")
            String cardFromCVV,
            @JsonProperty("cardToNumber")
            String cardToNumber,
            @JsonProperty("amount")
            Amount amount) {
        this.cardFromNumber = cardFromNumber;
        var yearMonth = YearMonth.now();
        var yearMonthEndCard = YearMonth.of(Integer.parseInt("20" + cardFromValidTill.substring(2)), Integer.parseInt(cardFromValidTill.substring(0, 2)));
        if (yearMonth.isAfter(yearMonthEndCard)) throw new IllegalArgumentException("Срок действия карты истек");
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        this.amount = amount;
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public Amount getAmount() {
        return amount;
    }

    public String getOperationId() {
        return operationId;
    }

    public Status getStatus() {
        return status;
    }

    public void completed(Confirmation confirmation) {
        this.status = Status.COMPLETED;
    }

    @Override
    public String toString() {
        return cardFromNumber + " to " + cardToNumber + " " + amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return operationId.equals(transfer.operationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId);
    }
}
