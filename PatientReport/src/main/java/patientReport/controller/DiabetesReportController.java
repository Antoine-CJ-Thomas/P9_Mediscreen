package patientReport.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import patientReport.model.DiabetesReport;
import patientReport.service.DiabetesReportService;
import patientReport.service.DiabetesReportServiceInterface;

import java.util.Date;
import java.util.List;

@RestController
public class DiabetesReportController {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private DiabetesReportServiceInterface diabetesReportServiceInterface;

    public DiabetesReportController() {
        logger.info("DiabetesReportController()");

        diabetesReportServiceInterface = new DiabetesReportService();
    }

    public DiabetesReportController(DiabetesReportServiceInterface diabetesReportServiceInterface) {
        logger.info("DiabetesReportController(" + diabetesReportServiceInterface + ")");

        this.diabetesReportServiceInterface = diabetesReportServiceInterface;
    }

    @PostMapping("/assess/diabetes")
    public String assessDiabetes(@RequestParam String gender,
                                 @RequestParam List<String> commentaryList,
                                 @RequestBody Date birthDate) throws JsonProcessingException {

        logger.info("assessDiabetes(" + gender + "," + birthDate + "," + commentaryList + ")");

        DiabetesReport diabetesReport = new DiabetesReport(gender, birthDate, commentaryList);

        diabetesReportServiceInterface.setGeneralTriggerTerm(diabetesReport);
        diabetesReportServiceInterface.findMedicalTriggerTerm(diabetesReport);
        diabetesReportServiceInterface.evaluateRiskLevel(diabetesReport);

        return objectMapper.writeValueAsString(diabetesReport);
    }
}
