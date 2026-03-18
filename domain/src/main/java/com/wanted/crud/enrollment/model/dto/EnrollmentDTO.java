package com.wanted.crud.enrollment.model.dto;

import java.util.Date;

public class EnrollmentDTO {
    private Long enrollmentId;
    private Long progressRate;
    private Date startDate;
    private Date endDate;
    private String status;
    private Long studentId;
    private Long courseId;

    public EnrollmentDTO(Long enrollmentId, Long progressRate, Date startDate, Date endDate, String status, Long studentId, Long courseId) {
        this.enrollmentId = enrollmentId;
        this.progressRate = progressRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Long getProgressRate() {
        return progressRate;
    }

    public void setProgressRate(Long progressRate) {
        this.progressRate = progressRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "EnrollmentDTO{" +
                "enrollmentId=" + enrollmentId +
                ", progressRate=" + progressRate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
