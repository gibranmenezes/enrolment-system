package io.github.enrolmentsystem.services.course;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.course.response.CourseDetailsResponse;
import io.github.enrolmentsystem.domain.evaluation.CourseEvaluation;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    private CourseEvaluation evaluation;



    @BeforeEach
    public void setup()  {

        student = new User(1L, "joao", "joao@gmail.com", "joao", "123456",
                Role.STUDENT, LocalDate.now());

        instructor = new User(1L, "joao", "joao@gmail.com", "joao", "123456",
                Role.INSTRUCTOR, LocalDate.now());


    }

    @Test
    void testCreateCourse_WhenInstructorExistsAndNoExistCourseWithCode_ThenCreateCourse() {
        var requestA = new CourseCreateRequest("curso", "curso-test", "desc", 1L);

        User instructorA = new User(1L, "joao", "joao@gmail.com", "joao", "123456",
                Role.INSTRUCTOR, LocalDate.now());


        given(userRepository.getReferenceById(anyLong())).willReturn(instructorA);
        var savedCourse = new Course(requestA);
        savedCourse.setId(1L);
        savedCourse.setInactivatedAt(LocalDate.now());
        savedCourse.setInstructor(instructorA);
        given(courseRepository.save(any())).willReturn(savedCourse);


        var response = service.createCourse(requestA);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.code(), "curso-test");
    }

    @Test
    public void testGetCoursesByStatus_GivenStatusActive_ThenReturnAllActiveCourses() {
    Status status = Status.ACTIVE;
        int page = 0;
        int size = 10;

        var requestA = new CourseCreateRequest("curso", "curso-test", "desc", 1L);
        var requestB = new CourseCreateRequest("Curso B", "curso-b", "Descrição do Curso B", 1L);

        var courseA = new Course(requestA);
        courseA.setId(1L);
        courseA.setInactivatedAt(LocalDate.now());
        courseA.setInstructor(instructor);

        var courseB = new Course(requestB);
        courseB.setId(2L);
        courseB.setInactivatedAt(LocalDate.now());
        courseB.setInstructor(instructor);

        List<Course> courseList = new ArrayList<>();
        courseList.add(courseA);
        courseList.add(courseB);


        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = new PageImpl<>(courseList, pageable, courseList.size());
        given(courseRepository.findAllByStatus(status, pageable)).willReturn(coursePage);

        Page<CourseDetailsResponse> resultPage = service.getCoursesByStatus(status, pageable);

        Assertions.assertEquals(2, resultPage.getTotalElements());
        Assertions.assertEquals("curso", resultPage.getContent().get(0).courseDetails().get("name"));
        Assertions.assertEquals("curso-test", resultPage.getContent().get(0).courseDetails().get("code"));
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

        var requestA = new CourseCreateRequest("curso", "curso-test", "desc", 1L);
        var requestB = new CourseCreateRequest("Curso B", "curso-b", "Descrição do Curso B", 1L);

        var courseA = new Course(requestA);
        courseA.setId(1L);
        courseA.setInactivatedAt(LocalDate.now());
        courseA.setCreatedAt(LocalDate.of(2020, 2, 19));

        var courseB = new Course(requestB);
        courseB.setId(2L);
        courseB.setInactivatedAt(LocalDate.now());
        courseB.setCreatedAt(LocalDate.of(2020, 2, 19));

        List<Course> courseList = new ArrayList<>();
        courseList.add(courseA);
        courseList.add(courseB);

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
        var requestB = new CourseCreateRequest("Curso B", "curso-b", "Descrição do Curso B", 1L);
        var courseB = new Course(requestB);
        courseB.setId(1L);
        courseB.setStatus(Status.ACTIVE);
        courseB.setCreatedAt(LocalDate.now());
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
