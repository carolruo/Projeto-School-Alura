package br.com.alura.school.enroll;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EnrollController {

    private final EnrollService enrollService;

    public EnrollController(EnrollService enrollService) {
        this.enrollService = enrollService;
    }

    @PostMapping("/courses/{courseCode}/enroll")
    ResponseEntity<Void> newSection(@RequestBody @Valid NewEnrollRequest username, @PathVariable String courseCode) {
        enrollService.save(username.toString(), courseCode);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
