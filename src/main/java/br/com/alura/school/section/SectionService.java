package br.com.alura.school.section;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseService;
import br.com.alura.school.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        section.setCourse(course);
        sectionRepository.save(section);
        course.addSection(section);
    }

    public Section findByCode(String sectionCode) {
        Optional<Section> course = sectionRepository.findByCode(sectionCode);
        return course.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Code: " + sectionCode + ", Tipo: " + Section.class.getName()
        ));
    }
}
