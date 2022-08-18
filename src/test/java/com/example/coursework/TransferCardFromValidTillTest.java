package com.example.coursework;

import com.example.coursework.model.Amount;
import com.example.coursework.model.Transfer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

public class TransferCardFromValidTillTest {

    @Test
    public void endCardTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Transfer("1234567890123456", "01/19", "982", "0987654321098765", new Amount(100, "RUB")));
    }

    @Test
    public void wrongDateTest() {
        Assertions.assertThrows(DateTimeException.class, () -> new Transfer("1234567890123456", "99/99", "982", "0987654321098765", new Amount(100, "RUB")));
    }

    @Test
    public void notDateTest() {
        Assertions.assertThrows(NumberFormatException.class, () -> new Transfer("1234567890123456", "Не/знаю", "982", "0987654321098765", new Amount(100, "RUB")));
    }

    @Test
    public void wrongDateFormat() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> new Transfer("1234567890123456", "-", "982", "0987654321098765", new Amount(100, "RUB")));
    }

    @Test
    public void goodDate() {
        Assertions.assertDoesNotThrow(() -> new Transfer("1234567890123456", "01/23", "982", "0987654321098765", new Amount(100, "RUB")));
    }
}
