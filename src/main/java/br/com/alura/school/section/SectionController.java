package br.com.alura.school.section;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RestController
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping("/courses/{code}/sections")
    ResponseEntity<Void> newSection(@RequestBody @Valid NewSectionRequest newSectionRequest, @PathVariable String code) {
        sectionService.save(newSectionRequest.toEntity(), code);
        URI location = URI.create(format("/courses/%s", newSectionRequest.getCode()));
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/sectionByVideosReport")
    ResponseEntity<List<SectionResponse>> sectionByVideosReport() {
        List<Section> sections = sectionService.findSectionsFromEnrolledCourses();
        List<SectionResponse> sectionResponses = sections.stream().map(obj -> new SectionResponse(obj)).collect(Collectors.toList());
        sectionResponses.sort((s1, s2) -> s1.compareTo(s2));
        return ResponseEntity.ok().body(sectionResponses);
    }

}
