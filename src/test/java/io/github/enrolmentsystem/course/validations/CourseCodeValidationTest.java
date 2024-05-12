package io.github.enrolmentsystem.course.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.*;

import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.validations.course.creation.CourseCodeValidation;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.CourseRepository;

@ExtendWith(MockitoExtension.class)
public class CourseCodeValidationTest {

    @InjectMocks
    private CourseCodeValidation validation;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseCreateRequest request;

    @DisplayName("given an existent course code")
    @Test
    void shouldThrowValidationException(){

        given(courseRepository.existsByCode(request.code())).willReturn(true);
        
        Assertions.assertThrows(ValidationException.class, () -> validation.validate(request));


    }

    @DisplayName("given an not existent course code")
    @Test
    void shouldNotThrowValidationException(){

        given(courseRepository.existsByCode(request.code())).willReturn(false);
        
        Assertions.assertDoesNotThrow(() -> validation.validate(request));


    }

}
