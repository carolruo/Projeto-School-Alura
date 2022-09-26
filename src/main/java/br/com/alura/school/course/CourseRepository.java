package br.com.alura.school.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCode(String code);

    @Query("SELECT DISTINCT d.enrolls FROM Course d")
    List<Course> findDistinctByEnrolls();

}
