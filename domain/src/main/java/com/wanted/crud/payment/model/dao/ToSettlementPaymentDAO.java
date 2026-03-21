package com.wanted.crud.payment.model.dao; // 패키지명은 상황에 맞게 수정하세요

import com.wanted.crud.global.utils.PaymentQueryUtil;
import com.wanted.crud.payment.model.dto.PaymentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // 날짜 처리를 위해 import
import java.util.ArrayList;
import java.util.List;

public class ToSettlementPaymentDAO {
    private final Connection connection;

    public ToSettlementPaymentDAO(Connection connection) {
        this.connection = connection;
    }

    // 🌟 수정: 시작 날짜(startDate)와 종료 날짜(endDate)를 매개변수로 받습니다.
    public List<PaymentDTO> selectPaymentsByDateRange(Date startDate, Date endDate) throws SQLException {
        String query = PaymentQueryUtil.getQuery("payment.selectForSettlement");
        List<PaymentDTO> paymentList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            // 🌟 쿼리의 첫 번째 '?'와 두 번째 '?'에 날짜를 쏙 넣어줍니다!
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);

            // ResultSet도 try-with-resources로 묶어주면 메모리 누수를 완벽하게 막을 수 있습니다.
            try (ResultSet rset = pstmt.executeQuery()) {
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
            }
            return paymentList;
        }
    }
}