package unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import userInterface.controller.HomeController;
import userInterface.model.Patient;
import userInterface.service.PatientServiceInterface;

import java.sql.Date;

public class HomeControllerIUnitTest {

    private HomeController homeController;

    private PatientServiceInterface patientServiceInterface = Mockito.mock(PatientServiceInterface.class);

    @BeforeEach
    public void beforeEach() {

        homeController = new HomeController(patientServiceInterface);
    }

    @Test
    public void addPatient() {

        //GIVEN
        Patient patient = Mockito.mock(Patient.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        homeController.addPatient(patient, model);

        //THEN
        Mockito.verify(model, Mockito.times(1)).addAttribute("patient", patient);
    }

    @Test
    public void listPatient() {

        //GIVEN
        Model model = Mockito.mock(Model.class);

        //WHEN
        homeController.listPatient(model);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).list();
    }

    @Test
    public void editPatient() {

        //GIVEN
        Integer userId = 1;
        Model model = Mockito.mock(Model.class);

        //WHEN
        homeController.editPatient(userId, model);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).select(userId);
    }
}
