package org.investmentsimspring.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Should only accept requests from users with admin privileges
 */
@RestController
@RequestMapping(path = "/api")
public class SystemController {

    private final ApplicationContext context;

    public SystemController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Tuto Pom";
    }
}
