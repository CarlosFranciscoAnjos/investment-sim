package org.investmentsimspring.models.users;

import org.investmentsimspring.domain.contracts.Dto;

public class CreateUserDto implements Dto {

    public String username;
    public String email;
    public String password;
    public String role;
}
