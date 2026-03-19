package com.wanted.crud.user.model.dao;

import com.wanted.crud.global.utils.QueryUtil;
import com.wanted.crud.user.model.dto.InstructorDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {

    private final Connection connection;

    public InstructorDAO(Connection connection) {this.connection = connection;}
    public List<InstructorDTO> selectAll() throws SQLException {

        String query = QueryUtil.getQuery("instructor.selectAll");
        List<InstructorDTO> instructorList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                InstructorDTO instructor = new InstructorDTO(
                        rset.getLong("instructor_id"),
                        rset.getDate("created_at"),
                        rset.getLong("user_no")
                );

                instructorList.add(instructor);
            }
        }

        return instructorList;
    }

    public Long findId(Long userno) throws SQLException {
        String query = QueryUtil.getQuery("instructor.findId");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, userno);

            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                return rset.getLong("instructor_id"); // 비밀번호 리턴
            }
        }
        return null;
    }



}