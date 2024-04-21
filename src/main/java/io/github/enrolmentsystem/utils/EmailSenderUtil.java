package io.github.enrolmentsystem.utils;

import org.springframework.stereotype.Component;

@Component
public class EmailSenderUtil {
    public static void send(String recipientEmail, String subject, String body) {
        System.out.printf("Simulating sending email to [%s]:\n%n", recipientEmail);
        System.out.printf("""
                Subject: %s
                Body: %s
                %n""", subject, body);
    }

}
