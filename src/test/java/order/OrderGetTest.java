package order;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import static org.hamcrest.Matchers.notNullValue;


public class OrderGetTest {
    private OrderSteps step = new OrderSteps();

    @Test
    public void checkBody() {
        ValidatableResponse response = step.getOrderList();
        response.assertThat().log().all().statusCode(200).body("orders", notNullValue());
    }
}


