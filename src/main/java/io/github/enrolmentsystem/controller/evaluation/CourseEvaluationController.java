package io.github.enrolmentsystem.controller.evaluation;

import io.github.enrolmentsystem.domain.evaluation.request.EvaluationRequest;
import io.github.enrolmentsystem.service.impl.CourseEvaluationServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses/evaluations")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class CourseEvaluationController {

    private final CourseEvaluationServiceImpl courseEvaluationServiceImpl;

    @PostMapping()
    public void submitEvaluation(@RequestBody EvaluationRequest request){
        courseEvaluationServiceImpl.submitEvaluation(request);

    }
}
