package userInterface.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import userInterface.model.Patient;

import java.util.List;

/**
 * This interface is used to interact with the patient information API
 */
@Component
@FeignClient(name = "patientInformation-api", url = "http://localhost:8081/patient")
public interface PatientProxy {

    /**
     * Insert a new Patient in the database
     * @param patient : patient to insert in the database
     */
    @PostMapping(value = "/insert", produces = "application/json")
    void insert(@RequestBody Patient patient);

    /**
     * Select a Patient in the database
     * @param id : id of the patient to select
     * @return The selected patient
     */
    @GetMapping(value = "/select", produces = "application/json")
    Patient select(@RequestParam int id);

    /**
     * Select the Patient list in the database
     * @return The selected patient list
     */
    @GetMapping(value = "/list", produces = "application/json")
    List<Patient> list();

    /**
     * Update the Patient in the database
     * @param id : id of the patient to update
     * @param patient : data of the patient to update
     */
    @PutMapping(value = "/update", produces = "application/json")
    void update(@RequestParam int id, @RequestBody Patient patient);

    /**
     * Delete the Patient in the database
     * @param id : id of the patient to delete
     */
    @DeleteMapping(value = "/delete", produces = "application/json")
    void delete(@RequestParam int id);
}