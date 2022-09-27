package br.com.alura.school.user;

import br.com.alura.school.exceptions.ObjectNotFoundException;
import br.com.alura.school.exceptions.UnauthorizedRoleAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Usuário não encontrado! Username: " + username + ", Tipo: " + User.class.getName()
        ));
    }

    public boolean isUserInstructor(String username) {
        UserRole role = findByUsername(username).getRole();
        if (role.equals(UserRole.INSTRUCTOR)) {
            return true;
        }
        throw new UnauthorizedRoleAccessException("O usuário não está autorizado a fazer esse acesso. Usuário: " + role + ", Tipo: " + UserRole.class.getName());
    }
}
