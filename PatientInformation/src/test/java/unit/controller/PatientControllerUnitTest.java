package unit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patientInformation.controller.PatientController;
import patientInformation.model.Patient;
import patientInformation.service.PatientServiceInterface;

public class PatientControllerUnitTest {

    private PatientController patientController;

    private PatientServiceInterface patientServiceInterface = Mockito.mock(PatientServiceInterface.class);

    @BeforeEach
    public void beforeEach() {

        patientController = new PatientController(patientServiceInterface);
    }

    @Test
    public void insert() {

        //GIVEN
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        patientController.insert(patient);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).insert(patient);
    }

    @Test
    public void select() throws JsonProcessingException {

        //GIVEN
        int id = 1;

        //WHEN
        patientController.select(id);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).select(id);
    }

    @Test
    public void list() throws JsonProcessingException {

        //GIVEN

        //WHEN
        patientController.list();

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).list();
    }

    @Test
    public void update() {

        //GIVEN
        int id = 1;
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        patientController.update(id, patient);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).update(id, patient);
    }

    @Test
    public void delete() {

        //GIVEN
        int id = 1;

        //WHEN
        patientController.delete(id);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).delete(id);
    }
}