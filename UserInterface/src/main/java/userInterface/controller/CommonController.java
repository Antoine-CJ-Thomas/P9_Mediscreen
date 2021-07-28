package userInterface.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
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

@Controller
public class CommonController {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private PatientServiceInterface patientServiceInterface;

    @Autowired
    private NoteServiceInterface noteServiceInterface;

    public CommonController() {
        logger.info("HomeController()");

        patientServiceInterface = new PatientService();
    }

    public CommonController(PatientServiceInterface patientServiceInterface,
                            NoteServiceInterface noteServiceInterface) {
        logger.info("HomeController(" + patientServiceInterface + ")");

        this.patientServiceInterface = patientServiceInterface;
        this.noteServiceInterface = noteServiceInterface;
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

        model.addAttribute("patient", patientServiceInterface.select(patientId));
        model.addAttribute("noteList", noteServiceInterface.list(patientId));

        return "/patient_open.html";
    }
}