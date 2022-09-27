package br.com.alura.school.course;

import br.com.alura.school.enroll.Enroll;
import br.com.alura.school.section.Section;
import br.com.alura.school.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max=10)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String code;

    @Size(max=20)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<Section> sections = new ArrayList<>();

    @OneToMany(mappedBy = "id.course")
    private List<Enroll> enrolls = new ArrayList<>();

    @Deprecated
    protected Course() { }

    public Course(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        for (Enroll x : enrolls) {
            list.add(x.getUser());
        }
        return list;
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<Enroll> getEnrolls() {
        return enrolls;
    }

    public void addEnroll(Enroll enroll) {
        this.enrolls.add(enroll);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(code, course.code) && Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name);
    }
}
