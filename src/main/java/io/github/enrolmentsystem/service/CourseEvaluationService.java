package io.github.enrolmentsystem.service;

import io.github.enrolmentsystem.domain.evaluation.request.EvaluationRequest;

public interface CourseEvaluationService {

    void submitEvaluation(EvaluationRequest request);
}
