package io.github.enrolmentsystem.domain.evaluation.request;

public record EvaluationRequest(String courseCode, Long userId, int rating, String evaluationDescription) {
}
