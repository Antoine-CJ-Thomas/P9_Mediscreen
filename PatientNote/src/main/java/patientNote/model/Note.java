package patientNote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * This class is used to stores the data of a note
 */
@Document(collection = "patient_note")
public class Note {

    @Id
    private String id;

    @Field(value = "patientId")
    private Integer patientId;

    @Field(value = "date")
    private Date date;

    @Field(value = "commentary")
    private String commentary;

    public Note() {

    }

    public Note(Integer patientId, Date date, String commentary) {

        this.patientId = patientId;
        this.date = date;
        this.commentary = commentary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}