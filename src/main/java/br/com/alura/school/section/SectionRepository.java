package br.com.alura.school.section;

import br.com.alura.school.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {

    Optional<Section> findByCodeAndCourse(String code, Course course);
}
