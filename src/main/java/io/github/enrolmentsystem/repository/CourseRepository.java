package io.github.enrolmentsystem.repository;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.course.response.CourseDetailsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Boolean existsByCode(String code);

    Page<Course> findAllByStatus(Status status, Pageable pagination);

    @Query("select c from Course c where c.code = :code")
    Course findCourseByCode(String code);


}
