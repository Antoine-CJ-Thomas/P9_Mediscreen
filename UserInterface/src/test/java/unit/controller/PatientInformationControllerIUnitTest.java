package unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import userInterface.controller.PatientInformationController;
import userInterface.model.Patient;
import userInterface.service.PatientServiceInterface;

public class PatientInformationControllerIUnitTest {

    private PatientInformationController patientInformationController;

    private PatientServiceInterface patientServiceInterface = Mockito.mock(PatientServiceInterface.class);

    @BeforeEach
    public void beforeEach() {

        patientInformationController = new PatientInformationController(patientServiceInterface);
    }

    @Test
    public void insert() {

        //GIVEN
        Patient patient = Mockito.mock(Patient.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        patientInformationController.insert(patient, bindingResult, model);

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

        patientInformationController.update(userId, patient, bindingResult, model);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).update(userId, patient);
    }

    @Test
    public void delete() {

        //GIVEN
        Integer userId = 1;
        Model model = Mockito.mock(Model.class);

        //WHEN

        patientInformationController.delete(userId, model);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).delete(userId);
    }
}