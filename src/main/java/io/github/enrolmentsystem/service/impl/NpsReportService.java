package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.report.NpsReport;
import io.github.enrolmentsystem.domain.report.reponse.NpsReportResponse;
import io.github.enrolmentsystem.repository.CourseEvaluationRepository;
import io.github.enrolmentsystem.repository.CourseRepository;
import io.github.enrolmentsystem.repository.NpsReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NpsReportService {

    private final CourseRepository courseRepository;
    private final CourseEvaluationRepository evaluationRepository;
    private final NpsReportRepository reportRepository;

    public NpsReportResponse generateReport(){
        List<Course> courses = courseRepository.getCoursesWithMoreThanFourEnrolments();
        List<NpsReport> reports = new ArrayList<>();
        courses.forEach(c -> {
            var totalEvaluations = evaluationRepository.countCourseEvaluation(c.getId());
            var promoters = evaluationRepository.countPromoters(c.getId());
            var defractors = evaluationRepository.countDetractors(c.getId());

            var courseCode = c.getCode();
            var instructorUsername = c.getInstructor().getUsername();
            var nps = calculateNPS(totalEvaluations, promoters, defractors).longValue();
            var report = new NpsReport(courseCode, instructorUsername, totalEvaluations,
                    promoters, defractors, nps);

            reportRepository.save(report);
            reports.add(report);
        });

        return new NpsReportResponse(reports);
    }

    private BigDecimal calculateNPS(Long totalEvaluations, Long promoters, Long defractors){
        return new BigDecimal(promoters - defractors).divide(BigDecimal.valueOf(totalEvaluations)).multiply(BigDecimal.valueOf(100));
    }


}
