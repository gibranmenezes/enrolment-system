package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.evaluation.CourseEvaluation;
import io.github.enrolmentsystem.domain.evaluation.request.EvaluationRequest;
import io.github.enrolmentsystem.domain.validations.evaluation.EvaluationSubmitValidator;
import io.github.enrolmentsystem.repository.CourseRepository;
import io.github.enrolmentsystem.repository.CourseEvaluationRepository;
import io.github.enrolmentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseEvaluationServiceImpl {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final  NotificationService notificationService;
    private final CourseEvaluationRepository courseEvaluationRepository;

    private List<EvaluationSubmitValidator> validators = new ArrayList<>();

    public void submitEvaluation(EvaluationRequest request){
        validators.forEach(v -> v.validate(request));

        var user = userRepository.getReferenceById(request.userId());
        var course = courseRepository.findCourseByCode(request.courseCode());

        var evaluation = new CourseEvaluation(request);
        evaluation.setCourse(course);
        evaluation.setUser(user);

        var savedEvaluation = courseEvaluationRepository.save(evaluation);

        course.addEvaluation(savedEvaluation);

        courseRepository.save(course);

        if (evaluation.getRating() < 6) {
            notificationService.lowRatingNotify(course, request.evaluationDescription());
        }
    }

}
