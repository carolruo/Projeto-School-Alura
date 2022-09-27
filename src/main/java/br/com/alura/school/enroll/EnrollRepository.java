package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Long> {

    @Query("select distinct obj.id.course from Enroll obj")
    List<Course> findDistinctByCourse_id();
}
