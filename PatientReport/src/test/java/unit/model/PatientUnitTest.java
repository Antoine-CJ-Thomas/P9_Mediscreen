package unit.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patientReport.model.Patient;

import java.sql.Date;

public class PatientUnitTest {

    private Patient patient;

    @BeforeEach
    public void beforeEach() {

        patient = new Patient(null,null,null,null,null,null);
    }

    @Test
    public void setAndGetUserId() {

        //GIVEN
        Integer userId = 1;

        //WHEN
        patient.setId(userId);

        //THEN
        Assertions.assertThat(patient.getId() == userId);
    }

    @Test
    public void setAndGetFirstName() {

        //GIVEN
        String firstName = "firstName";

        //WHEN
        patient.setFirstName(firstName);

        //THEN
        Assertions.assertThat(patient.getFirstName() == firstName);
    }

    @Test
    public void setAndGetLastName() {

        //GIVEN
        String lastName = "lastName";

        //WHEN
        patient.setLastName(lastName);

        //THEN
        Assertions.assertThat(patient.getLastName() == lastName);
    }

    @Test
    public void setAndGetBirthDate() {

        //GIVEN
        Date birthDate = new Date(System.currentTimeMillis());

        //WHEN
        patient.setBirthDate(birthDate);

        //THEN
        Assertions.assertThat(patient.getBirthDate() == birthDate);
    }

    @Test
    public void setAndGetGender() {

        //GIVEN
        String gender = "gender";

        //WHEN
        patient.setGender(gender);

        //THEN
        Assertions.assertThat(patient.getGender() == gender);
    }

    @Test
    public void setAndGetAddress() {

        //GIVEN
        String address = "address";

        //WHEN
        patient.setAddress(address);

        //THEN
        Assertions.assertThat(patient.getAddress() == address);
    }

    @Test
    public void setAndGetPhoneNumber() {

        //GIVEN
        String phoneNumber = "phoneNumber";

        //WHEN
        patient.setPhoneNumber(phoneNumber);

        //THEN
        Assertions.assertThat(patient.getPhoneNumber() == phoneNumber);
    }
}
