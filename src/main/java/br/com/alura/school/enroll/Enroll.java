package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enrolls")
public class Enroll {

    @EmbeddedId
    private EnrollPK id = new EnrollPK();

    private LocalDate data;

    public void setId(EnrollPK id) {
        this.id = id;
    }

    public Enroll() {
    }

    public Enroll(User user, Course course) {
        this.id.setUser(user);
        this.id.setCourse(course);
        this.data = LocalDate.now();
    }

    public User getUser() {
        return id.getUser();
    }

    public Course getCourse() {
        return id.getCourse();
    }

    public EnrollPK getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }


}
