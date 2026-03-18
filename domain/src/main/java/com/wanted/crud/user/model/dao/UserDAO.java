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

    //selectAll
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

    //마이페이지에 쓰일 정보.
    public UserDTO findById(long id) throws SQLException {
        String query = QueryUtil.getQuery("users.findById");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);

            //select 결과는 ResultSet 객체로 변환!!
            ResultSet rset = pstmt.executeQuery();

            if(rset.next()) {
                return new UserDTO(
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
            }
        }
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
                        //  여기에 user_no 도 getString
                        rs.getString("user_id"),
                        rs.getString("user_password"),
                        rs.getString("user_role")
                );
            }
        }
        return null;
    }

    // Insert문
    public Long save(UserDTO newUser) throws SQLException {
        // 1. properties 등에서 INSERT 쿼리를 가져옵니다.
        // 예상 쿼리: INSERT INTO `USER` (user_id, user_password, user_name, user_phone_number, user_role) VALUES (?, ?, ?, ?, ?)
        String query = QueryUtil.getQuery("users.insert");
        Long generatedId = null;

        // 2. INSERT 실행 후 생성된 PK값을 가져오기 위해 RETURN_GENERATED_KEYS 옵션을 추가합니다.
        try (PreparedStatement pstmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // 3. UserDTO의 데이터들을 쿼리의 ?(파라미터)에 바인딩합니다.
            // DB에 설정한 DEFAULT 값(user_price, created_at, status)은 생략하고 필수 값만 넣는다고 가정했습니다.
            pstmt.setString(1, newUser.getUserId());
            pstmt.setString(2, newUser.getUserPassword());
            pstmt.setString(3, newUser.getUserName());
            pstmt.setString(4, newUser.getUserPhoneNumber());
            pstmt.setString(5, newUser.getUserRole());

            // 4. INSERT 쿼리를 실행하고 영향을 받은 행(row)의 개수를 반환받습니다.
            int affectedRows = pstmt.executeUpdate();

            // 5. 성공적으로 INSERT 되었다면, 생성된 PK(user_no)를 조회하여 반환합니다.
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getLong(1);
                    }
                }
            }
        }

        return generatedId; // 성공 시 생성된 유저번호 반환, 실패 시 null 반환
    }
}

