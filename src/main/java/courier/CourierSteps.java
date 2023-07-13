package courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CourierSteps {

    private static final String BASE_URI = "http://qa-scooter.praktikum-services.ru";
    private static final String COURIER_PATH = "/api/v1/courier";
    private static final String COURIER_LOGIN_PATH = "/api/v1/courier/login";
    private static final String DELETE_COURIER = "api/v1/courier/";

    @Step("Create new courier")
    public ValidatableResponse create(Courier courier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .when()
                .body(courier)
                .post(COURIER_PATH)
                .then();
    }

    @Step("Login courier")
    public ValidatableResponse login(CourierLogin courierLogin) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .when()
                .body(courierLogin)
                .post(COURIER_LOGIN_PATH)
                .then();
    }

    @Step("Delete courier")
    public ValidatableResponse delete(String id) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .when()
                .delete(DELETE_COURIER + id)
                .then();
    }
}
