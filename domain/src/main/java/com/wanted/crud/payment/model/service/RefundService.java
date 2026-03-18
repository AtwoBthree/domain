package com.wanted.crud.payment.model.service;

import com.wanted.crud.payment.model.dao.RefundDAO;
import com.wanted.crud.payment.model.dto.RefundDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RefundService {
    private final RefundDAO refundDAO;
    private final Connection connection;
    public RefundService(Connection connection) {
        this.connection = connection;
        this.refundDAO = new RefundDAO(connection);
    }

    public List<RefundDTO> selectAllRefund() {
        try {
            return refundDAO.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException("환불 전체 조회 중 Error 발생!!");
        }
    }
}
