CREATE TABLE nps_report (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            course_code VARCHAR(255) NOT NULL,
                            instructor_username VARCHAR(255) NOT NULL,
                            total_evaluations BIGINT,
                            promoters BIGINT,
                            detractors BIGINT,
                            nps BIGINT,
                            generated_at DATE
);
