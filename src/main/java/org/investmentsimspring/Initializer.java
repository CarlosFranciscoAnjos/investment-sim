package org.investmentsimspring;

import org.investmentsimspring.domain.security.UserRole;
import org.investmentsimspring.domain.users.CreateUserDto;
import org.investmentsimspring.domain.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;

@Configuration
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

    protected void configureAdmin(UsersService usersService) throws IOException {
        // create default admin
        CreateUserDto dto = new CreateUserDto() {
            {
                username = "admin";
                email = "admin@investmentsim.org";
                password = "admin";
                // password = UUID.randomUUID().toString();
                role = UserRole.ADMIN.name();
            }
        };
        usersService.createUser(dto);
        // log password
        System.out.println("password: " + dto.password);
        // write admin details to file
        Writer writer = new FileWriter("./output/admin-credentials.txt");
        writer.write(String.format("%s\nusername: %s\nemail: %s\npassword: %s\n",
                LocalDateTime.now(), dto.username, dto.email, dto.password));
        writer.close();
    }
}
