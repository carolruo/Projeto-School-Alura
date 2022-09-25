package br.com.alura.school.video;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static java.lang.String.format;

@RestController
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/courses/{courseCode}/sections/{sectionCode}")
    ResponseEntity<Void> newVideo(@RequestBody @Valid NewVideoRequest newVideoRequest, @PathVariable String courseCode, @PathVariable String sectionCode) {
        videoService.save(newVideoRequest.toEntity(), courseCode, sectionCode);
        URI location = URI.create(format("/courses/%s/sections/%s", courseCode, sectionCode));
        return ResponseEntity.created(location).build();
    }
}
