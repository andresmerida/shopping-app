package org.adk.shopping.inventory.web.rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryControllerTest {
    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mySQLContainer.start();
    }

    @Test
    void getAllInventory() {
        var responseList = RestAssured
                .given()
                .when()
                .get("/api/inventories")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().as(List.class);
        assertFalse(responseList.isEmpty());
    }

    @Test
    void isAvailable() {
        var response = RestAssured
                .given()
                .when()
                .get("/api/inventories/iphone_15/1/available")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().as(Boolean.class);
        assertTrue(response);

        var falseResponse = RestAssured
                .given()
                .when()
                .get("/api/inventories/iphone_15/1000/available")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().as(Boolean.class);
        assertFalse(falseResponse);
    }
}