package br.com.alura.school.video;

import br.com.alura.school.section.Section;
import br.com.alura.school.support.validation.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Objects;

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
    protected Video() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video1 = (Video) o;
        return Objects.equals(video, video1.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(video);
    }
}