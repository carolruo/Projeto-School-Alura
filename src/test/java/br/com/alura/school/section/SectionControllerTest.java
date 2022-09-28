package br.com.alura.school.section;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.enroll.*;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserRepository;
import br.com.alura.school.user.UserRole;
import br.com.alura.school.video.Video;
import br.com.alura.school.video.VideoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SectionControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EnrollService enrollService;
    @Autowired
    private VideoService videoService;

    @AfterEach
    void wipe() {
        userRepository.deleteAll();
        courseRepository.deleteAll();
    }

    @Test
    void should_send_no_content_exception_when_no_enrollments() throws Exception {
        mockMvc.perform(get("/sectionByVideosReport").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_retrieve_section_by_videos_report() throws Exception {
        User user = new User("carol", "carol@gmail.com");
        Video video1 = new Video("youtube.com/gbth45");
        Video video2 = new Video("youtube.com/je8eueu2q");
        Video video3 = new Video("youtube.com/k43kl43");

        Course course1 = new Course("spring-3", "Spring Basico", "Spring Core and Spring MVC.");
        Section section1 = new Section("spring-boot", "Initialize", "Alexa");
        section1.setCourse(course1);
        section1.setVideos(Arrays.asList(video1, video2));
        courseRepository.save(course1);
        sectionRepository.save(section1);
        userRepository.save(user);
        Enroll enroll1 = new Enroll(user, course1);
        course1.addEnroll(enroll1);
        enrollService.save("carol", "spring-3");
        videoService.save(video1, "spring-3", "spring-boot");
        videoService.save(video2, "spring-3", "spring-boot");

        Course course2 = new Course("spring-4", "Spring B", "Spring Boot");
        Section section2 = new Section("spring-security", "JWT Basic", "Ale");
        section2.setCourse(course2);
        section2.setVideos(Arrays.asList(video1));
        courseRepository.save(course2);
        sectionRepository.save(section2);
        Enroll enroll2 = new Enroll(user, course1);
        course2.addEnroll(enroll2);
        enrollService.save("carol", "spring-4");
        videoService.save(video3, "spring-4", "spring-security");

        mockMvc.perform(get("/sectionByVideosReport").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].courseName", is("Spring Basico")))
                .andExpect(jsonPath("$[0].sectionTitle", is("Initialize")))
                .andExpect(jsonPath("$[0].authorName", is("Alexa")))
                .andExpect(jsonPath("$[0].totalVideos", is(2)))
                .andExpect(jsonPath("$[1].courseName", is("Spring B")))
                .andExpect(jsonPath("$[1].sectionTitle", is("JWT Basic")))
                .andExpect(jsonPath("$[1].authorName", is("Ale")))
                .andExpect(jsonPath("$[1].totalVideos", is(1)));
    }

    @Test
    void should_add_new_section() throws Exception {
        courseRepository.save(new Course("java-1", "Java OO", "Encapsulation, Polymorphism."));
        User alexa = new User("alexa", "alexa@gmail.com");
        alexa.setRole(UserRole.INSTRUCTOR);
        userRepository.save(alexa);
        NewSectionRequest newSectionRequest = new NewSectionRequest("java-total", "Titulo", "alexa");

        mockMvc.perform(post("/courses/java-1/sections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newSectionRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/courses/java-total"));
    }

    @Test
    void unauthorized_when_user_not_instructor() throws Exception {
        courseRepository.save(new Course("java-1", "Java OO", "Encapsulation, Polymorphism."));
        User carolina = new User("carolina", "carolina@gmail.com");
        userRepository.save(carolina);
        NewSectionRequest newSectionRequest = new NewSectionRequest("java-total", "Titulo", "carolina");

        mockMvc.perform(post("/courses/java-1/sections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newSectionRequest)))
                .andExpect(status().isUnauthorized());
    }

}
