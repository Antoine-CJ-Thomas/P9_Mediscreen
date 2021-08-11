package userInterface.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import userInterface.model.Note;

import java.util.List;

/**
 * This interface is used to interact with the patient note API
 */
@Component
@FeignClient(name = "patientNote-api", url = "http://localhost:8082/note")
public interface NoteProxy {

    /**
     * Insert a new Note in the database
     * @param note : note to add in the database
     */
    @PostMapping(value = "/insert", produces = "application/json")
    void insert(@RequestBody Note note);

    /**
     * Select a Note in the database
     * @param id : id of the note to select
     * @return The selected note
     */
    @GetMapping(value = "/select", produces = "application/json")
    Note select(@RequestParam String id);

    /**
     * Select the Note list in the database
     * @param patientId : id of the patient to find
     * @return The selected note list
     */
    @GetMapping(value = "/list", produces = "application/json")
    List<Note> list(@RequestParam int patientId);

    /**
     * Update the Note in the database
     * @param id : id of the note to update
     * @param note : data of the note to update
     */
    @PutMapping(value = "/update", produces = "application/json")
    void update(@RequestParam String id, @RequestBody Note note);

    /**
     * Delete the Note in the database
     * @param id : id of the note to delete
     */
    @DeleteMapping(value = "/delete", produces = "application/json")
    void delete(@RequestParam String id);
}