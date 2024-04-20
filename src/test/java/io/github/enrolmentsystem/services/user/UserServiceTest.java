package io.github.enrolmentsystem.services.user;

import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.domain.user.User;
import io.github.enrolmentsystem.domain.user.request.UserCreateRequest;
import io.github.enrolmentsystem.infra.exception.ValidationException;
import io.github.enrolmentsystem.repository.UserRepository;
import io.github.enrolmentsystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)

public class UserServiceTest {

    @Mock
    private  UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    private UserCreateRequest request;

    @BeforeEach
    public void setUp() {
        user = new User(1L, "joao", "joao@gmail.com", "joao", "123456",
                Role.STUDENT, LocalDate.now());
        request = new UserCreateRequest("joao", "joao", "joao@gmail.com",
                "123456", Role.STUDENT);
    }

    @Test
    void registerTest_GivenCreateRequest_ThenCreateUser() {
        given(userRepository.existsByUsernameAndEmail(request.username(), request.email())).willReturn(false);

        var response = userService.register(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(user.getName(), response.name());
        Assertions.assertEquals(user.getEmail(), response.email());
        Assertions.assertEquals(user.getRole(), response.role());

    }

    @Test
    void registerTest_GivenExistingEmailAndUserName_ThenThrowException() {
        given(userRepository.existsByUsernameAndEmail(request.username(), request.email())).willReturn(true);

        ValidationException exception = assertThrows(ValidationException.class, () -> userService.register(request));
        assertEquals("Username and email already registered", exception.getMessage());
    }
}
