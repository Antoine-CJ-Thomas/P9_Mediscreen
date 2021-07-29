package unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import userInterface.controller.PatientController;
import userInterface.model.Patient;
import userInterface.service.PatientServiceInterface;

public class PatientControllerIUnitTest {

    private PatientController patientController;

    private PatientServiceInterface patientServiceInterface = Mockito.mock(PatientServiceInterface.class);

    @BeforeEach
    public void beforeEach() {

        patientController = new PatientController(patientServiceInterface);
    }

    @Test
    public void addPatient() {

        //GIVEN
        Patient patient = Mockito.mock(Patient.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        patientController.addPatient(patient, model);

        //THEN
        Mockito.verify(model, Mockito.times(1)).addAttribute("patient", patient);
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
    public void editPatient() {

        //GIVEN
        Integer patientId = 1;
        Model model = Mockito.mock(Model.class);

        //WHEN
        patientController.editPatient(patientId, model);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).select(patientId);
    }

    @Test
    public void update() {

        //GIVEN
        Integer patientId = 1;
        Patient patient = Mockito.mock(Patient.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        RedirectAttributes redirectAttributes = Mockito.mock(RedirectAttributes.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        patientController.update(patientId, patient, bindingResult, redirectAttributes, model);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).update(patientId, patient);
    }

    @Test
    public void delete() {

        //GIVEN
        Integer patientId = 1;
        Model model = Mockito.mock(Model.class);

        //WHEN

        patientController.delete(patientId, model);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).delete(patientId);
    }
}