CREATE TABLE enrolment (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            user_id BIGINT NOT NULL,
                            course_id BIGINT NOT NULL,
                            enrollment_at DATE NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES user (id),
                            FOREIGN KEY (course_id) REFERENCES course (id)
);