package org.investmentsimspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

/**
 * Should only accept requests from users with admin privileges
 */
@RestController
public class SystemController {

    private final ApplicationContext context;

    @Autowired
    public SystemController(ApplicationContext context) {
        this.context = context;
    }

}
