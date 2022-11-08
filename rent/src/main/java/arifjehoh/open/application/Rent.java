package arifjehoh.open.application;

import javax.persistence.*;

@Entity
@Table(name = "rent")
public class Rent {
    @Id
    @GeneratedValue
    private Long id;
    private Long instrumentId;
    private Long studentId;

    public Rent() {
    }

    public Rent(Form form) {
        this.instrumentId = form.getInstrument();
        this.studentId = form.getStudent();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Long instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
