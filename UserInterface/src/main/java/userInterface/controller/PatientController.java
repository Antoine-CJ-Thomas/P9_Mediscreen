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

/**
 * This class is used to intercept requests related to patient information
 */
@Controller
@RequestMapping("/patient")
public class PatientController {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private PatientServiceInterface patientServiceInterface;

    /**
     * Creates a new PatientController
     */
    public PatientController() {
        logger.info("PatientController()");

        patientServiceInterface = new PatientService();
    }

    /**
     * Creates a new PatientController with the specified PatientServiceInterface
     * @param patientServiceInterface : service that this controller will use
     */
    public PatientController(PatientServiceInterface patientServiceInterface) {
        logger.info("PatientController(" + patientServiceInterface + ")");

        this.patientServiceInterface = patientServiceInterface;
    }

    /**
     * Get the html page that allow to add a patient
     * @param patient : patient default data
     * @param model : holder for model attribute
     * @return The html page that allow add a patient
     */
    @GetMapping("/add")
    public String addPatient(Patient patient, Model model) {
        logger.info("addPatient(" + patient + "," + model + ")");

        model.addAttribute("patient", patient);

        return "patient_add.html";
    }

    /**
     * Perform a insert of a patient
     * @param patient : patient to add
     * @param bindingResult : holder for dataBinder result
     * @param model : holder for model attribute
     * @return The html page that allow to consult the patient list or the html page that allow add
     * a patient in case of binding error
     */
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

    /**
     * Get the html page that allow to edit a patient
     * @param patientId : id of the patient to edit
     * @param model : holder for model attribute
     * @return The html page that allow edit a patient
     */
    @GetMapping("/edit")
    public String editPatient(@RequestParam int patientId, Model model) {
        logger.info("editPatient(" + patientId + "," + model + ")");

        model.addAttribute("patient", patientServiceInterface.selectById(patientId));

        return "patient_edit.html";
    }

    /**
     * Perform a update of a note to a patient
     * @param patientId : id of the patient to update
     * @param patient : data to update to the patient
     * @param bindingResult : holder for dataBinder result
     * @param redirectAttributes : holder for redirect attribute
     * @param model : holder for model attribute
     * @return The html page that allow to consult the patient list or the html page that allow edit
     * a patient in case of binding error
     */
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

    /**
     * Perform a delete of a patient
     * @param patientId : id of the patient to delete
     * @param model : holder for model attribute
     * @return The html page that allow to consult the patient list
     */
    @GetMapping("/delete")
    public String delete(@RequestParam int patientId, Model model) {
        logger.info("delete(" + patientId + "," + model + ")");

        patientServiceInterface.delete(patientId);

        return ("redirect:/patient/list");
    }
}