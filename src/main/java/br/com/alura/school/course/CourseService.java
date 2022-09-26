package br.com.alura.school.course;

import br.com.alura.school.enroll.EnrollService;
import br.com.alura.school.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findByCode(String code) {
        Optional<Course> course = courseRepository.findByCode(code);
        return course.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Code: " + code + ", Tipo: " + Course.class.getName()
        ));
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public List<Course> findAllEnrolledCourses() {
        List<Course> coursesEnr = courseRepository.findDistinctByEnrolls();
        return coursesEnr;
    }

}
