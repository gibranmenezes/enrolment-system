package io.github.enrolmentsystem.services.course;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.domain.user.User;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.CourseRepository;
import io.github.enrolmentsystem.repository.UserRepository;
import io.github.enrolmentsystem.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.BDDMockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {


    @Mock
    private UserRepository userRepository;
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseServiceImpl service;

    private CourseCreateRequest request;

    private User instructor;

    private Course course;


    @BeforeEach
    public void setup()  {
        request = new CourseCreateRequest("curso", "curso-test",
                "desc",1L);

        instructor = new User(1L, "joao@gmail.com", "joao", "123456",
                Role.INSTRUCTOR, LocalDate.now());
        course = new Course(1L, request.name(), request.code(), request.description(), instructor, Status.ACTIVE,
                LocalDate.now(), LocalDate.now());

    }
    @Test
    @DisplayName("if exists an instructor with the same id than request and there is no one course with the code")
     void testCreateCourse_WhenInstructorIdExistsAndNoExistCourseWithCode_ThenCreateCourse() {
       given(userRepository.existsByIdAndRole(request.instructorId(), Role.INSTRUCTOR))
                .willReturn(true);

        given(userRepository.getReferenceById(request.instructorId())).willReturn(instructor);
        given(courseRepository.save(course)).willReturn(course);
        given(courseRepository.existsByCode(request.code())).willReturn(false);


        var response = service.createCourse(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.code(), "curso-test");

    }

    @Test
    void testCreateCourse_WhenInstructorIdDoesNotExist() {
        given(userRepository.existsByIdAndRole(request.instructorId(), Role.INSTRUCTOR))
                .willReturn(true);

       ValidationException exception = assertThrows(ValidationException.class,
                () -> service.createCourse(request));

        String expectedMessage = "There is no none instructor with this id: " + request.instructorId();
        assert(exception.getMessage().equals(expectedMessage));

        verify(courseRepository, never()).save(any(Course.class));
    }

    @Test
    void testCreateCourse_WhenInstructorExistsAndTheCodeAlreadyExists_ThenThrowsException() {
        given(userRepository.existsByIdAndRole(request.instructorId(), Role.INSTRUCTOR))
                .willReturn(true);
        given(courseRepository.existsByCode(request.code())).willReturn(true);


        ValidationException exception = assertThrows(ValidationException.class,
                () -> service.createCourse(request));

        String expectedMessage = "This course already exists!";
        assert(exception.getMessage().equals(expectedMessage));

        verify(courseRepository, never()).save(any(Course.class));
    }












}
