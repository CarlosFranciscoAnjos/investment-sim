package org.investmentsimspring.services;

import org.investmentsimspring.database.UsersRepository;
import org.investmentsimspring.domain.users.Email;
import org.investmentsimspring.domain.users.User;
import org.investmentsimspring.domain.users.UserBuilder;
import org.investmentsimspring.domain.users.Username;
import org.investmentsimspring.models.users.CreateUserDto;
import org.investmentsimspring.models.users.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "UsersService")
public class UsersService implements UserDetailsService {

    private final UsersRepository repo;

    public UsersService(UsersRepository repo) {
        this.repo = repo;
    }

    public List<UserDto> getAllUsers() {
        return repo.findAll().stream().map(User::toDto).collect(Collectors.toList());
    }

    public UserDto createUser(CreateUserDto dto) {
        User user = repo.save(UserBuilder.build(dto));
        return user.toDto();
    }

    public UserDto getUser(long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User with id %d not found", id)));;
        return user.toDto();
    }

    protected User getByEmailOrUsername(String input) {
        // find by username
        try {
            Username username = new Username(input);
            User user = repo.findByUsername(username);
            if (user != null) return user;
        } catch (IllegalArgumentException | IllegalStateException ignored) {
        }
        // find by email
        try {
            Email email = new Email(input);
            User user = repo.findByEmail(email);
            if (user != null) return user;
        } catch (IllegalArgumentException | IllegalStateException ignored) {
        }
        // return null if not found
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        User user = getByEmailOrUsername(input);
        if (user == null)
            throw new UsernameNotFoundException(String.format("User not found for input: %s", input));
        return user;
    }

}
