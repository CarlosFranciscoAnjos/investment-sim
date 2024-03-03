package org.investmentsimspring;

import org.investmentsimspring.infrastructure.initializers.InitializeUsers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Initializer implements CommandLineRunner {

    private final ApplicationContext context;

    public Initializer(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        var initUsers = context.getBean(InitializeUsers.class);
        initUsers.admin();
    }
}
