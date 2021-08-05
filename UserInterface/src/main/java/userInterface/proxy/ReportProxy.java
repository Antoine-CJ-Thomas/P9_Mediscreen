package userInterface.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import userInterface.model.DiabetesReport;

import java.util.Date;
import java.util.List;

@Component
@FeignClient(name = "patientReport-api", url = "http://localhost:8083/assess")
public interface ReportProxy {

    @PostMapping(value = "/diabetes", produces = "application/json")
    DiabetesReport assessDiabetes(@RequestBody DiabetesReport diabetesReport);
}
