package order;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)

public class OrdersCreateTest {
    private OrderSteps step = new OrderSteps();
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> color;
    int track;
    public OrdersCreateTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters()
    public static Object[][] params() {
        return new Object[][]{
                {"Семен", "Иванов", "Восточная, 12", 1, "+7 999 111 11 11", 1, "2023-07-11", "Тест1", List.of("BLACK")},
                {"Артур", "Алексеев", "Западная, 13", 2, "+7 999 222 22 22", 2, "2023-07-12", "Тест2", List.of("GREY")},
                {"Алена", "Михайлова", "Северная, 14", 3, "+7 999 333 33 33", 3, "2023-07-13", "Тест3", List.of("BLACK", "GREY")},
                {"Анна", "Смирнова", "Южная, 15", 4, "+7 999 444 44 44", 4, "2023-07-14", "Тест4", List.of()},
        };
    }
    @Test
    @Description("Test for order creation")
    public void checkOrdersResponseBodyTest() {
        Orders orders = new Orders(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment);
        ValidatableResponse response = step.createOrder(orders);
        response.assertThat().log().all().statusCode(201).body("track", is(notNullValue()));
    }

    @After
    public void tearDown() {
        ValidatableResponse response = step.deleteOrder(track);
    }
}

