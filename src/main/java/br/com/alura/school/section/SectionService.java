package br.com.alura.school.section;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseService;
import br.com.alura.school.enroll.EnrollService;
import br.com.alura.school.exceptions.DuplicateObjectException;
import br.com.alura.school.exceptions.NoContentException;
import br.com.alura.school.exceptions.ObjectNotFoundException;
import br.com.alura.school.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final CourseService courseService;

    private final EnrollService enrollService;

    private final UserService userService;

    public SectionService(SectionRepository sectionRepository, CourseService courseService, EnrollService enrollService, UserService userService) {
        this.sectionRepository = sectionRepository;
        this.courseService = courseService;
        this.enrollService = enrollService;
        this.userService = userService;
    }

    public void save(Section section, String code) {
        Course course = courseService.findByCode(code);
        userService.isUserInstructor(section.getAuthorUsername());

        verifySectionDuplication(section, course);

        section.setCourse(course);
        course.addSection(section);
        sectionRepository.save(section);
    }

    private void verifySectionDuplication(Section section, Course course) {
        List<Section> sections = course.getSections();

        if (sections.stream().anyMatch(s -> s.getCode().equals(section.getCode()))) {
            throw new DuplicateObjectException("A aula já existe dentro do curso. Código: " + section.getCode());
        }
    }

    public Section findByCodeAndCourse(String sectionCode, Course course) {
        Optional<Section> section = sectionRepository.findByCodeAndCourse(sectionCode, course);
        return section.orElseThrow(() -> new ObjectNotFoundException(
                "Aula não encontrada! Code: " + sectionCode + ", Tipo: " + Section.class.getName()
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
                        .map(Course::getSections).toList();

        List<Section> sections = new ArrayList<>();
        superList.forEach(sections::addAll);

        verifyNumberOfEnrollments(sections);

        return sections;
    }

    private void verifyNumberOfEnrollments(List<Section> sections) {
        if (sections.isEmpty()) {
            throw new NoContentException("Nenhum curso com matrícula encontrado");
        }
    }
}
