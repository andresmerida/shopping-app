package org.adk.shopping.product.rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.MongoDBContainer;

import static io.restassured.RestAssured.given;
import static org.adk.shopping.product.util.ConstantsUtil.BASE_URI;
import static org.adk.shopping.product.util.ConstantsUtil.CONTENT_TYPE_APPLICATION_JSON;
import static org.adk.shopping.product.util.ConstantsUtil.DOCKER_IMAGE_MONGODB;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DOCKER_IMAGE_MONGODB);

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    static {
        mongoDBContainer.start();
    }

    @Test
    void shouldCreate() {
        String requestBody = """
                    {
                        "name": "iPhone 14",
                        "description": "iPhone 14 2023",
                        "price": 999
                    }
                """;
        // GIVEN
        given()
                .contentType(CONTENT_TYPE_APPLICATION_JSON)
                .body(requestBody)
                // WHEN
                .when()
                .post("/api/products")
                // THEN
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("name", equalTo("iPhone 14"))
                .body("description", equalTo("iPhone 14 2023"))
                .body("price", equalTo(999));
    }
}