package unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import patientReport.controller.DiabetesReportController;
import patientReport.service.DiabetesReportServiceInterface;

public class DiabetesReportControllerUnitTest {

    private DiabetesReportController diabetesReportController;

    private DiabetesReportServiceInterface diabetesReportServiceInterface = Mockito.mock(DiabetesReportServiceInterface.class);

    @BeforeEach
    public void beforeEach() {

        diabetesReportController = new DiabetesReportController(diabetesReportServiceInterface);
    }
}
