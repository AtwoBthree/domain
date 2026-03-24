package com.wanted.crud.payment.model.dao; // 패키지명은 상황에 맞게 수정하세요

import com.wanted.crud.global.utils.PaymentQueryUtil;
import com.wanted.crud.payment.model.dto.PaymentDTO;
import com.wanted.crud.payment.model.dto.ToSettlementPaymentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToSettlementPaymentDAO {
    private final Connection connection;

    public ToSettlementPaymentDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ToSettlementPaymentDTO> selectForSettlement(Timestamp startDate) throws SQLException {
        String query = PaymentQueryUtil.getQuery("payment.selectForSettlement");
        List<ToSettlementPaymentDTO> paymentList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setTimestamp(1, startDate);

            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    ToSettlementPaymentDTO forStmPay = new ToSettlementPaymentDTO(
                            rset.getLong("raw_amount"),
                            rset.getLong("course_id")
                    );
                    paymentList.add(forStmPay);
                }
            }
            return paymentList;
        }
    }
}