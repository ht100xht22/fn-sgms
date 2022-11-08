package arifjehoh.open.application;

public class Form {
    private Long instrument;
    private Long student;

    public Form() {

    }

    public Form(Long instrument, Long student) {
        this.instrument = instrument;
        this.student = student;
    }

    public Long getInstrument() {
        return instrument;
    }

    public Long getStudent() {
        return student;
    }

    public boolean isEmpty() {
        return instrument == null && student == null;
    }

}
