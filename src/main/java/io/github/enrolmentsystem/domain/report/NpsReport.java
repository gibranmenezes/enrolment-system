package io.github.enrolmentsystem.domain.report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "nps_report")
@Getter
@Setter
@NoArgsConstructor
public class NpsReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseCode;
    private String instructorUsername;
    private Long totalEvaluations;
    private Long promoters;
    private Long detractors;
    private Long nps;
    private LocalDate generatedAt;

    public NpsReport(String courseCode, String instructorUsername, Long totalEvaluations,
                     Long promoters, Long detractors, Long nps){
        this.courseCode = courseCode;
        this.instructorUsername = instructorUsername;
        this.totalEvaluations = totalEvaluations;
        this.promoters = promoters;
        this.detractors = detractors;
        this.nps = nps;
        this.generatedAt = LocalDate.now();

    }


}
