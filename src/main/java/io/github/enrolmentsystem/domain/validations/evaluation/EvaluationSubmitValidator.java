package io.github.enrolmentsystem.domain.validations.evaluation;

import io.github.enrolmentsystem.domain.evaluation.request.EvaluationRequest;

public interface EvaluationSubmitValidator {
    void validate(EvaluationRequest request);
}
