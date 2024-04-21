package io.github.enrolmentsystem.controller.report;

import io.github.enrolmentsystem.domain.report.reponse.NpsReportResponse;
import io.github.enrolmentsystem.service.impl.NpsReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reports")
@RequiredArgsConstructor
public class NpsReportController {

    private final NpsReportService reportService;

   @GetMapping
    public ResponseEntity<NpsReportResponse> generateReport(){
        return ResponseEntity.ok(reportService.generateReport());
    }
}
