ALTER TABLE course_evaluation
    ADD COLUMN user_id BIGINT,
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(id);
