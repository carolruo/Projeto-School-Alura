package br.com.alura.school.video;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseService;
import br.com.alura.school.exceptions.DuplicateObjectException;
import br.com.alura.school.exceptions.ObjectNotFoundException;
import br.com.alura.school.section.Section;
import br.com.alura.school.section.SectionService;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    private final CourseService courseService;

    private final SectionService sectionService;


    public VideoService(VideoRepository videoRepository, CourseService courseService, SectionService sectionService) {
        this.videoRepository = videoRepository;
        this.courseService = courseService;
        this.sectionService = sectionService;
    }

    public void save(Video video, String courseCode, String sectionCode) {
        Course course = courseService.findByCode(courseCode);
        Section section = sectionService.findByCode(sectionCode);

        isDuplicated(video, section);
        video.setSection(section);
        section.addVideo(video);
        videoRepository.save(video);
    }

    private void isDuplicated(Video video, Section section) {
        boolean isDuplicatedVideo = section.getVideos().stream().anyMatch(video1 -> video1.equals(video));
        if (isDuplicatedVideo) {
            throw new DuplicateObjectException("Vídeo duplicado");
        }
    }
}
