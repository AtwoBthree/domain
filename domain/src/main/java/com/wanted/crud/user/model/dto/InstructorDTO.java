package com.wanted.crud.user.model.dto;

import java.util.Date;

public class InstructorDTO {

    private Long instructorId;
    private Date createdAt;
    private Long userNo;  // FK

    public InstructorDTO() {}

    public InstructorDTO(Long instructorId, Date createdAt, Long userNo) {
        this.instructorId = instructorId;
        this.createdAt = createdAt;
        this.userNo = userNo;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "InstructorDTO{" +
                "instructorId=" + instructorId +
                ", createdAt=" + createdAt +
                ", userNo=" + userNo +
                '}';
    }
}