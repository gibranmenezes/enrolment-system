package io.github.enrolmentsystem.repository;

import io.github.enrolmentsystem.domain.report.NpsReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NpsReportRepository extends JpaRepository<NpsReport, Long> {
}
