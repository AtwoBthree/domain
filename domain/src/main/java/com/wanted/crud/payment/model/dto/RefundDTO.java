package com.wanted.crud.payment.model.dto;

import java.util.Date;

public class RefundDTO {
    private long refundId;
    private long refundAmount;
    private boolean refundStatus;
    private Date refundAt;
    private long paymentId;

    @Override
    public String toString() {
        return "RefundDTO{" +
                "refundId=" + refundId +
                ", refundAmount=" + refundAmount +
                ", refundStatus=" + refundStatus +
                ", refundAt=" + refundAt +
                ", paymentId=" + paymentId +
                '}';
    }

    public long getRefundId() {
        return refundId;
    }

    public void setRefundId(long refundId) {
        this.refundId = refundId;
    }

    public long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public boolean isRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(boolean refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Date getRefundAt() {
        return refundAt;
    }

    public void setRefundAt(Date refundAt) {
        this.refundAt = refundAt;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public RefundDTO(long refundId, long refundAmount, boolean refundStatus, Date refundAt, long paymentId) {
        this.refundId = refundId;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
        this.refundAt = refundAt;
        this.paymentId = paymentId;
    }
}

