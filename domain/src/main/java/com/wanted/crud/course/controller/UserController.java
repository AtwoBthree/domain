package com.wanted.crud.course.controller;

import com.wanted.crud.course.model.dto.UserDTO;
import com.wanted.crud.course.model.service.UserService;

import java.util.List;

public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public List<UserDTO> selectAllUsers() {
        return service.selectAllUsers();
    }
}
