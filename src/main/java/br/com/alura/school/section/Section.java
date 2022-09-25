package br.com.alura.school.section;

import br.com.alura.school.course.Course;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
//    @ManyToOne
    @JoinColumn(name = "course_id")
    private String code;

//    @NotBlank
//    private String code;
    @Size(min = 5)
    @NotBlank
    private String title;
    @NotBlank
    private String authorUsername;

    @Deprecated
    protected Section() {
    }

    public Section(String code, String title, String authorUsername) {
        this.code = code;
        this.title = title;
        this.authorUsername = authorUsername;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

}
