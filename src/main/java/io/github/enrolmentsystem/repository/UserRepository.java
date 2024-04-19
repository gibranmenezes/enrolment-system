package io.github.enrolmentsystem.repository;

import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByIdAndRole(Long id, Role role);
}
