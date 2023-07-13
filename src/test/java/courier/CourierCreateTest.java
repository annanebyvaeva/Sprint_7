package courier;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CourierCreateTest {
    private CourierSteps step;
    private Courier courier;

    @Before
    @Step("create objects for tests")
    public void setUp() {
        step =  new CourierSteps();
        courier = CourierRandom.getRandom();
    }

    @Test
    @Description("Create courier success")
    public void createCourierSuccessTest() {
            ValidatableResponse response = step.create(courier);
//            id = response.extract().path("id").toString();
            response.assertThat().statusCode(201).body("ok", is(true));
        }

    @Test
    @Description("Create courier without login")
    public void createCourierWithoutLoginTest() {
        courier.setLogin(null);
        ValidatableResponse responseNullLogin = step.create(courier);
        responseNullLogin.statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @Description("Create courier without password")
    public void createCourierWithoutPasswordTest() {
        courier.setPassword(null);
        ValidatableResponse responseNullPassword = step.create(courier);
        responseNullPassword.statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Test
    @Description("Create courier with same login")
    public void createCourierDouble() {
        step.create(courier);
        ValidatableResponse response = step.create(courier);
        response.statusCode(409).and().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    @Step("Delete courier")
    public void deleteCourier() {
        if (CourierLoginTest.id != null) {
            step.delete(CourierLoginTest.id);
        }
    }
}

