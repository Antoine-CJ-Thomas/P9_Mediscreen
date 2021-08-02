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
import userInterface.service.PatientService;
import userInterface.service.PatientServiceInterface;
import userInterface.service.NoteServiceInterface;
import userInterface.service.ReportServiceInterface;

import java.util.List;

@Controller
public class CommonController {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private PatientServiceInterface patientServiceInterface;

    @Autowired
    private NoteServiceInterface noteServiceInterface;

    @Autowired
    private ReportServiceInterface reportServiceInterface;

    public CommonController() {
        logger.info("CommonController()");

        patientServiceInterface = new PatientService();
    }

    public CommonController(PatientServiceInterface patientServiceInterface,
                            NoteServiceInterface noteServiceInterface,
                            ReportServiceInterface reportServiceInterface) {

        logger.info("CommonController(" + patientServiceInterface + ")");

        this.patientServiceInterface = patientServiceInterface;
        this.noteServiceInterface = noteServiceInterface;
        this.reportServiceInterface = reportServiceInterface;
    }

    @GetMapping("/patient/list")
    public String listPatient(Model model) {
        logger.info("listPatient(" + model + ")");

        model.addAttribute("patientList", patientServiceInterface.list());

        return "/patient_list.html";
    }

    @GetMapping("/patient/open")
    public String openPatient(@RequestParam int patientId, Model model) {
        logger.info("openPatient(" + patientId + "," + model + ")");

        Patient patient = patientServiceInterface.select(patientId);
        List<Note> noteList = noteServiceInterface.list(patientId);

        model.addAttribute("patient", patient);
        model.addAttribute("noteList", noteList);
        model.addAttribute("diabetesReport", reportServiceInterface.getDiabetesReport(patient, noteList));

        return "/patient_open.html";
    }
}