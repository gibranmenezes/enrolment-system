package io.github.enrolmentsystem.repository;

import io.github.enrolmentsystem.domain.enrolment.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnrolmentRepository extends JpaRepository<Enrolment, Long> {

    @Query("""
        SELECT CASE WHEN count(e) > 0 THEN true ELSE false END
        FROM Enrolment e
        WHERE e.user.id = :userId
    """)
    Boolean existsEnrolmentByUser(Long userId);
}
