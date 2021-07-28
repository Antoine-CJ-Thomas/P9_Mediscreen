package unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import userInterface.controller.CommonController;
import userInterface.model.Patient;
import userInterface.service.PatientServiceInterface;
import userInterface.service.NoteServiceInterface;

public class CommonControllerIUnitTest {

    private CommonController commonController;

    private PatientServiceInterface patientServiceInterface = Mockito.mock(PatientServiceInterface.class);
    private NoteServiceInterface noteServiceInterface = Mockito.mock(NoteServiceInterface.class);

    @BeforeEach
    public void beforeEach() {

        commonController = new CommonController(patientServiceInterface, noteServiceInterface);
    }

    @Test
    public void listPatient() {

        //GIVEN
        Model model = Mockito.mock(Model.class);

        //WHEN
        commonController.listPatient(model);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).list();
    }
}
