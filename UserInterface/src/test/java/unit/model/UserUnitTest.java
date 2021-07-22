package unit.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userInterface.model.User;

import java.sql.Date;

public class UserUnitTest {

    private User user;

    @BeforeEach
    public void beforeEach() {

        user = new User(null,null,null,null,null,null);
    }

    @Test
    public void setAndGetUserId() {

        //GIVEN
        Integer userId = 1;

        //WHEN
        user.setUserId(userId);

        //THEN
        Assertions.assertThat(user.getUserId() == userId);
    }

    @Test
    public void setAndGetFirstName() {

        //GIVEN
        String firstName = "firstName";

        //WHEN
        user.setFirstName(firstName);

        //THEN
        Assertions.assertThat(user.getFirstName() == firstName);
    }

    @Test
    public void setAndGetLastName() {

        //GIVEN
        String lastName = "lastName";

        //WHEN
        user.setLastName(lastName);

        //THEN
        Assertions.assertThat(user.getLastName() == lastName);
    }

    @Test
    public void setAndGetBirthDate() {

        //GIVEN
        Date birthDate = new Date(System.currentTimeMillis());

        //WHEN
        user.setBirthDate(birthDate);

        //THEN
        Assertions.assertThat(user.getBirthDate() == birthDate);
    }

    @Test
    public void setAndGetGender() {

        //GIVEN
        String gender = "gender";

        //WHEN
        user.setGender(gender);

        //THEN
        Assertions.assertThat(user.getGender() == gender);
    }

    @Test
    public void setAndGetAddress() {

        //GIVEN
        String address = "address";

        //WHEN
        user.setAddress(address);

        //THEN
        Assertions.assertThat(user.getAddress() == address);
    }

    @Test
    public void setAndGetPhoneNumber() {

        //GIVEN
        String phoneNumber = "phoneNumber";

        //WHEN
        user.setPhoneNumber(phoneNumber);

        //THEN
        Assertions.assertThat(user.getPhoneNumber() == phoneNumber);
    }
}
