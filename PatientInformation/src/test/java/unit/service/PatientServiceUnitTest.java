package unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patientInformation.model.Patient;
import patientInformation.repository.PatientRepository;
import patientInformation.service.PatientService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class PatientServiceUnitTest {

    private PatientService patientService;

    private PatientRepository mockedPatientRepository = Mockito.mock(PatientRepository.class);

    @BeforeEach
    public void beforeEach() {

        patientService = new PatientService(mockedPatientRepository);
    }

    @Test
    public void insert_success() {

        //GIVEN
        Patient patient = Mockito.mock(Patient.class);

        String firstName = "testFirstName";
        String lastName = "testLastName";
        Date birthDate = new Date(System.currentTimeMillis());
        String gender = "M";
        String address = "testAddress";
        String phoneNumber = "0123456789";

        //WHEN
        Mockito.when(patient.getFirstName()).thenReturn(firstName);
        Mockito.when(patient.getLastName()).thenReturn(lastName);
        Mockito.when(patient.getBirthDate()).thenReturn(birthDate);
        Mockito.when(patient.getGender()).thenReturn(gender);
        Mockito.when(patient.getAddress()).thenReturn(address);
        Mockito.when(patient.getPhoneNumber()).thenReturn(phoneNumber);

        //THEN
        Assertions.assertThat(patientService.insert(patient) == "OK");
    }

    @Test
    public void insert_failure() {

        //GIVEN
        Patient patient = Mockito.mock(Patient.class);

        String firstName = "";

        //WHEN
        Mockito.when(patient.getFirstName()).thenReturn(firstName);

        //THEN
        Assertions.assertThat(patientService.insert(patient) == "Invalid informations");
    }

    @Test
    public void select_success() {

        //GIVEN
        int id = 1;
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(mockedPatientRepository.existsById(id)).thenReturn(true);
        Mockito.when(mockedPatientRepository.findById(id)).thenReturn(Optional.of(patient));

        //THEN
        Assertions.assertThat(patientService.select(id) == patient);
    }

    @Test
    public void select_failure() {

        //GIVEN
        int id = 1;

        //WHEN
        Mockito.when(mockedPatientRepository.existsById(id)).thenReturn(false);

        //THEN
        Assertions.assertThat(patientService.select(id) == null);
    }

    @Test
    public void list_success() {

        //GIVEN
        Iterable<Patient> patientIterable = new ArrayList<>();

        //WHEN
        Mockito.when(mockedPatientRepository.findAll()).thenReturn(patientIterable);

        //THEN
        Assertions.assertThat(patientService.list() == patientIterable);
    }

    @Test
    public void list_failure() {

        //GIVEN

        //WHEN
        Mockito.when(mockedPatientRepository.findAll()).thenReturn(null);

        //THEN
        Assertions.assertThat(patientService.list() == null);
    }

    @Test
    public void update_success() {

        //GIVEN
        int id = 1;
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(mockedPatientRepository.existsById(id)).thenReturn(true);

        //THEN
        Assertions.assertThat(patientService.update(id, patient) == "OK");
    }

    @Test
    public void update_failure() {

        //GIVEN
        int id = 1;
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(mockedPatientRepository.existsById(id)).thenReturn(false);

        //THEN
        Assertions.assertThat(patientService.update(id, patient) == "Invalid id");
    }

    @Test
    public void delete_success() {

        //GIVEN
        int id = 1;

        //WHEN
        Mockito.when(mockedPatientRepository.existsById(id)).thenReturn(true);

        //THEN
        Assertions.assertThat(patientService.delete(id) == "OK");
    }

    @Test
    public void delete_failure() {

        //GIVEN
        int id = 1;

        //WHEN
        Mockito.when(mockedPatientRepository.existsById(id)).thenReturn(false);

        //THEN
        Assertions.assertThat(patientService.delete(id) == "Invalid id");
    }
}