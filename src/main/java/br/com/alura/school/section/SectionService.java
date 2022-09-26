package br.com.alura.school.section;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseService;
import br.com.alura.school.enroll.EnrollService;
import br.com.alura.school.exceptions.NoContentException;
import br.com.alura.school.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final CourseService courseService;

    private final EnrollService enrollService;

    public SectionService(SectionRepository sectionRepository, CourseService courseService, EnrollService enrollService) {
        this.sectionRepository = sectionRepository;
        this.courseService = courseService;
        this.enrollService = enrollService;
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

    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    public List<Section> findSectionsFromEnrolledCourses() {
        List<Course> allEnrolledCourses = enrollService.findAllEnrolledCourses();
        List<List<Section>> superList =
                allEnrolledCourses
                        .stream()
                        .map(x -> x.getSections())
                        .collect(Collectors.toList());

        List<Section> sections = new ArrayList<>();
        superList
                .stream()
                .forEach(x -> sections.addAll(x));

        verifyNumberOfEnrollments(sections);

        return sections;
    }

    private void verifyNumberOfEnrollments(List<Section> sections) {
        if (sections.size() <= 0) {
            throw new NoContentException("Nenhum curso com matrícula encontrado");
        }
    }

}
