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

    @PostMapping("/insert")
    public String insert(@Valid Patient patient, BindingResult bindingResult, Model model) {
        logger.info("insert(" + "," + patient + "," + bindingResult + "," + model + ")");

        if (!bindingResult.hasErrors()) {

            patientServiceInterface.insert(patient);

            return ("redirect:/patient/list");
        }

        else {

            model.addAttribute("user", patient);

            return "/patient_add.html";
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam int id, @Valid Patient patient, BindingResult bindingResult, Model model) {
        logger.info("update(" + id + "," + patient + "," + bindingResult + "," + model + ")");

        if (!bindingResult.hasErrors()) {

            patientServiceInterface.update(id, patient);

            return ("redirect:/patient/list");
        }

        else {

            return "/patient_edit.html";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id, Model model) {
        logger.info("delete(" + id + "," + model + ")");

        patientServiceInterface.delete(id);

        return ("redirect:/patient/list");
    }
}