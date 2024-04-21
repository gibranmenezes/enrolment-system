package io.github.enrolmentsystem.repository;


import io.github.enrolmentsystem.domain.evaluation.CourseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseEvaluationRepository extends JpaRepository<CourseEvaluation,Long> {

    @Query("SELECT COUNT(ce) FROM CourseEvaluation ce WHERE ce.course.id = :courseId and ce.rating >= 9 ")
    Long countPromoters(Long courseId);


    @Query("SELECT COUNT(ce) FROM CourseEvaluation ce WHERE ce.course.id = :courseId and ce.rating <= 6 ")
    Long countDetractors(Long courseId);

    @Query("SELECT COUNT(ce) FROM CourseEvaluation ce WHERE ce.course.id = :courseId")
    Long countCourseEvaluation(Long courseId);


}

