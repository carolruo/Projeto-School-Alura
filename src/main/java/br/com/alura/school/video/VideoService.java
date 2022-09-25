package br.com.alura.school.video;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseService;
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

    public void save(Video toEntity, String courseCode, String sectionCode) {
        Course course = courseService.findByCode(courseCode);
        Section section = sectionService.findByCode(sectionCode);

        videoRepository.save(toEntity);
        toEntity.setSection(section);
        section.addVideo(toEntity);

    }
}
