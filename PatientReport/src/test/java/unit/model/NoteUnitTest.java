package unit.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patientReport.model.Note;

import java.util.Date;

public class NoteUnitTest {

    private Note note;

    @BeforeEach
    public void beforeEach() {

        note = new Note(null, null, null);
    }

    @Test
    public void setAndGetId() {

        //GIVEN
        String id = "id";

        //WHEN
        note.setId(id);

        //THEN
        Assertions.assertThat(note.getId() == id);
    }

    @Test
    public void setAndGetPatientId() {

        //GIVEN
        Integer patientId = 1;

        //WHEN
        note.setPatientId(patientId);

        //THEN
        Assertions.assertThat(note.getPatientId() == patientId);
    }

    @Test
    public void setAndGetDate() {

        //GIVEN
        Date date = new Date();

        //WHEN
        note.setDate(date);

        //THEN
        Assertions.assertThat(note.getDate() == date);
    }

    @Test
    public void setAndGetCommentary() {

        //GIVEN
        String commentary = "commentary";

        //WHEN
        note.setCommentary(commentary);

        //THEN
        Assertions.assertThat(note.getCommentary() == commentary);
    }
}