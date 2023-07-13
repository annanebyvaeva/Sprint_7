package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierRandom {

    public static Courier getRandom() {
        String login = RandomStringUtils.randomAlphanumeric(10);
        String password = RandomStringUtils.randomAlphanumeric(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstName);
    }
}
