package br.com.alura.school.course;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RestController
class CourseController {

    private final CourseService courseService;

    CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/courses")
    ResponseEntity<List<CourseResponse>> allCourses() {
        List<Course> courses = courseService.findAll();
        List<CourseResponse> courseResponses = courses.stream().map(CourseResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(courseResponses);
    }

    @GetMapping("/courses/{code}")
    ResponseEntity<CourseResponse> courseByCode(@PathVariable("code") String code) {
        Course course = courseService.findByCode(code);
        return ResponseEntity.ok(new CourseResponse(course));
    }

    @PostMapping("/courses")
    ResponseEntity<Void> newCourse(@RequestBody @Valid NewCourseRequest newCourseRequest) {
        courseService.save(newCourseRequest.toEntity());
        URI location = URI.create(format("/courses/%s", newCourseRequest.getCode()));
        return ResponseEntity.created(location).build();
    }
}
