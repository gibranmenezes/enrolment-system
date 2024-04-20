package io.github.enrolmentsystem.repository;

import io.github.enrolmentsystem.domain.enrolment.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrolmentRepository extends JpaRepository<Enrolment, Long> {
}
