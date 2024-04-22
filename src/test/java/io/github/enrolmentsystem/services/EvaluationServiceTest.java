package io.github.enrolmentsystem.services;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.evaluation.request.EvaluationRequest;
import io.github.enrolmentsystem.domain.user.User;
import io.github.enrolmentsystem.repository.CourseRepository;
import io.github.enrolmentsystem.repository.CourseEvaluationRepository;
import io.github.enrolmentsystem.repository.UserRepository;
import io.github.enrolmentsystem.service.impl.CourseEvaluationServiceImpl;
import io.github.enrolmentsystem.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EvaluationServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseEvaluationRepository courseEvaluationRepository;

    @Mock
    private NotificationServiceImpl notificationServiceImpl;

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

        verify(courseEvaluationRepository, times(1)).save(any());
        verify(notificationServiceImpl, times(1)).lowRatingNotify(eq(course), eq("This course was not so good."));
    }
}
