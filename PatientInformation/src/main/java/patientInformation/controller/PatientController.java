package patientInformation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import patientInformation.model.Patient;
import patientInformation.service.PatientService;
import patientInformation.service.PatientServiceInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private ObjectMapper objectMapper;

    @Autowired
    private PatientServiceInterface patientServiceInterface;

    public PatientController() {
        logger.info("PatientController()");

        objectMapper = new ObjectMapper();
        patientServiceInterface = new PatientService();
    }

    public PatientController(ObjectMapper objectMapper, PatientServiceInterface patientServiceInterface) {
        logger.info("PatientController(" + patientServiceInterface + "," + objectMapper + ")");

        this.objectMapper = objectMapper;
        this.patientServiceInterface = patientServiceInterface;
    }

    @PostMapping("/insert")
    public void insert(@RequestBody Patient patient, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("insert(" + patient + ")");

        String message = patientServiceInterface.insert(patient);

        if (message.equals("OK")) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(400, message);
        }
    }

    @GetMapping("/select")
    public String select(@RequestParam int id, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("select(" + id + ")");

        Patient patient = patientServiceInterface.select(id);

        if (patient != null) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(404, "Patient with (id = " + id + ") could not be found");
        }

        return objectMapper.writeValueAsString(patient);
    }

    @GetMapping("/list")
    public String list(HttpServletResponse httpServletResponse) throws IOException {
        logger.info("list()");

        List<Patient> patientList = patientServiceInterface.list();

        if (patientList != null) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(404, "No patient could be found");
        }

        return objectMapper.writeValueAsString(patientList);
    }

    @PutMapping("/update")
    public void update(@RequestParam int id, @RequestBody Patient patient, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("update(" + id + "," + patient + ")");

        String message = patientServiceInterface.update(id, patient);

        if (message.equals("OK")) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(400, message);
        }
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("delete(" + id + ")");

        String message = patientServiceInterface.delete(id);

        if (message.equals("OK")) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(400, message);
        }
    }
}