package br.com.alura.school.enroll;

import br.com.alura.school.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class NewEnrollRequest {

    @NotBlank
    private final String username;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewEnrollRequest(@JsonProperty("username") String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

//    User toEntity() {
//        return new Enroll(username);
//    }


    @Override
    public String toString() {
        return username;
    }
}
