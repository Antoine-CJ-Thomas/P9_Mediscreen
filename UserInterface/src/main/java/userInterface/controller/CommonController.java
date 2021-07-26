package userInterface.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String openPatient(@RequestParam int id, Model model) {
        logger.info("openPatient(" + model + ")");

        model.addAttribute("patient", patientServiceInterface.select(id));
        model.addAttribute("noteList", noteServiceInterface.list(id));

        return "/patient_open.html";
    }

    @GetMapping("/patient/add")
    public String addPatient(Patient patient, Model model) {
        logger.info("addPatient(" + model + ")");

        model.addAttribute("patient", patient);

        return "/patient_add.html";
    }

    @GetMapping("/patient/edit")
    public String editPatient(@RequestParam int id, Model model) {
        logger.info("editPatient(" + id + "," + model + ")");

        model.addAttribute("patient", patientServiceInterface.select(id));

        return "/patient_edit.html";
    }
}