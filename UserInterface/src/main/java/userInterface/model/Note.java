package userInterface.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Component
public class Note {

    @NotBlank
    @Size(min=4, max=32)
    private Integer patientId;

    @NotBlank
    @Size(min=4, max=32)
    private Date date;

    @NotBlank
    @Size(min=4, max=32)
    private String commentary;

    public Note() {

    }

    public Note(Integer patientId, Date date, String commentary) {

        this.patientId = patientId;
        this.date = date;
        this.commentary = commentary;
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