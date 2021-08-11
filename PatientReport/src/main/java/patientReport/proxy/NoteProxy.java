package patientReport.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import patientReport.model.Note;

import java.util.List;

/**
 * This interface is used to interact with the patient note API
 */
@Component
@FeignClient(name = "patientNote-api", url = "http://patientNote-api:8082/note")
public interface NoteProxy {

    /**
     * Select the Note list in the database
     * @param patientId : id of the patient to find
     * @return The selected note list
     */
    @GetMapping(value = "/list", produces = "application/json")
    List<Note> list(@RequestParam int patientId);
}