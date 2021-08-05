package userInterface.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import userInterface.model.Note;

import java.util.List;

@Component
@FeignClient(name = "patientNote-api", url = "http://localhost:8082/note")
public interface NoteProxy {

    @PostMapping(value = "/insert", produces = "application/json")
    void insert(@RequestBody Note note);

    @GetMapping(value = "/select", produces = "application/json")
    Note select(@RequestParam String id);

    @GetMapping(value = "/list", produces = "application/json")
    List<Note> list(@RequestParam int patientId);

    @PutMapping(value = "/update", produces = "application/json")
    void update(@RequestParam String id, @RequestBody Note note);

    @DeleteMapping(value = "/delete", produces = "application/json")
    void delete(@RequestParam String id);
}
