package br.com.alura.school.section;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseService;
import org.springframework.stereotype.Service;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final CourseService courseService;


    public SectionService(SectionRepository sectionRepository, CourseService courseService) {
        this.sectionRepository = sectionRepository;
        this.courseService = courseService;
    }

    public void save(Section section, String code) {
        Course course = courseService.findByCode(code);
        sectionRepository.save(section);
        course.addSection(section);
    }
}
