package com.wanted.crud.user.model.service;

import com.wanted.crud.user.model.dao.InstructorDAO;
import com.wanted.crud.user.model.dto.InstructorDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InstructorService {

    private final InstructorDAO instructorDAO;
    private final Connection connection;

    public InstructorService(Connection connection) {
        this.connection = connection;
        this.instructorDAO = new InstructorDAO(connection);
    }

    public List<InstructorDTO> selectAllInstructors() {
        try {
            return instructorDAO.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException("강사 전체 조회 중 Error 발생!! /InstructorService");
        }
    }
}