package unit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patientReport.controller.DiabetesReportController;
import patientReport.model.DiabetesReport;
import patientReport.service.DiabetesReportServiceInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DiabetesReportControllerUnitTest {

    private DiabetesReportController diabetesReportController;

    private ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);

    private DiabetesReportServiceInterface diabetesReportServiceInterface = Mockito.mock(DiabetesReportServiceInterface.class);
    private HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

    @BeforeEach
    public void beforeEach() {

        diabetesReportController = new DiabetesReportController(objectMapper, diabetesReportServiceInterface);
    }

    @Test
    public void assessDiabetes_success() throws IOException {

        //GIVEN
        DiabetesReport diabetesReport = Mockito.mock(DiabetesReport.class);

        //WHEN
        Mockito.when(diabetesReportServiceInterface.assessDiabetes(diabetesReport)).thenReturn("OK");
        Mockito.when(objectMapper.writeValueAsString(diabetesReport)).thenReturn("diabetesReport");

        diabetesReportController.assessDiabetes(diabetesReport, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).setStatus(200);
    }

    @Test
    public void assessDiabetes_failure() throws IOException {

        //GIVEN
        DiabetesReport diabetesReport = Mockito.mock(DiabetesReport.class);

        //WHEN
        Mockito.when(diabetesReportServiceInterface.assessDiabetes(diabetesReport)).thenReturn("Invalid informations");
        Mockito.when(objectMapper.writeValueAsString(diabetesReport)).thenReturn("diabetesReport");

        diabetesReportController.assessDiabetes(diabetesReport, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).sendError(400, "Diabetes assessment could not be done");
    }
}