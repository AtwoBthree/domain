package com.wanted.crud.payment.model.dto;

import com.wanted.crud.global.utils.PaymentQueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToSettlementPaymentDTO {
    private final Connection connection;

    public ToSettlementPaymentDTO(Connection connection) { this.connection = connection;}

    public List<PaymentDTO> selectAll() throws SQLException {
        String query = PaymentQueryUtil.getQuery("payment.selectAll");
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


