package io.github.enrolmentsystem.services.course;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.course.response.CourseDetailsResponse;
import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.domain.user.User;
import io.github.enrolmentsystem.domain.validations.course.creation.CourseCodeValidation;
import io.github.enrolmentsystem.domain.validations.course.creation.CreateCourseValidator;
import io.github.enrolmentsystem.domain.validations.course.creation.InstructorExistenceValidation;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    @Mock
    private CourseCodeValidation courseCodeValidation;
    @Mock
    private InstructorExistenceValidation instructorExistenceValidation;
    @Mock
    private List<CreateCourseValidator> createValidators;
    @InjectMocks
    private CourseServiceImpl service;

    private CourseCreateRequest request;

    private User instructor;

    private User student;

    private Course course;



    @BeforeEach
    public void setup()  {
        request = new CourseCreateRequest("curso", "curso-test", "desc", 1L);

        student = new User(1L, "joao@gmail.com", "joao", "123456",
                Role.STUDENT, LocalDate.now());

        instructor = new User(1L, "joao@gmail.com", "joao", "123456",
                Role.INSTRUCTOR, LocalDate.now());

        course = new Course(1L, request.name(), request.code(), request.description(),
                instructor, Status.ACTIVE, LocalDate.now(), LocalDate.now());




    }

    @Test
    void testCreateCourse_WhenInstructorExistsAndNoExistCourseWithCode_ThenCreateCourse() {
        given(userRepository.getReferenceById(request.instructorId())).willReturn(instructor);
        given(courseRepository.save(any())).willReturn(course);

        var response = service.createCourse(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.code(), "curso-test");
    }

//    @Test
//    void testCreateCourse_GivenAnNotInstructorId_ThenThrowsException(){
//        given(service.createValidators.)
//
//    }


    @Test
    public void testGetCoursesByStatus_GivenStatusActive_ThenReturnAllActiveCourses() {
    Status status = Status.ACTIVE;
        int page = 0;
        int size = 10;

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(1L, "Curso A", "curso-a", "Descrição do Curso A"
                , instructor, status, LocalDate.now(), null));
        courseList.add(new Course(2L, "Curso B", "curso-b", "Descrição do Curso B"
                , instructor, status, LocalDate.now(), null));

        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = new PageImpl<>(courseList, pageable, courseList.size());
        given(courseRepository.findAllByStatus(status, pageable)).willReturn(coursePage);

        Page<CourseDetailsResponse> resultPage = service.getCoursesByStatus(status, pageable);

        Assertions.assertEquals(2, resultPage.getTotalElements());
        Assertions.assertEquals("Curso A", resultPage.getContent().get(0).courseDetails().get("name"));
        Assertions.assertEquals("curso-a", resultPage.getContent().get(0).courseDetails().get("code"));
        Assertions.assertFalse(resultPage.getContent().get(0).courseDetails().containsKey("inactiveAt"));
        Assertions.assertEquals("Curso B", resultPage.getContent().get(1).courseDetails().get("name"));
        Assertions.assertEquals("curso-b", resultPage.getContent().get(1).courseDetails().get("code"));
        Assertions.assertFalse(resultPage.getContent().get(1).courseDetails().containsKey("inactiveAt"));

    }

    @Test
    public void testGetCoursesByStatus_GivenStatusInactive_ThenReturnAllInactiveCourses() {
        Status status = Status.INACTIVE;
        int page = 0;
        int size = 10;

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(1L, "Curso A", "curso-a", "Descrição do Curso A"
                , instructor, status, LocalDate.of(2020, 2, 19),  LocalDate.now()));
        courseList.add(new Course(2L, "Curso B", "curso-b", "Descrição do Curso B"
                , instructor, status, LocalDate.of(2020, 2, 19), LocalDate.now()));

        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = new PageImpl<>(courseList, pageable, courseList.size());
        given(courseRepository.findAllByStatus(status, pageable)).willReturn(coursePage);

        Page<CourseDetailsResponse> resultPage = service.getCoursesByStatus(status, pageable);

        Assertions.assertEquals(2, resultPage.getTotalElements());
        Assertions.assertEquals("Curso A", resultPage.getContent().get(0).courseDetails().get("name"));
        Assertions.assertEquals("curso-a", resultPage.getContent().get(0).courseDetails().get("code"));
        Assertions.assertTrue(resultPage.getContent().get(0).courseDetails().containsKey("inactivatedAt"));
        Assertions.assertEquals("Curso B", resultPage.getContent().get(1).courseDetails().get("name"));
        Assertions.assertEquals("curso-b", resultPage.getContent().get(1).courseDetails().get("code"));
        Assertions.assertTrue(resultPage.getContent().get(1).courseDetails().containsKey("inactivatedAt"));
    }


    @Test
    public void testInactivateCourse_GivenCourseCode_ThenInactivateStatusAndSetInactivateDate(){
        var courseB = new Course(1L, "cursoB", "curso-b", "desc-b",
                instructor, Status.ACTIVE, LocalDate.now(), null);
        given(courseRepository.findCourseByCode("curso-b")).willReturn(courseB);

        service.inactivateCourse("curso-b");

        verify(courseRepository, times(1)).findCourseByCode("curso-b");

        Assertions.assertSame(Status.INACTIVE, courseB.getStatus());
        Assertions.assertEquals(LocalDate.now(), courseB.getInactivatedAt());
    }

    @Test
    public void testInactivateCourse_WhenCourseNotFound_ThrowsValidationException() {
        given(courseRepository.findCourseByCode(anyString())).willReturn(null);

       String nonExistingCourseCode = "Course not found";

       Assertions.assertThrows(ValidationException.class, () -> {
            service.inactivateCourse(nonExistingCourseCode);
        });

        verify(courseRepository, times(1)).findCourseByCode(nonExistingCourseCode);
    }





}
