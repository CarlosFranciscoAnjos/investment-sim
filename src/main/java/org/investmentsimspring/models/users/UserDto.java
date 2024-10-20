package org.investmentsimspring.models.users;

import org.investmentsimspring.domain.contracts.Dto;

public class UserDto implements Dto {

    public long id;
    public String username;
    public String email;
    public String hash;
    public String role;

}
