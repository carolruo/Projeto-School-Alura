package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseService;
import br.com.alura.school.exceptions.DuplicateEnrollmentException;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EnrollService {

    private final EnrollRepository enrollRepository;
    private final CourseService courseService;
    private final UserService userService;

    public EnrollService(EnrollRepository enrollRepository, CourseService courseService, UserService userService) {
        this.enrollRepository = enrollRepository;
        this.courseService = courseService;
        this.userService = userService;
    }

    public void save(String username, String courseCode) {
        Course course = courseService.findByCode(courseCode);
        User user = userService.findByUsername(username);

        Set<Enroll> enrolls = course.getEnrolls();
        Enroll enroll = new Enroll(user, course);

        verifyEnrollDuplicity(enrolls, enroll);

        course.addEnroll(enroll);
        user.addCourseEnroll(enroll);
        enrollRepository.save(enroll);
    }

    private void verifyEnrollDuplicity(Set<Enroll> enrolls, Enroll enroll) {
        boolean contains = enrolls.contains(enroll);
        if (contains) {
            throw new DuplicateEnrollmentException("Aluno já matriculado");
        }
    }

    public List<Course> findAllEnrolledCourses() {
        return enrollRepository.findDistinctByCourse_id();
    }

}
