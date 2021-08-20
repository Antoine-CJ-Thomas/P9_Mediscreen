package patientReport.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import patientReport.model.Patient;

import java.util.List;

/**
 * This interface is used to interact with the patient information API
 */
@Component
@FeignClient(name = "patientInformation-api", url = "http://patientInformation-api:8081/patient")
public interface PatientProxy {

    /**
     * Select a Patient in the database by his id
     * @param id : id of the patient to select
     * @return The selected patient
     */
    @GetMapping(value = "/selectById", produces = "application/json")
    Patient selectById(@RequestParam int id);

    /**
     * Select a Patient in the database by his last name
     * @param lastName : last name of the patient to select
     * @return The selected patient
     */
    @GetMapping(value = "/selectByLastName", produces = "application/json")
    Patient selectByLastName(@RequestParam String lastName);
}