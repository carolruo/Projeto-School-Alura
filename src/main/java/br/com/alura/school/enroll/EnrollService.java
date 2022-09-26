package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseService;
import br.com.alura.school.user.User;
import org.springframework.stereotype.Service;

@Service
public class EnrollService {

    private final EnrollRepository enrollRepository;
    private final CourseService courseService;
    private final UserService userService;

    public EnrollService(EnrollRepository enrollRepository, CourseService courseService) {
        this.enrollRepository = enrollRepository;
        this.courseService = courseService;
    }

    public void save(NewEnrollRequest username, String courseCode) {
        Course course = courseService.findByCode(courseCode);
        User user = userService.findByUsername(username);
//        enrollRepository.save(matricula);
    }
}
