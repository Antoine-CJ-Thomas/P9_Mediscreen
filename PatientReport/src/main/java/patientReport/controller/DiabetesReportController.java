package patientReport.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import patientReport.model.DiabetesReport;
import patientReport.model.Note;
import patientReport.model.Patient;
import patientReport.service.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to intercept requests related to diabetes report
 */
@RestController
public class DiabetesReportController {

    private ObjectMapper objectMapper;

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private PatientServiceInterface patientServiceInterface;

    @Autowired
    private NoteServiceInterface noteServiceInterface;

    @Autowired
    private DiabetesReportServiceInterface diabetesReportServiceInterface;

    /**
     * Creates a new DiabetesReportController
     */
    public DiabetesReportController() {
        logger.info("DiabetesReportController()");

        objectMapper = new ObjectMapper();

        patientServiceInterface = new PatientService();
        noteServiceInterface = new NoteService();
        diabetesReportServiceInterface = new DiabetesReportService();
    }

    /**
     * Creates a new DiabetesReportController with the specified ObjectMapper and DiabetesReportServiceInterface
     * @param objectMapper : mapper that this controller will use
     * @param patientServiceInterface : patient service that this controller will use
     * @param noteServiceInterface : note service that this controller will use
     * @param diabetesReportServiceInterface : report service that this controller will use
     */
    public DiabetesReportController(ObjectMapper objectMapper,
                                    PatientServiceInterface patientServiceInterface,
                                    NoteServiceInterface noteServiceInterface,
                                    DiabetesReportServiceInterface diabetesReportServiceInterface) {

        logger.info("DiabetesReportController(" + objectMapper + "," + patientServiceInterface +  ","
                + noteServiceInterface +  "," + diabetesReportServiceInterface + ")");

        this.objectMapper = objectMapper;

        this.patientServiceInterface = patientServiceInterface;
        this.noteServiceInterface = noteServiceInterface;
        this.diabetesReportServiceInterface = diabetesReportServiceInterface;
    }

    /**
     * Complete a diabetes report by evaluating the risk level of contracting it
     * @param diabetesReport : diabetes report that contains needed patient informations
     * @param httpServletResponse : http response
     * @return The diabetes report completed (JSon)
     * @throws IOException : if httpServletResponse is null
     */
    @PostMapping("/assess/diabetes")
    public String assessDiabetes(@RequestBody DiabetesReport diabetesReport, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("assessDiabetes(" + diabetesReport + "," + httpServletResponse + ")");

        String message = diabetesReportServiceInterface.assessDiabetes(diabetesReport);

        if (message.equals("OK")) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(400, "Diabetes assessment could not be done");
        }

        return objectMapper.writeValueAsString(diabetesReport);
    }

    /**
     * Create a diabetes report by evaluating the risk level of contracting it
     * @param patientId : if of the patient for diabetes risk assessment
     * @param httpServletResponse : http response
     * @return The diabetes report completed (JSon)
     * @throws IOException : if httpServletResponse is null
     */
    @PostMapping("/assess/diabetesById")
    public String assessDiabetesById(@RequestParam int patientId, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("assessDiabetesById(" + patientId + "," + httpServletResponse + ")");

        Patient patient = patientServiceInterface.selectById(patientId);
        List<Note> noteList = noteServiceInterface.list(patientId);

        List<String> commentaryList = new ArrayList<>();

        for (Note n : noteList) {

            commentaryList.add(n.getCommentary());
        }

        DiabetesReport diabetesReport = new DiabetesReport(patient.getGender(), patient.getBirthDate(), commentaryList);

        String message = diabetesReportServiceInterface.assessDiabetes(diabetesReport);

        if (message.equals("OK")) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(400, "Diabetes assessment could not be done");
        }

        return objectMapper.writeValueAsString(diabetesReport);
    }

    /**
     * Create a diabetes report by evaluating the risk level of contracting it
     * @param lastName : last name of the patient for diabetes risk assessment
     * @param httpServletResponse : http response
     * @return The diabetes report completed (JSon)
     * @throws IOException : if httpServletResponse is null
     */
    @PostMapping("/assess/diabetesByLastName")
    public String assessDiabetesByLastName(@RequestParam String lastName, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("assessDiabetesByLastName(" + lastName + "," + httpServletResponse + ")");

        Patient patient = patientServiceInterface.selectByLastName(lastName);
        List<Note> noteList = noteServiceInterface.list(patient.getId());

        List<String> commentaryList = new ArrayList<>();

        for (Note n : noteList) {

            commentaryList.add(n.getCommentary());
        }

        DiabetesReport diabetesReport = new DiabetesReport(patient.getGender(), patient.getBirthDate(), commentaryList);

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