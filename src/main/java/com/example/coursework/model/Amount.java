package com.example.coursework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Amount {

    @Min(1)
    private final BigDecimal sum;
    @NotBlank
    private final String currency;

    private final BigDecimal commission;

    public Amount(
            @JsonProperty("value")
            int value,
            @JsonProperty("currency")
            String currency) {
        this.sum = new BigDecimal(value).divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN);
        this.commission = this.sum.divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN);
        this.currency = currency;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    @Override
    public String toString() {
        return sum + " " + currency;
    }
}
