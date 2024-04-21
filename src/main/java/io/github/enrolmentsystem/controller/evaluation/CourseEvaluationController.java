package io.github.enrolmentsystem.controller.evaluation;

import io.github.enrolmentsystem.domain.evaluation.request.EvaluationRequest;
import io.github.enrolmentsystem.service.impl.CourseEvaluationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses/evaluations")
@RequiredArgsConstructor
public class CourseEvaluationController {

    private final CourseEvaluationServiceImpl courseEvaluationServiceImpl;

    @PostMapping()
    public void submitEvaluation(@RequestBody EvaluationRequest request){
        courseEvaluationServiceImpl.submitEvaluation(request);

    }
}
