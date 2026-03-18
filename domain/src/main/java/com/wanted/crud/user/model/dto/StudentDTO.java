package com.wanted.crud.user.model.dto;

public class StudentDTO {

    private Long studentId;
    private Long userNo;   // FK

    public StudentDTO() {}

    public StudentDTO(Long studentId, Long userNo) {
        this.studentId = studentId;
        this.userNo = userNo;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "studentId=" + studentId +
                ", userNo=" + userNo +
                '}';
    }
}