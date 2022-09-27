package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseService;
import br.com.alura.school.exceptions.DuplicateObjectException;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public void save(String username, String courseCode) {
        Course course = courseService.findByCode(courseCode);
        User user = userService.findByUsername(username);

        List<Enroll> enrolls = course.getEnrolls();
        Enroll enroll = new Enroll(user, course);

        isUserEnrolled(enrolls, enroll);

        course.addEnroll(enroll);
        user.addCourseEnroll(enroll);
        enrollRepository.save(enroll);
    }

    private boolean isUserEnrolled(List<Enroll> enrolls, Enroll enroll) {
        for (Enroll e : enrolls) {
            if (e.getUser().equals(enroll.getUser())) {
                throw new DuplicateObjectException("O Aluno já está matriculado. Aluno: " + enroll.getUser());
            }
        }
        return true;
    }

    public List<Course> findAllEnrolledCourses() {
        return enrollRepository.findDistinctByCourse_id();
    }
}
