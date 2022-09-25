package br.com.alura.school.video;

import br.com.alura.school.section.Section;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    private String video;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @Deprecated
    public Video() {
    }

    public Video(String video) {
        this.video = video;
    }

    public Long getId() {
        return id;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}