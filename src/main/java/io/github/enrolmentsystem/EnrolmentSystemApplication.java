package io.github.enrolmentsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EnrolmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnrolmentSystemApplication.class, args);
    }

}
