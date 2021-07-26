package unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import userInterface.controller.PatientController;
import userInterface.model.Patient;
import userInterface.service.PatientServiceInterface;

import java.sql.Date;

public class PatientControllerIUnitTest {

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
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        patientController.insert(patient, bindingResult, model);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).insert(patient);
    }

    @Test
    public void update() {

        //GIVEN
        Integer userId = 1;
        Patient patient = Mockito.mock(Patient.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        patientController.update(userId, patient, bindingResult, model);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).update(userId, patient);
    }

    @Test
    public void delete() {

        //GIVEN
        Integer userId = 1;
        Model model = Mockito.mock(Model.class);

        //WHEN

        patientController.delete(userId, model);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).delete(userId);
    }
}