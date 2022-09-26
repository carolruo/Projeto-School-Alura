package br.com.alura.school.enroll;

import br.com.alura.school.section.NewSectionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static java.lang.String.format;

@RestController
public class EnrollController {

    private final EnrollService enrollService

    @PostMapping("/courses/{courseCode}/enroll")
    ResponseEntity<Void> newSection(@RequestBody @Valid NewEnrollRequest username, @PathVariable String courseCode) {
        enrollService.save(username, courseCode);
        URI location = URI.create(format("/courses/%s", newSectionRequest.getCode()));
        return ResponseEntity.created(location).build();
    }
}
