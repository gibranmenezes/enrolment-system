package io.github.enrolmentsystem.services.enrolment;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.enrolment.Enrolment;
import io.github.enrolmentsystem.domain.enrolment.request.EnronlmentCreateRequest;
import io.github.enrolmentsystem.domain.enrolment.response.EnrolmentCreateResponse;
import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.domain.user.User;
import io.github.enrolmentsystem.repository.CourseRepository;
import io.github.enrolmentsystem.repository.EnrolmentRepository;
import io.github.enrolmentsystem.repository.UserRepository;
import io.github.enrolmentsystem.service.EnrolmentService;
import io.github.enrolmentsystem.service.impl.EnrolmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EnrolmentServiceTest {

    @Mock
    private  EnrolmentRepository enrolmentRepository;
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  CourseRepository courseRepository;

    @InjectMocks
    private EnrolmentServiceImpl enrolmentService;

    private User user;

    private Course course;

    private EnronlmentCreateRequest request;

    private Enrolment enrol;
    @BeforeEach
    public void setUp() {
        user = new User(1L, "joao@gmail.com", "joao", "123456",
                Role.STUDENT, LocalDate.now());
        course = new Course(1L, "curso", "code", "desc",
                user, Status.ACTIVE, LocalDate.now(), LocalDate.now());
        request = new EnronlmentCreateRequest(1L, 1L);

       enrol = new Enrolment(user, course);
    }

    @Test
    void testEnrol_GivenAnUserIdAndCourseId_ThenEnrol() {
       given(enrolmentRepository.save(any(Enrolment.class))).willReturn(enrol);

        EnrolmentCreateResponse response = enrolmentService.enrol(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(user.getUsername(), response.userName());
        Assertions.assertEquals(course.getCode(), response.courseCode());

    }


}
