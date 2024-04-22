package io.github.enrolmentsystem.repository;

import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByIdAndRole(Long id, Role role);

    Boolean existsByUsernameAndEmail(String username, String email);

    @Query ("SELECT u from User u where u.username = :username")
    User getUserByUsername(String username);

    UserDetails findUserByUsername(String username);




}
