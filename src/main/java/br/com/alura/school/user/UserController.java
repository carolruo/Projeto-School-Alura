package br.com.alura.school.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static java.lang.String.format;

@RestController
class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{username}")
    ResponseEntity<UserResponse> userByUsername(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(new UserResponse(user));
    }

    @PostMapping("/users")
    ResponseEntity<Void> newUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        userService.save(newUserRequest.toEntity());
        URI location = URI.create(format("/users/%s", newUserRequest.getUsername()));
        return ResponseEntity.created(location).build();
    }
}
