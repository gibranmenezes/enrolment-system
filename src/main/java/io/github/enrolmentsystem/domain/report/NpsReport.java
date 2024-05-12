package io.github.enrolmentsystem.domain.report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class NpsReport {

    private String courseCode;
    private String instructorUsername;    
    private Long promoters;
    private Long detractors;
    private Long totalEvaluations;
    private BigDecimal nps;
    private LocalDate generatedAt;

    public NpsReport(String courseCode, String instructorUsername, 
                     Long promoters, Long detractors, Long totalEvaluations){
        this.courseCode = courseCode;
        this.instructorUsername = instructorUsername;
        this.totalEvaluations = totalEvaluations;
        this.promoters = promoters;
        this.detractors = detractors;     
        this.generatedAt = LocalDate.now();

    }


}
