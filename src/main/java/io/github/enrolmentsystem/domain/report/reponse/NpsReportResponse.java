package io.github.enrolmentsystem.domain.report.reponse;

import io.github.enrolmentsystem.domain.report.NpsReport;

import java.util.List;

public record NpsReportResponse(List<NpsReport> reports) {

}
