package com.wanted.crud.course.model.service;

import com.wanted.crud.course.model.dao.UserDAO;
import com.wanted.crud.course.model.dto.UserDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



public class UserService {
    private final UserDAO userDAO;
    private final Connection connection;
    public UserService(Connection connection) {
        this.connection = connection;
        this.userDAO = new UserDAO(connection);
    }
    public List<UserDTO> selectAllUsers() {
        try {
            return userDAO.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException("유저 전체 조회 중 Error 발생!! /UserService");
        }
    }



    }
