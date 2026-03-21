package com.wanted.crud.settlement.model.dto;

import java.util.Date;

public class SettlementDTO {

    private Long settlementId;
    private Long courseId;
    private Date settlementDate;
    private Long instructorId;
    private Long rawAmount; //원금
    private Long commssion; //강사의 돈
    private Long finalAmount; //관리자의 돈
    private String status;

    public SettlementDTO(Long settlementId, Long courseId, Date settlementDate, Long instructorId, Long rawAmount, Long commssion, Long finalAmount, String status) {
        this.settlementId = settlementId;
        this.courseId = courseId;
        this.settlementDate = settlementDate;
        this.instructorId = instructorId;
        this.rawAmount = rawAmount;
        this.commssion = commssion;
        this.finalAmount = finalAmount;
        this.status = status;
    }

    public SettlementDTO(Long settlementId, String status, Date settlementDate, Long instructorId, Long courseId) {
        this.settlementId = settlementId;
        this.courseId = courseId;
        this.settlementDate = settlementDate;
        this.instructorId = instructorId;
        this.status = status;
    }

    @Override
    public String toString() {
        // 1. 데이터 포맷팅 (null 방지 안전장치 포함)
        String formattedRaw = (rawAmount != null) ? String.format("%,d", rawAmount) : "0";
        String formattedCommission = (commssion != null) ? String.format("%,d", commssion) : "0";
        String formattedFinal = (finalAmount != null) ? String.format("%,d", finalAmount) : "0";

        String safeDate = (settlementDate != null) ? settlementDate.toString() : "미정";

        // 상태값 변환 (프로젝트 설정에 맞게 "완료", "대기" 등으로 바꿔서 쓰세요!)
        String statusText = "1".equals(status) ? "정산 완료" : "정산 대기";

        // 2. 영수증 그리기
        StringBuilder sb = new StringBuilder();
        sb.append("  .----/        \\--------------------------------------\n");
        sb.append("  |    ____  _____  _____  _____  _      _____ \n");
        sb.append("  |   / ___||  ___||_   _||_   _|| |    |  ___|\n");
        sb.append("  |   \\___ \\| |__    | |    | |  | |    | |__  \n");
        sb.append("  |    ___) |  __|   | |    | |  | |___ |  __| \n");
        sb.append("  |   |____/|_____|  |_|    |_|  |_____||_____|\n");
        sb.append("  ├--------------------------------------------------\n");
        sb.append("  |         [ OFFICIAL SETTLEMENT STATEMENT ]\n");
        sb.append("  ├--------------------------------------------------\n");

        String formatId = (settlementId != null) ? String.format("%04d", settlementId) : "0000";
        sb.append("  |    🆔 정산 번호 : #S-").append(formatId).append("\n");
        sb.append("  |    👨‍🏫 강사 번호 : ").append(instructorId).append("번 강사\n");
        sb.append("  |    📘 강좌 번호 : ").append(courseId).append("번 강좌\n");
        sb.append("  ╟──────────────────────────────────────────────────\n");
        sb.append("  |    💰 총 매출액(원금)  : ").append(formattedRaw).append(" 원\n");
        sb.append("  |    💸 강사 정산금      : ").append(formattedCommission).append(" 원\n");
        sb.append("  |    🏢 플랫폼 수수료    : ").append(formattedFinal).append(" 원\n");
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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

    public Long getRawAmount() {
        return rawAmount;
    }

    public void setRawAmount(Long rawAmount) {
        this.rawAmount = rawAmount;
    }

    public Long getCommssion() {
        return commssion;
    }

    public void setCommssion(Long commssion) {
        this.commssion = commssion;
    }

    public Long getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Long finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
