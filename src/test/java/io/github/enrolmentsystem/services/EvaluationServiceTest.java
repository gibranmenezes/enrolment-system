package io.github.enrolmentsystem.services;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.evaluation.request.EvaluationRequest;
import io.github.enrolmentsystem.domain.user.User;
import io.github.enrolmentsystem.domain.validations.evaluation.EvaluationSubmitValidator;
import io.github.enrolmentsystem.repository.CourseRepository;
import io.github.enrolmentsystem.repository.EvaluationRepository;
import io.github.enrolmentsystem.repository.UserRepository;
import io.github.enrolmentsystem.service.impl.CourseEvaluationServiceImpl;
import io.github.enrolmentsystem.service.impl.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EvaluationServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EvaluationRepository evaluationRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private CourseEvaluationServiceImpl courseEvaluationService;

    private  EvaluationRequest request;

    private User user;

    private Course course;



    @Test
    public void testSubmitEvaluation() {
        EvaluationRequest request = new EvaluationRequest("javao", 1L, 4, "This course was not so good.");

        Course course = new Course();
        course.setInstructor(new User());

        given(userRepository.getReferenceById(anyLong())).willReturn(new User());
        given(courseRepository.findCourseByCode(anyString())).willReturn(course);

        courseEvaluationService.submitEvaluation(request);

        verify(evaluationRepository, times(1)).save(any());
        verify(notificationService, times(1)).lowRatingNotify(eq(course), eq("This course was not so good."));
    }
}
