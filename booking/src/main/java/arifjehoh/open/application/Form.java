package arifjehoh.open.application;

public class Form {
    private Long lesson;
    private Long student;

    public Form() {
    }

    public Form(Long lesson, Long student) {
        this.lesson = lesson;
        this.student = student;
    }

    public Long getLesson() {
        return lesson;
    }

    public void setLesson(Long lesson) {
        this.lesson = lesson;
    }

    public Long getStudent() {
        return student;
    }

    public void setStudent(Long student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Form{" +
                "lesson=" + lesson +
                ", student=" + student +
                '}';
    }
}
