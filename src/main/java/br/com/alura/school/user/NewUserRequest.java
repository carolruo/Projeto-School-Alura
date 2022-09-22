package br.com.alura.school.user;

import br.com.alura.school.support.validation.Unique;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


class NewUserRequest {

    @Unique(entity = User.class, field = "username")
    @Size(max=20)
    @NotBlank
    @JsonProperty("username")
    private final String username;

    @Unique(entity = User.class, field = "email")
    @NotBlank
    @Email
    @JsonProperty("email")
    private final String email;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    NewUserRequest(@JsonProperty("username") String username, @JsonProperty("email") String email) {
        this.username = username;
        this.email = email;
    }

    String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    User toEntity() {
        return new User(username, email);
    }
}
