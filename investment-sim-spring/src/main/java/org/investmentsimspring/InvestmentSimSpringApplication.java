package org.investmentsimspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class InvestmentSimSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestmentSimSpringApplication.class, args);
    }

    @GetMapping("/api/status")
    public String getStatus() {
        return "tuto pom";
    }
}
