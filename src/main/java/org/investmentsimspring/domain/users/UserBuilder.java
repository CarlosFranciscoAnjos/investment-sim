package org.investmentsimspring.domain.users;

import org.investmentsimspring.domain.contracts.Builder;
import org.investmentsimspring.domain.security.Hash;
import org.investmentsimspring.domain.security.UserRole;
import org.investmentsimspring.models.users.CreateUserDto;
import org.investmentsimspring.models.users.UserDto;

public class UserBuilder implements Builder<User> {

    public static User build(UserDto dto) {
        UserBuilder builder = new UserBuilder();
        builder.id(dto.id).name(dto.username).email(dto.email).hash(dto.hash).role(dto.role);
        return builder.build();
    }

    public static User build(CreateUserDto dto) {
        UserBuilder builder = new UserBuilder();
        builder.name(dto.username).email(dto.email).password(dto.password).role(dto.role);
        return builder.build();
    }

    private long id;
    private Username username;
    private Email email;
    private Hash hash;
    private UserRole role;

    public UserBuilder() {
        this.id = 0;
    }

    public UserBuilder id(long id) {
        this.id = id;
        return this;
    }

    public UserBuilder name(String name) {
        this.username = new Username(name);
        return this;
    }

    public UserBuilder email(String email) {
        this.email = new Email(email);
        return this;
    }

    public UserBuilder password(String password) {
        this.hash = new Hash(password);
        return this;
    }

    public UserBuilder hash(String hash) {
        this.hash = Hash.fromHash(hash);
        return this;
    }

    public UserBuilder role(String role) {
        this.role = UserRole.valueOf(role);
        return this;
    }

    @Override
    public User build() {
        return id <= 0 ? new User(username, email, hash, role)
                : new User(id, username, email, hash, role);
    }
}
