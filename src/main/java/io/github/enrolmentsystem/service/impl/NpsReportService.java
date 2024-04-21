package io.github.enrolmentsystem.service.impl;

import io.github.enrolmentsystem.domain.course.Course;
import io.github.enrolmentsystem.domain.report.NpsReport;
import io.github.enrolmentsystem.domain.report.reponse.NpsReportResponse;
import io.github.enrolmentsystem.repository.CourseEvaluationRepository;
import io.github.enrolmentsystem.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NpsReportService {

    private final CourseRepository courseRepository;
    private final CourseEvaluationRepository evaluationRepository;

    public NpsReportResponse generateReport(){
        List<Course> courses = courseRepository.getCoursesWithMoreThanFourEnrolments();
        List<NpsReport> reports = new ArrayList<>();
        courses.forEach(c -> {
            var totalEvaluations = evaluationRepository.countCourseEvaluation(c.getId());
            var promoters = evaluationRepository.countPromoters(c.getId());
            var defractors = evaluationRepository.countDetractors(c.getId());

            var courseCode = c.getCode();
            var instructorUsername = c.getInstructor().getUsername();
            var nps = calculateNPS(totalEvaluations, promoters, defractors);
            var report = new NpsReport(courseCode, instructorUsername, totalEvaluations,
                    promoters, defractors, nps);
            reports.add(report);
        });

        return new NpsReportResponse(reports);
    }

    private Long calculateNPS(Long totalEvaluations, Long promoters, Long defractors){
        return (promoters - defractors) / totalEvaluations * 100;
    }


}
