package com.wanted.crud.user.model.service;

import com.wanted.crud.user.model.dao.StudentDAO;
import com.wanted.crud.user.model.dto.StudentDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO;
    private final Connection connection;

    public StudentService(Connection connection) {
        this.connection = connection;
        this.studentDAO = new StudentDAO(connection);
    }

    public List<StudentDTO> selectAllStudents() {
        try {
            return studentDAO.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException("학생 전체 조회 중 Error 발생!! /StudentService");
        }
    }
/*
    public StudentDTO findByUserId(String userId) {
        try {
            StudentDTO student = studentDAO.findByUserId(userId);
            if (student == null) {
                throw new RuntimeException("해당 유저의 수강생 정보가 존재하지 않습니다: " + userId);
            }
            return student;
        } catch (SQLException e) {
            throw new RuntimeException("수강생 정보 조회 중 Error 발생!! /StudentService", e);
        }
    }*/


}