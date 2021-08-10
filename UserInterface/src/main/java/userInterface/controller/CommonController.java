package userInterface.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import userInterface.model.Note;
import userInterface.model.Patient;
import userInterface.service.*;

import java.util.List;

/**
 * This class is used to intercept common requests
 */
@Controller
public class CommonController {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private PatientServiceInterface patientServiceInterface;

    @Autowired
    private NoteServiceInterface noteServiceInterface;

    @Autowired
    private ReportServiceInterface reportServiceInterface;

    /**
     * Creates a new CommonController
     */
    public CommonController() {
        logger.info("CommonController()");

        patientServiceInterface = new PatientService();
        noteServiceInterface = new NoteService();
        reportServiceInterface = new ReportService();
    }

    /**
     * Creates a new CommonController with the specified PatientServiceInterface
     * @param patientServiceInterface : patient service that this controller will use
     * @param noteServiceInterface : note service that this controller will use
     * @param reportServiceInterface : report service that this controller will use
     */
    public CommonController(PatientServiceInterface patientServiceInterface,
                            NoteServiceInterface noteServiceInterface,
                            ReportServiceInterface reportServiceInterface) {

        logger.info("CommonController(" + patientServiceInterface + "," +  noteServiceInterface + "," + reportServiceInterface + ")");

        this.patientServiceInterface = patientServiceInterface;
        this.noteServiceInterface = noteServiceInterface;
        this.reportServiceInterface = reportServiceInterface;
    }

    /**
     * Get the html page that allow to consult the patient list
     * @param model : holder for model attribute
     * @return The html page that allow to consult the patient list
     */
    @GetMapping("/patient/list")
    public String listPatient(Model model) {
        logger.info("listPatient(" + model + ")");

        model.addAttribute("patientList", patientServiceInterface.list());

        return "patient_list.html";
    }

    /**
     * Get the html page that allow to consult the medical record of a patient
     * @param patientId : id of the patient
     * @param model : holder for model attribute
     * @return The html page that allow to consult the medical record of the patient
     */
    @GetMapping("/patient/open")
    public String openPatient(@RequestParam int patientId, Model model) {
        logger.info("openPatient(" + patientId + "," + model + ")");

        Patient patient = patientServiceInterface.select(patientId);
        List<Note> noteList = noteServiceInterface.list(patientId);

        model.addAttribute("patient", patient);
        model.addAttribute("noteList", noteList);
        model.addAttribute("diabetesReport", reportServiceInterface.getDiabetesReport(patient, noteList));

        return "patient_open.html";
    }
}