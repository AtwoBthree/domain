package com.wanted.crud.settlement.model.service;

import com.wanted.crud.settlement.model.dao.SettlementDAO;
import com.wanted.crud.settlement.model.dto.SettlementDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class SettlementService {

    private final SettlementDAO settlementDAO;
    //private final InstructorDTO instructorDTO;
    private final Connection connection;

    public SettlementService(Connection connection) {
        this.connection = connection;
        this.settlementDAO = new SettlementDAO(connection);
    }

    public SettlementDTO findbyId(long id){

        try {
            return settlementDAO.find(id);
        } catch (SQLException e) {
            throw new RuntimeException("정산 조회 중 오류 발생!! 🚨");
        }
    }

}
