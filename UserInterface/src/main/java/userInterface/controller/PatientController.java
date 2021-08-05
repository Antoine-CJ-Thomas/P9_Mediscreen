package userInterface.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import userInterface.model.Patient;
import userInterface.service.PatientService;
import userInterface.service.PatientServiceInterface;

import javax.validation.Valid;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private PatientServiceInterface patientServiceInterface;

    public PatientController() {
        logger.info("PatientController()");

        patientServiceInterface = new PatientService();
    }

    public PatientController(PatientServiceInterface patientServiceInterface) {
        logger.info("PatientController(" + patientServiceInterface + ")");

        this.patientServiceInterface = patientServiceInterface;
    }

    @GetMapping("/add")
    public String addPatient(Patient patient, Model model) {
        logger.info("addPatient(" + patient + "," + model + ")");

        model.addAttribute("patient", patient);

        return "patient_add.html";
    }

    @PostMapping("/insert")
    public String insert(@Valid Patient patient, BindingResult bindingResult, Model model) {
        logger.info("insert(" + patient + "," + bindingResult + "," + model + ")");

        if (!bindingResult.hasErrors()) {

            patientServiceInterface.insert(patient);

            return ("redirect:/patient/list");
        }

        else {

            model.addAttribute("user", patient);

            return "patient_add.html";
        }
    }

    @GetMapping("/edit")
    public String editPatient(@RequestParam int patientId, Model model) {
        logger.info("editPatient(" + patientId + "," + model + ")");

        model.addAttribute("patient", patientServiceInterface.select(patientId));

        return "patient_edit.html";
    }

    @PostMapping("/update")
    public String update(@RequestParam int patientId, @Valid Patient patient, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        logger.info("update(" + patientId + "," + patient + "," + bindingResult + "," + model + ")");

        if (!bindingResult.hasErrors()) {

            patientServiceInterface.update(patientId, patient);

            redirectAttributes.addAttribute("patientId", patientId);

            return ("redirect:/patient/open");
        }

        else {

            return "patient_edit.html";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int patientId, Model model) {
        logger.info("delete(" + patientId + "," + model + ")");

        patientServiceInterface.delete(patientId);

        return ("redirect:/patient/list");
    }
}