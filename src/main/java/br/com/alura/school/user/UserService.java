package br.com.alura.school.user;

import br.com.alura.school.course.Course;
import br.com.alura.school.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Username: " + username + ", Tipo: " + User.class.getName()
        ));
    }
}
