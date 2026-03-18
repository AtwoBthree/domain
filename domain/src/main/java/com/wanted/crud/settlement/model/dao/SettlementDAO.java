package com.wanted.crud.settlement.model.dao;

import com.wanted.crud.settlement.model.dto.SettlementDTO;
import com.wanted.crud.global.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 강사가 select 하면 나오도록.
public class SettlementDAO {

    private final Connection connection;

    public SettlementDAO(Connection connection) {
        this.connection = connection;
    }

    public SettlementDTO find(long id) throws SQLException {

        String query = QueryUtil.getQuery("settlement.findById");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setLong(1, id);

            // select 결과는 ResultSet 객체로 반환!!
            ResultSet rset = pstmt.executeQuery();

            if(rset.next()) {
                return new SettlementDTO(
                        rset.getLong("settlement_id"),
                        rset.getLong("total_revenue"),
                        rset.getBoolean("status"),
                        rset.getDate("settlement_date"),
                        rset.getLong("instructor_id"),
                        rset.getLong("course_id")
                );
            }
        }
        return null;
    }


}
