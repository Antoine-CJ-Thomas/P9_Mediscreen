package userInterface.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import userInterface.model.Note;

import java.util.List;

@Component
@FeignClient(name = "PatientNote", url = "http://localhost:8082/note")
public interface NoteProxy {

    @GetMapping(value = "/list", produces = "application/json")
    List<Note> list(@RequestParam int id);
}
