package com.pir_online.test_assignment_pir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class TestAssignmentPirApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestAssignmentPirApplication.class, args);
    }
}
