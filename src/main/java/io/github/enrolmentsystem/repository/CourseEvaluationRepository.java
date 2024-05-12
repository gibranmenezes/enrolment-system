package io.github.enrolmentsystem.repository;


import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.evaluation.CourseEvaluation;
import io.github.enrolmentsystem.domain.report.NpsReport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEvaluationRepository extends JpaRepository<CourseEvaluation,Long> {

    @Query("SELECT COUNT(ce) FROM CourseEvaluation ce WHERE ce.course.id = :courseId and ce.rating >= 9 ")
    Long countPromoters(Long courseId);


    @Query("SELECT COUNT(ce) FROM CourseEvaluation ce WHERE ce.course.id = :courseId and ce.rating <= 6 ")
    Long countDetractors(Long courseId);

    @Query("SELECT COUNT(ce) FROM CourseEvaluation ce WHERE ce.course.id = :courseId")
    Long countCourseEvaluation(Long courseId);
    
    
    @Query("""
           SELECT new io.github.enrolmentsystem.domain.report.NpsReport(
            c.code,
            c.instructor.name,
            COUNT(e.rating>=9),
            COUNT(e.rating<=4),
            COUNT(e)
            FROM Course c
            INNER JOIN c.evaluations e
            WHERE c in :courses
            GROUP BY c.code
           ) 
            """)
    List<NpsReport> getReports(List<Course> courses);


}

