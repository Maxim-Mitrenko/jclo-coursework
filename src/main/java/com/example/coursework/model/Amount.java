package com.example.coursework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Amount {

    @Min(1)
    private final int value;
    @NotBlank
    private final String currency;

    public Amount(
            @JsonProperty("value")
            int value,
            @JsonProperty("currency")
            String currency) {
        this.value = value;
        this.currency = currency;
    }

    public int getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return value + " " + currency;
    }
}
