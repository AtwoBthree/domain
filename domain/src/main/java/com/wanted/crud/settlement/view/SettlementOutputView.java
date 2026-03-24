package com.wanted.crud.settlement.view;

import com.wanted.crud.settlement.model.dto.SettlementDTO;
import com.wanted.crud.user.model.dto.UserDTO;

import java.util.List;

public class SettlementOutputView {

    public void printError(String message) {
        System.out.println("\n🚨 [ERROR] ❌ " + message);
    }

    public void printSuccess(String message) {
        System.out.println("\n✨ [SUCCESS] ✅ " + message);
    }

    public void printAllSettlement(List<SettlementDTO> settlementList) {
        System.out.println("👥 [ 정산 목록 ]");

        if (settlementList == null || settlementList.isEmpty()) {
            System.out.println("🚨 조회된 정산 내역이 없습니다.");
        } else {
            for (SettlementDTO settlementDTO : settlementList) {
                System.out.println(settlementDTO);
            }
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }


    public void printDoneSettlement(List<SettlementDTO> settlementList) {
        System.out.println("👥 [ 정산 목록 ]");

        if (settlementList == null || settlementList.isEmpty()) {
            System.out.println("🚨 조회된 정산 내역이 없습니다.");
        } else {
            for (SettlementDTO settlementDTO : settlementList) {
                System.out.println(settlementDTO);
            }
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
}
