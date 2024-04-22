package io.github.enrolmentsystem.repository;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Boolean existsByCode(String code);

    Page<Course> findAllByStatus(Status status, Pageable pagination);

    @Query("select c from Course c where c.code = :code")
    Course findCourseByCode(String code);

   // @Query("SELECT c FROM Course c WHERE (SELECT COUNT(e) FROM Enrolment e WHERE e.course.id = c.id) > 4")
   @Query("SELECT c FROM Course c , Enrolment e  where c.id = e.course.id GROUP BY c.id HAVING COUNT(e.course.id) > 4")
   List<Course> getCoursesWithMoreThanFourEnrolments();

}
