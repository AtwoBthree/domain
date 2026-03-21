package com.wanted.crud.settlement.model.dto;

import java.util.Date;

public class SettlementDTO {

    private Long settlementId;
    private Long totalRevenue;
    private boolean status;
    private Date settlementDate;
    private Long instructorId;
    private Long courseId;

    // ✨ 고양이와 줄 맞춤을 위한 정밀 여백 계산기 (너비 54 고정)
    private String padRight(String text) {
        int targetWidth = 54;
        int width = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= '가' && c <= '힣') width += 2;
            else width += 1;
        }
        StringBuilder padded = new StringBuilder(text);
        while (width < targetWidth) {
            padded.append(" ");
            width++;
        }
        return padded.toString();
    }

    @Override
    public String toString() {
        // 1. 순수 데이터 포맷팅
        String formattedRevenue = String.format("%,d원", totalRevenue);
        boolean isDone = "1".equals(String.valueOf(status));
        String statusText = isDone ? "◆ 정산완료(SETTLED)" : "◇ 정산대기(PENDING)";
        String safeDate = (settlementDate != null) ? String.valueOf(settlementDate) : "확인 중 (Pending)";

        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        // 🚀 고양이 담당관 (헤더)
        sb.append("       /\\___/\\           \n");
        sb.append("      ( > ^ < )  🐾 Meow! 정산 데이터를 가져왔다냥! \n");
        sb.append("      (  :   : )         \n");
        sb.append("       (__=__)____       \n");

        // 🚀 정산서 본체 (오른쪽 테두리 제거)
        sb.append("  .----/       \\--------------------------------------\n");
        sb.append("  |   ____  _____  _____  _____  _      _____ \n");
        sb.append("  |  / ___||  ___||_   _||_   _|| |    |  ___|\n");
        sb.append("  |  \\___ \\| |__    | |    | |  | |    | |__  \n");
        sb.append("  |   ___) |  __|   | |    | |  | |___ |  __| \n");
        sb.append("  |  |____/|_____|  |_|    |_|  |_____||_____|\n");
        sb.append("  ├--------------------------------------------------\n");
        sb.append("  |         [ OFFICIAL SETTLEMENT STATEMENT ]\n");
        sb.append("  ├--------------------------------------------------\n");
        sb.append("  | \n");
        sb.append("  |    🆔 정산 번호 : #S-").append(String.format("%04d", settlementId)).append("\n");
        sb.append("  |    👨‍🏫 강사 번호 : ").append(instructorId).append("번 강사\n");
        sb.append("  |    📘 강좌 번호 : ").append(courseId).append("번 강좌\n");
        sb.append("  | \n");
        sb.append("  ╟──────────────────────────────────────────────────\n");
        sb.append("  |    💰 총 매출액 : ").append(formattedRevenue).append("\n");
        sb.append("  |    ✨ 정산 상태 : ").append(statusText).append("\n");
        sb.append("  |    📅 정산 일자 : ").append(safeDate).append("\n");
        sb.append("  ├--------------------------------------------------\n");
        sb.append("  |    || ||| || |||| || ||| || ||||\n");
        sb.append("  |    CERTIFIED BY NYAN ADMINISTRATION 🐾\n");
        sb.append("  '--------------------------------------------------\n");

        return sb.toString();
    }

    public Long getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(Long settlementId) {
        this.settlementId = settlementId;
    }

    public Long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public SettlementDTO(Long settlementId, Long totalRevenue, boolean status, Date settlementDate, Long instructorId, Long courseId) {
        this.settlementId = settlementId;
        this.totalRevenue = totalRevenue;
        this.status = status;
        this.settlementDate = settlementDate;
        this.instructorId = instructorId;
        this.courseId = courseId;
    }

    public SettlementDTO(){}
}
