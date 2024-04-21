package io.github.enrolmentsystem.repository;

import io.github.enrolmentsystem.domain.evaluation.CourseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<CourseEvaluation, Long> {
}
