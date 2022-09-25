package br.com.alura.school.video;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class NewVideoRequest {

    @NotBlank
    @JsonProperty("video")
    private final String video;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewVideoRequest(@JsonProperty("video") String video) {
        this.video = video;
    }

    public String getVideo() {
        return video;
    }

    Video toEntity() {
        return new Video(video);
    }
}
