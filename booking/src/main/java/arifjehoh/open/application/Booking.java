package arifjehoh.open.application;

import javax.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    private Long lesson;
    private Long student;
    private Long instructor;

    public Booking() {
    }

    public Booking(Form form) {
        this.lesson = form.getLesson();
        this.student = form.getStudent();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getInstructor() {
        return instructor;
    }

    public void setInstructor(Long instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", lesson=" + lesson +
                ", student=" + student +
                ", instructor=" + instructor +
                '}';
    }
}
