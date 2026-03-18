package com.wanted.crud.user.model.service;

import com.wanted.crud.user.model.dao.UserDAO;
import com.wanted.crud.user.model.dao.StudentDAO;
import com.wanted.crud.user.model.dao.InstructorDAO;

import com.wanted.crud.user.model.dto.UserDTO;
import com.wanted.crud.user.model.dto.StudentDTO;
import com.wanted.crud.user.model.dto.InstructorDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserDAO userDAO;
    private final StudentDAO studentDAO;
    private final InstructorDAO instructorDAO;
    private final Connection connection;

    public UserService(Connection connection) {
        this.connection = connection;
        this.userDAO = new UserDAO(connection);
        this.studentDAO = new StudentDAO(connection);
        this.instructorDAO = new InstructorDAO(connection);
    }

    // ===== User =====
    public List<UserDTO> selectAllUsers() {
        try {
            return userDAO.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException("유저 전체 조회 중 Error 발생!! /UserService2");
        }
    }

    public UserDTO login(String id, String password) {
        try {
            return userDAO.login(id, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long saveUser(UserDTO newUser) {
        try {
            return userDAO.save(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("회원가입 중 Error 발생!!! 🚨");
        }
    }

    // ===== Student =====
    public List<StudentDTO> selectAllStudents() {
        try {
            return studentDAO.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException("학생 전체 조회 중 Error 발생!! /UserService2");
        }
    }

    public StudentDTO findById(long id) throws SQLException {
        return studentDAO.findById(id);
    }

    // ===== Instructor =====
    public List<InstructorDTO> selectAllInstructors() {
        try {
            return instructorDAO.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException("강사 전체 조회 중 Error 발생!! /UserService2");
        }
    }
}