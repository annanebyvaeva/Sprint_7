package order;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderSteps {
    private final String BASE_URI = "http://qa-scooter.praktikum-services.ru";
    private final String POST_CREATE_ORDER = "/api/v1/orders";
    private final String GET_LIST_ORDER = "/api/v1/orders";
    private final String PUT_CANCEL_ORDER = "/api/v1/orders/cancel";

    @Step("Create new order")
    public ValidatableResponse createOrder(Orders orders) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(orders)
                .when()
                .post(POST_CREATE_ORDER)
                .then();
    }
    @Step("Get order list")
    public ValidatableResponse getOrderList() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .when()
                .get(GET_LIST_ORDER)
                .then();
    }

    @Step("Delete existing order by track")
    public ValidatableResponse deleteOrder(int track) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(track)
                .when()
                .put(PUT_CANCEL_ORDER)
                .then();
    }
}
