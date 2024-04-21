CREATE TABLE course_evaluation (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   rating INTEGER,
                                   evaluation_description VARCHAR(255),
                                   submited_at DATE,
                                   course_id BIGINT,
                                   FOREIGN KEY (course_id) REFERENCES course(id)
);