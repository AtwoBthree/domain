package com.wanted.crud.user.controller;

import com.wanted.crud.user.model.dto.UserDTO;
import com.wanted.crud.user.model.service.UserService;

import java.util.List;

public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public List<UserDTO> selectAllUsers() {
        return service.selectAllUsers();
    }

    public UserDTO login(String id, String password) {
        return service.login(id, password);
    }
}
