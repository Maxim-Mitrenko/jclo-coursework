package com.example.coursework;

import com.example.coursework.model.Amount;
import com.example.coursework.model.Transfer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class CourseworkApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Container
    private final GenericContainer<?> app = new GenericContainer<>("backend:1.0")
            .withExposedPorts(5500);

    @Test
    void contextLoads() {
    }

    @Test
    public void goodTransferTest() throws URISyntaxException {
        var transfer = new Transfer("1234567890123456", "0123", "982", "0987654321098765", new Amount(100, "RUB"));
        var entity = testRestTemplate.postForEntity(new URI("http://localhost:" + app.getMappedPort(5500) + "/transfer"), transfer, String.class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void badNumberFromCardTest() throws URISyntaxException {
        var transfer = new Transfer("1", "0123", "982", "0987654321098765", new Amount(100, "RUB"));
        badTransfer(transfer);
    }

    @Test
    public void badCVVTest() throws URISyntaxException {
        var transfer = new Transfer("1234567890123456", "0123", "-", "0987654321098765", new Amount(100, "RUB"));
        badTransfer(transfer);
    }

    @Test
    public void badNumberToCardTest() throws URISyntaxException {
        var transfer = new Transfer("1234567890123456", "0123", "982", "0", new Amount(100, "RUB"));
        badTransfer(transfer);
    }

    @Test
    public void badSumTest() throws URISyntaxException {
        var transfer = new Transfer("1234567890123456", "0123", "982", "0987654321098765", new Amount(-1, "RUB"));
        badTransfer(transfer);
    }

    public void badTransfer(Transfer transfer) throws URISyntaxException {
        var entity = testRestTemplate.postForEntity(new URI("http://localhost:" + app.getMappedPort(5500) + "/transfer"), transfer, String.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }
}
