package userInterface.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import userInterface.model.Patient;

import java.util.List;

@Component
@FeignClient(name = "patientInformation-api", url = "http://localhost:8081/patient")
public interface PatientProxy {

    @PostMapping(value = "/insert", produces = "application/json")
    void insert(@RequestBody Patient patient);

    @GetMapping(value = "/select", produces = "application/json")
    Patient select(@RequestParam int id);

    @GetMapping(value = "/list", produces = "application/json")
    List<Patient> list();

    @PutMapping(value = "/update", produces = "application/json")
    void update(@RequestParam int id, @RequestBody Patient patient);

    @DeleteMapping(value = "/delete", produces = "application/json")
    void delete(@RequestParam int id);
}
