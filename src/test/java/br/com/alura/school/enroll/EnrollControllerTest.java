package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EnrollControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EnrollRepository enrollRepository;

    @AfterEach
    void wipe() {
        courseRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void not_found_when_user_does_not_exist() throws Exception {
        courseRepository.save(new Course("java-3", "Java Exceptions", "Curso de Java"));
        NewEnrollRequest enrollRequest = new NewEnrollRequest("alexa");

        mockMvc.perform(post("/courses/java-1/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(enrollRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_add_new_enroll() throws Exception {
        courseRepository.save(new Course("java-4", "Java Spring", "Curso de Java"));
        userRepository.save(new User("alexa", "alexa@gmail.com"));
        NewEnrollRequest enrollRequest = new NewEnrollRequest("alexa");

        mockMvc.perform(post("/courses/java-4/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(enrollRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void bad_request_when_duplicate_enroll() throws Exception {
        Course entity = new Course("java-4", "Java Spring", "Curso de Java");
        courseRepository.save(entity);
        User alexa = new User("alexa", "alexa@gmail.com");
        userRepository.save(alexa);
        enrollRepository.save(new Enroll(alexa, entity));

        mockMvc.perform(post("/courses/java-1/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(alexa)))
                .andExpect(status().isBadRequest());
    }

}
