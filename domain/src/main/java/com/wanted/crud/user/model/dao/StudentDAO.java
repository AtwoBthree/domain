package com.wanted.crud.user.model.dao;

import com.wanted.crud.global.utils.QueryUtil;
import com.wanted.crud.user.model.dto.StudentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private final Connection connection;

    public StudentDAO(Connection connection) {this.connection = connection;}
    public List<StudentDTO> selectAll() throws SQLException {

        String query = QueryUtil.getQuery("student.selectAll");
        List<StudentDTO> studentList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                StudentDTO student = new StudentDTO(
                        rset.getLong("student_id"),
                        rset.getLong("user_no")
                );

                studentList.add(student);
            }
        }

        return studentList;
    }

}