package userInterface.service;

import org.springframework.stereotype.Component;
import userInterface.model.Note;

import java.util.List;

@Component
public interface NoteServiceInterface {

    List<Note> list(int id);
}
