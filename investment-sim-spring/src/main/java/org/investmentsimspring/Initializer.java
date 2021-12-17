package org.investmentsimspring;

import org.investmentsimspring.domain.security.UserRole;
import org.investmentsimspring.domain.users.CreateUserDto;
import org.investmentsimspring.domain.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;

@Component
public class Initializer implements CommandLineRunner {

    private final ApplicationContext context;

    @Autowired
    public Initializer(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        configureAdmin(context.getBean(UsersService.class));
    }

    private void configureAdmin(UsersService usersService) throws IOException {
        // create default admin
        CreateUserDto dto = new CreateUserDto();
        dto.username = "admin";
        dto.email = "admin@investmentsim.org";
        // dto.password = UUID.randomUUID().toString(); // generate password
        dto.password = "admin";
        dto.role = UserRole.ADMIN.name();
        usersService.createUser(dto);

        // log password
        System.out.println("password: " + dto.password);

        // write admin details to file
        File file = new File("./output/admin-details.txt");
        Writer writer = new FileWriter(file);
        String string = LocalDateTime.now() + "\n" +
                "username: " + dto.username + "\n" +
                "email: " + dto.email + "\n" +
                "password: " + dto.password + "\n";
        writer.write(string);
        writer.close();
    }
}
