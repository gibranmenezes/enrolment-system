package io.github.enrolmentsystem.repository;

import io.github.enrolmentsystem.domain.report.NpsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NpsReportRepository extends JpaRepository<NpsReport, Long> {
}
