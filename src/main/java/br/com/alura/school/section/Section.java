package br.com.alura.school.section;

import br.com.alura.school.course.Course;
import br.com.alura.school.support.validation.Unique;
import br.com.alura.school.video.Video;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    private String code;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Size(min = 5)
    @NotBlank
    private String title;

    @NotBlank
    private String authorUsername;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "section")
    private List<Video> videos = new ArrayList<>();

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

    public Course getCourse() {
        return course;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void addVideo(Video newVideo) {
        this.videos.add(newVideo);
    }
}
