package br.com.alura.school.course;

import br.com.alura.school.enroll.Enroll;
import br.com.alura.school.section.Section;
import br.com.alura.school.user.User;
import br.com.alura.school.video.Video;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
    private List<Section> sections = new ArrayList<>();

    @OneToMany(mappedBy = "id.course")
    private Set<Enroll> enrolls = new HashSet<>();


    @Deprecated
    protected Course() { }

    Course(String code, String name, String description) {
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

    public Set<Enroll> getEnrolls() {
        return enrolls;
    }

    public void addEnroll(Enroll enroll) {
        this.enrolls.add(enroll);
    }

    String getCode() {
        return code;
    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    public void addSection(Section section) {
        sections.add(section);
    }
}
