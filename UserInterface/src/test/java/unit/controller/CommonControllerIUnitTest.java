package unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import userInterface.controller.CommonController;
import userInterface.model.Patient;
import userInterface.service.PatientServiceInterface;
import userInterface.service.NoteServiceInterface;
import userInterface.service.ReportServiceInterface;

public class CommonControllerIUnitTest {

    private CommonController commonController;

    private PatientServiceInterface patientServiceInterface = Mockito.mock(PatientServiceInterface.class);
    private NoteServiceInterface noteServiceInterface = Mockito.mock(NoteServiceInterface.class);
    private ReportServiceInterface reportServiceInterface = Mockito.mock(ReportServiceInterface.class);

    @BeforeEach
    public void beforeEach() {

        commonController = new CommonController(patientServiceInterface, noteServiceInterface, reportServiceInterface);
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

    @Test
    public void openPatient() {

        //GIVEN
        int patientId = 1;
        Model model = Mockito.mock(Model.class);

        //WHEN
        commonController.openPatient(patientId, model);

        //THEN
        Mockito.verify(patientServiceInterface, Mockito.times(1)).select(patientId);
        Mockito.verify(noteServiceInterface, Mockito.times(1)).list(patientId);
    }
}
