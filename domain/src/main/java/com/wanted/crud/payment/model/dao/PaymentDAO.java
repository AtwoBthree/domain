package com.wanted.crud.payment.model.dao;

import com.wanted.crud.payment.model.dto.PaymentDTO;
import com.wanted.crud.global.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private final Connection connection;

    public PaymentDAO(Connection connection) { this.connection = connection;}

    public List<PaymentDTO> selectAll() throws SQLException {
        String query = QueryUtil.getQuery("payment.selectAll");
        List<PaymentDTO> paymentList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                PaymentDTO payment = new PaymentDTO(
                        rset.getLong("payment_id"),
                        rset.getLong("payment_amount"),
                        rset.getString("payment_method"),
                        rset.getBoolean("status"),
                        rset.getDate("paid_at"),
                        rset.getLong("student_id"),
                        rset.getLong("course_id")
                );
                paymentList.add(payment);
            }
            return paymentList;
        }
    }


}
