package com.wanted.crud.payment.model.dto;

import java.util.Date;

public class PaymentDTO {
    private long paymentId;
    private long paymentAmount;
    private String paymentMethod;
    private boolean status;
    private Date paidAt;
    private long studentId;
    private long courseId;

    public PaymentDTO(long paymentId, long paymentAmount, String paymentMethod, boolean status, Date paidAt, long studentId, long courseId) {
        this.paymentId = paymentId;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paidAt = paidAt;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public long getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Date paidAt) {
        this.paidAt = paidAt;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
