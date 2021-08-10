package patientReport.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import patientReport.model.DiabetesReport;
import patientReport.service.DiabetesReportService;
import patientReport.service.DiabetesReportServiceInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class DiabetesReportController {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private DiabetesReportServiceInterface diabetesReportServiceInterface;

    public DiabetesReportController() {
        logger.info("DiabetesReportController()");

        objectMapper = new ObjectMapper();
        diabetesReportServiceInterface = new DiabetesReportService();
    }

    public DiabetesReportController(ObjectMapper objectMapper, DiabetesReportServiceInterface diabetesReportServiceInterface) {
        logger.info("DiabetesReportController(" + objectMapper + "," + diabetesReportServiceInterface + ")");

        this.objectMapper = objectMapper;
        this.diabetesReportServiceInterface = diabetesReportServiceInterface;
    }

    @PostMapping("/assess/diabetes")
    public String assessDiabetes(@RequestBody DiabetesReport diabetesReport, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("assessDiabetes(" + diabetesReport + ")");

        String message = diabetesReportServiceInterface.assessDiabetes(diabetesReport);

        if (message.equals("OK")) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(400, "Diabetes assessment could not be done");
        }

        return objectMapper.writeValueAsString(diabetesReport);
    }
}