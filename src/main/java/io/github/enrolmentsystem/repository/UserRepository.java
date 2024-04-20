package io.github.enrolmentsystem.repository;

import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByIdAndRole(Long id, Role role);

    Boolean existsByUsernameAndEmail(String username, String email);

    @Query ("SELECT u from User u where u.username = :username")
    User getUserByUsername(String username);
}
