package com.wanted.crud.payment.model.service;

import com.wanted.crud.payment.model.dao.PaymentDAO;
import com.wanted.crud.payment.model.dto.PaymentDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PaymentService {
    private final PaymentDAO paymentDAO;
    private final Connection connection;
    public PaymentService(Connection connection) {
        this.connection = connection;
        this.paymentDAO = new PaymentDAO(connection);
    }

    public List<PaymentDTO> selectAllPayment() {
        try {
            return paymentDAO.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException("결제 전제 조회 중 Error 발생!! /UserService");
        }
    }
}
