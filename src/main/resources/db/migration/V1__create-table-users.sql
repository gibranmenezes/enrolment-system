CREATE TABLE users (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      email VARCHAR(100) NOT NULL,
                      username VARCHAR(20) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      role VARCHAR(100) NOT NULL,
                      created_at DATE NOT NULL,
                      CONSTRAINT unique_name_email UNIQUE (name, email)
);
