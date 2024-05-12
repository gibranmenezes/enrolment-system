package io.github.enrolmentsystem.course.validations;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.enrolmentsystem.domain.course.request.CourseCreateRequest;
import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.domain.validations.course.creation.InstructorExistenceValidation;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class InstructorExistenceValidationTest {

    @InjectMocks
    private InstructorExistenceValidation validation;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseCreateRequest request;

    @DisplayName("given an user id which dont belongs to an instructor role")
    @Test
    void shouldThrowValidationException(){
        given(userRepository.existsByIdAndRole(request.instructorId(), Role.INSTRUCTOR)).willReturn(false);
        
        Assertions.assertThrows(ValidationException.class, () -> validation.validate(request));
    }

    @DisplayName("given an user id which  belongs to an instructor role")
    @Test
    void shoulNotThrowValidationException(){
        given(userRepository.existsByIdAndRole(request.instructorId(), Role.INSTRUCTOR)).willReturn(true);
        
        Assertions.assertDoesNotThrow( () -> validation.validate(request));
    }


}
