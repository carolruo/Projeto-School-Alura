package br.com.alura.school.section;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

class NewSectionRequest {

    @NotBlank
    private final String code;

    @NotBlank
    private final String title;

    @NotBlank
    private final String authorUsername;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewSectionRequest(@JsonProperty("code") String code, @JsonProperty("title") String title, @JsonProperty("authorUsername") String authorUsername) {
        this.code = code;
        this.title = title;
        this.authorUsername = authorUsername;
    }

    public String getCode() {
        return code;
    }

    Section toEntity() {
        return new Section(code, title, authorUsername);
    }
}
