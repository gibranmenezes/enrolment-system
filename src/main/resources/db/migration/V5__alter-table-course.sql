ALTER TABLE course
    ADD COLUMN evaluation_id BIGINT,
    ADD CONSTRAINT fk_course_evaluation FOREIGN KEY (evaluation_id) REFERENCES course_evaluation(id);
