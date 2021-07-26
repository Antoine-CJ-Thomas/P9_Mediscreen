package patientNote.service;

import patientNote.model.Note;

import java.util.List;

public interface NoteServiceInterface {

    List<Note> list(int patientId);
}
