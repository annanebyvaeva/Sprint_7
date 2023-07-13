package courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;


public class CourierLoginTest {
    private CourierSteps step;
    private Courier courier;
    protected static String id;

    @Before
    @Step("create objects for tests")
    public void setUp() {
        step =  new CourierSteps();
        courier = CourierRandom.getRandom();
        step.create(courier);
    }

    @Test
    @Step("login courier with correct login and password")
    public void loginSuccessTest() {
        ValidatableResponse response = step.login(CourierLogin.from(courier));
        id = response.extract().path("id").toString();
        response.assertThat().statusCode(200).body("id", greaterThan(0));
    }

    @Test
    @Step("login courier without login")
    public void loginWithoutLoginTest() {
        courier.setLogin("");
        ValidatableResponse responseWithoutLogin = step.login(CourierLogin.from(courier));
        responseWithoutLogin.statusCode(400).assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @Step("login courier without password")
    public void loginWithoutPasswordTest() {
       courier.setPassword("");
       ValidatableResponse responseWithoutPassword = step.login(CourierLogin.from(courier));
        responseWithoutPassword.statusCode(400).assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @Step ("login courier with incorrect password")
    public void loginWithNotCorrectPassword() {
        courier.setPassword("0000");
        ValidatableResponse responseWithIncorrectPassword = step.login(CourierLogin.from(courier));
        responseWithIncorrectPassword.assertThat().statusCode(404).body("message", Matchers.equalTo("Учетная запись не найдена"));
    }
    }


