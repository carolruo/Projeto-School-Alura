package br.com.alura.school.course;

import br.com.alura.school.support.validation.Unique;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

class NewCourseRequest {

    @Unique(entity = Course.class, field = "code")
    @Size(max=10)
    @NotBlank
    private final String code;

    @Unique(entity = Course.class, field = "name")
    @Size(max=20)
    @NotBlank
    private final String name;

    private final String description;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewCourseRequest(@JsonProperty("code") String code, @JsonProperty("name") String name, @JsonProperty("description") String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    Course toEntity() {
        return new Course(code, name, description);
    }
}
