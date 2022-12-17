package com.parking.controller;

import com.parking.repository.ParkRepository;
import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.path.json.config.JsonPathConfig;
import com.parking.model.ParkingDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class ParkingControllerTests {
    @Autowired
    private ParkRepository parkRepository;
    @LocalServerPort
    private int serverPort;

    @BeforeAll
    public void setUpTest() {
        RestAssured.port = serverPort;
        RestAssured.config = RestAssured.config().jsonConfig(
                JsonConfig.jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.DOUBLE)
        );
    }

    private ParkingDTO parkingDTOTest() {
        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setLicense("PGS-7603");
        parkingDTO.setPrice(2.50);

        return parkingDTO;
    }

    @Test
    @Order(0)
    public void checkinTest() {
        ParkingDTO parkingDTO = parkingDTOTest();

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(parkingDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo(parkingDTO.getLicense()))
                .body("price", Matchers.equalTo(parkingDTO.getPrice()))
                .body("checkout", Matchers.equalTo(false));
    }

    @Test
    @Order(1)
    public void getAllTest() {
        ParkingDTO parkingDTO = parkingDTOTest();

        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
