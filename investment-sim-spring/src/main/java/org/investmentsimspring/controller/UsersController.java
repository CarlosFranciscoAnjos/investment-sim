package org.investmentsimspring.controller;

import org.investmentsimspring.domain.users.CreateUserDto;
import org.investmentsimspring.domain.users.UserDto;
import org.investmentsimspring.domain.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {

    private final UsersService service;

    @Autowired
    public UsersController(UsersService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public UserDto createUser(@RequestBody CreateUserDto dto) {
        try {
            return service.createUser(dto);
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

}
