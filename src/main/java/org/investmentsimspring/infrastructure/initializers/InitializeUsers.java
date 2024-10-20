package org.investmentsimspring.infrastructure.initializers;

import org.investmentsimspring.domain.security.UserRole;
import org.investmentsimspring.models.users.CreateUserDto;
import org.investmentsimspring.services.UsersService;
import org.springframework.stereotype.Component;

@Component
public class InitializeUsers {

    private final UsersService usersService;

    public InitializeUsers(UsersService usersService) {
        this.usersService = usersService;
    }

    public void admin() {
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
    }
}
