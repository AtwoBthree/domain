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




    // 비번 찾기 메서드
    public String findPassword(String userid, String phoneNumber) {
        return service.findPassord(userid, phoneNumber);
    }

    public String findId(String name, String phoneNumber) {
        return service.findId(name, phoneNumber);
    }

    // ===== Instructor =====
    public List<InstructorDTO> selectAllInstructors() {
        return service.selectAllInstructors();
    }

    public Long instructorFindId(Long userno) {
        return service.findInstructorId(userno);
    }

}