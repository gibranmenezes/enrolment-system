package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.report.NpsReport;
import io.github.enrolmentsystem.domain.report.reponse.NpsReportResponse;
import io.github.enrolmentsystem.repository.CourseEvaluationRepository;
import io.github.enrolmentsystem.repository.CourseRepository;
import io.github.enrolmentsystem.service.NpsReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NpsReportServiceImpl implements NpsReportService {

    private final CourseRepository courseRepository;
    private final CourseEvaluationRepository evaluationRepository;
  

    @Override
    public NpsReportResponse generateReport(){
        List<Course> courses = courseRepository.getCoursesWithMoreThanFourEnrolments();
        List<NpsReport> reports = evaluationRepository.getReports(courses);

        reports.forEach(report -> {
            var promoters = BigDecimal.valueOf(report.getPromoters());
            var detractors = BigDecimal.valueOf(report.getDetractors());
            var totalEvaluations = BigDecimal.valueOf(report.getTotalEvaluations());

            report.setNps(this.calculateNPS(totalEvaluations, promoters, detractors));
        });
       
        return new NpsReportResponse(reports);
    }

    private BigDecimal calculateNPS(BigDecimal totalEvaluations, BigDecimal promoters, BigDecimal defractors){
        return  ((promoters).subtract(defractors)).divide(totalEvaluations).multiply(BigDecimal.valueOf(100));
    }


}
