package com.wanted.crud.user.controller;

import com.wanted.crud.user.model.dto.UserDTO;
import com.wanted.crud.user.model.dto.StudentDTO;
import com.wanted.crud.user.model.dto.InstructorDTO;

import com.wanted.crud.user.model.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // ===== User =====
    public List<UserDTO> selectAllUsers() {
        return service.selectAllUsers();
    }

    public UserDTO login(String id, String password) {
        return service.login(id, password);
    }

    public Long createUser(String id, String password, String name, String phoneNumber, String role) {

        UserDTO newUser = new UserDTO(1L, id, password, name, phoneNumber, null, role, null, true);
        return service.saveUser(newUser);
    }

    // ===== Student =====
    public List<StudentDTO> selectAllStudents() {
        return service.selectAllStudents();
    }

    public StudentDTO findCourseById(long id) {
        try {
            return service.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("강좌 상세 조회 중 오류 발생!! 🚨");
        }
    }

    // ===== Instructor =====
    public List<InstructorDTO> selectAllInstructors() {
        return service.selectAllInstructors();
    }
}