package com.wanted.crud.user.model.dao;

import com.wanted.crud.course.model.dto.CourseDTO;
import com.wanted.crud.global.utils.QueryUtil;
import com.wanted.crud.user.model.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Connection connection;

    public UserDAO(Connection connection) {this.connection = connection;}
    public List<UserDTO> selectAll() throws SQLException {
        String query = QueryUtil.getQuery("users.selectAll");
        List<UserDTO> userList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                UserDTO user = new UserDTO(
                        rset.getLong("user_no"),
                        rset.getString("user_id"),
                        rset.getString("user_password"),
                        rset.getString("user_name"),
                        rset.getString("user_phone_number"),
                        rset.getLong("user_price"),
                        rset.getString("user_role"),
                        rset.getDate("created_at"),
                        rset.getBoolean("status")
                );

                userList.add(user);
            }
        }
        return userList;
    }

    public Long save(CourseDTO newCourse) {
        String query = QueryUtil.getQuery("users.selectAll");

        return null;
    }

    public UserDTO login(String id, String password) throws SQLException {

        String query = QueryUtil.getQuery("users.login");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, id);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new UserDTO(
                        rs.getString("user_id"),
                        rs.getString("user_password"),
                        rs.getString("user_role")
                );
            }
        }
        return null;
    }
}

