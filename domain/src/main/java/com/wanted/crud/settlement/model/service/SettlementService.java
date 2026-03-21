package com.wanted.crud.settlement.model.service;

import com.wanted.crud.settlement.model.dao.SettlementDAO;
import com.wanted.crud.settlement.model.dto.SettlementDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    // 강사별 총수익
    public List<SettlementDTO> getRevenueByInstructor() {
        try {
            return settlementDAO.selectRevenueByInstructor();
        } catch (SQLException e) {
            throw new RuntimeException("🚨 강사별 수익 조회 중 오류: " + e.getMessage());
        }
    }

//    public List<SettlementDTO> getRevenue(){
//        try {
//            List<SettlementDTO> settlementList = settlementDAO.selectRevenueByInstructor();
//
//            if (settlementList == null || settlementList.isEmpty()) {
//                System.out.println("❌ 정산된 내역이 없습니다.");
//            }
//            return settlementList;
//
//        } catch (SQLException e) {
//            throw new RuntimeException("🚨정산 목록 조회 중 Error 발생", e);
//        }
//    }

    public Long saveSettlement(SettlementDTO saveNewSettlement){
        try {
            Long saveId = settlementDAO.save(saveNewSettlement);

            if (saveId == null){
                System.out.println("🚨 정산 내역 저장에 실패했습니다.");
            }
            return saveId;
        } catch (SQLException e) {
            throw new RuntimeException("🚨 정산 내역 저장 중 Error 발생", e);
        }
    }


}
