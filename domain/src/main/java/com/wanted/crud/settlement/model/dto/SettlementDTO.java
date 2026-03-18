package com.wanted.crud.settlement.model.dto;

import java.util.Date;

public class SettlementDTO {

    private Long settlementId;
    private Long totalRevenue;
    private boolean status;
    private Date settlementDate;
    private Long instructorId;
    private Long courseId;

    @Override
    public String toString() {
        return "SettlementDTO{" +
                "settlementId=" + settlementId +
                ", totalRevenue=" + totalRevenue +
                ", status=" + status +
                ", settlementDate=" + settlementDate +
                ", instructorId=" + instructorId +
                ", courseId=" + courseId +
                '}';
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
