package br.com.alura.school.video;

import br.com.alura.school.section.Section;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoResponse {

    @JsonProperty
    private final String video;

    @JsonProperty
    private final Section section;

    public VideoResponse(String video, Section section) {
        this.video = video;
        this.section = section;
    }

    private String abbreviateDescription(String description) {
        if (description.length() <= 13) return description;
        return description.substring(0, 10) + "...";
    }
}
