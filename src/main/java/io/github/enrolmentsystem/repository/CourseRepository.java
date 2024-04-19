package io.github.enrolmentsystem.repository;

import io.github.enrolmentsystem.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Boolean existsByCode(String code);
}
