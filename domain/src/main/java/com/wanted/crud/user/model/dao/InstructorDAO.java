package com.wanted.crud.user.model.dao;

import com.wanted.crud.global.utils.UserQueryUtil;
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

        String query = UserQueryUtil.getQuery("instructor.selectAll");
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

    public Long findId(Long userNo) throws SQLException {
        String query = UserQueryUtil.getQuery("instructor.findId");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, userNo);

            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                return rset.getLong("instructor_id");

            }
        }
        return null;
    }





}