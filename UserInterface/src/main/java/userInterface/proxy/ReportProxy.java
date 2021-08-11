package userInterface.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import userInterface.model.DiabetesReport;

import java.util.Date;
import java.util.List;

/**
 * This interface is used to interact with the patient report API
 */
@Component
@FeignClient(name = "patientReport-api", url = "http://patientReport-api:8083/assess")
public interface ReportProxy {

    /**
     * Complete a diabetes report by evaluating the risk level of contracting it
     * @param diabetesReport : diabetes report that contains needed patient informations
     * @return The diabetes report completed
     */
    @PostMapping(value = "/diabetes", produces = "application/json")
    DiabetesReport assessDiabetes(@RequestBody DiabetesReport diabetesReport);
}
