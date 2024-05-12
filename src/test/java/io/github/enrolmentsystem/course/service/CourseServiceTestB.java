package io.github.enrolmentsystem.course.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.course.Status;
import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.user.User;
import io.github.enrolmentsystem.repository.CourseRepository;
import io.github.enrolmentsystem.repository.UserRepository;
import io.github.enrolmentsystem.service.impl.CourseServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTestB {

    @InjectMocks
    private CourseServiceImpl service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private User instructor;

    
    private Course course;
    
    
    private CourseCreateRequest request;    

   

    @Test
    void shoulSaveCourse() {

        this.request = new CourseCreateRequest("curso A", "curso-a", "Curso A", 1L);       
             
        given(userRepository.getReferenceById(request.instructorId())).willReturn(instructor);

       this.course = new Course(request);
        course.setId(1L);
        course.setInactivatedAt(LocalDate.now());
        course.setInstructor(instructor);
        given(courseRepository.save(any())).willReturn(course);

        var response = service.createCourse(request);

        
        Assertions.assertEquals(course.getId(), response.id());
        Assertions.assertEquals(request.name(), response.name());        
        Assertions.assertEquals(request.code(), response.code());
        Assertions.assertEquals(Status.ACTIVE, response.status());
        Assertions.assertEquals(LocalDate.now(), course.getCreatedAt());

        verify(courseRepository, times(1)).save(any());
       

    }
    
    

   
}
