package br.com.alura.school.section;

import br.com.alura.school.course.Course;
import br.com.alura.school.support.validation.Unique;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

class NewSectionRequest {

    @Unique(entity = Section.class, field = "code")
    @NotBlank
    private final String code;
    @Unique(entity = Section.class, field = "title")
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

    public String getTitle() {
        return title;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    Section toEntity() {
        return new Section(code, title, authorUsername);
    }
}
