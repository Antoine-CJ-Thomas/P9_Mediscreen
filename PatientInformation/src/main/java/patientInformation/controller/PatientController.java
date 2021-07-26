package patientInformation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import patientInformation.model.Patient;
import patientInformation.service.PatientService;
import patientInformation.service.PatientServiceInterface;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private ObjectMapper objectMapper = new ObjectMapper();

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
    public void insert(@RequestBody Patient patient) {
        logger.info("insert(" + patient + ")");

        patientServiceInterface.insert(patient);
    }

    @GetMapping("/select")
    public String select(@RequestParam int id) throws JsonProcessingException {
        logger.info("select(" + id + ")");

        return objectMapper.writeValueAsString(patientServiceInterface.select(id));
    }

    @GetMapping("/list")
    public String list() throws JsonProcessingException {
        logger.info("list()");

        return objectMapper.writeValueAsString(patientServiceInterface.list());
    }

    @PutMapping("/update")
    public void update(@RequestParam int id, @RequestBody Patient patient) {
        logger.info("update(" + id + "," + patient + ")");

        patientServiceInterface.update(id, patient);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) {
        logger.info("delete(" + id + ")");

        patientServiceInterface.delete(id);
    }
}
