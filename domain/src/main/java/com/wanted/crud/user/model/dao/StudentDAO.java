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


    //student_id 값으로 학생찾기. 혹은 user_no로 변경할수도 있음.
    public StudentDTO findById(long id) throws SQLException {
        String query = QueryUtil.getQuery("student.findById");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);

            //select 결과는 ResultSet 객체로 변환!!
            ResultSet rset = pstmt.executeQuery();

            if(rset.next()) {
                return new StudentDTO(
                        rset.getLong("student_id"),
                        rset.getLong("user_no")
                );
            }
        }
        return null;
    }



}