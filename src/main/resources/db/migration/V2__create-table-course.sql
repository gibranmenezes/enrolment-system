CREATE TABLE course (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        code VARCHAR(50) NOT NULL,
                        description TEXT,
                        user_id BIGINT,
                        status VARCHAR(100) NOT NULL,
                        created_at DATE NOT NULL,
                        inactivated_at DATE,
                        FOREIGN KEY (user_id) REFERENCES user (id)
);
