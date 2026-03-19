package com.wanted.crud.payment.model.dao;

import com.wanted.crud.payment.model.dto.PaymentDTO;
import com.wanted.crud.global.utils.QueryUtil;

import java.sql.*;
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

    //세이브
    public Long save(PaymentDTO newPayment) throws SQLException {
        String query = QueryUtil.getQuery("payment.save");

        try (PreparedStatement pstmt = connection.prepareStatement(query , PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, newPayment.getPaymentId());
            pstmt.setLong(2, newPayment.getPaymentAmount());
            pstmt.setString(3, newPayment.getPaymentMethod());
            pstmt.setBoolean(4, newPayment.isStatus());
            pstmt.setDate(5, (Date)newPayment.getPaidAt());
            pstmt.setLong(6, newPayment.getStudentId());

            // dml 구문은 executeUpdate 를 통해 query 를 실행한다.
            // 결과 값은 정수 자료형 즉 영향을 받은 행의 갯수가 리턴된다.
            int affectedRows = pstmt.executeUpdate();
            if(affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()) {
                    return rs.getLong(1); //pk값 쫘르륵
                }
            }
        }

        return null;


    }




}
