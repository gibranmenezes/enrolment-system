package io.github.enrolmentsystem.services.course;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.course.response.CourseDetailsResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
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


}
