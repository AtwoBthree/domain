package com.wanted.crud.payment.view;

import com.wanted.crud.payment.model.dto.PaymentDTO;

import java.util.List;

public class PaymentOutputView {
    public void printError(String message) {System.out.println(message);}
    public void printMessage(String s) {
        System.out.println("=====================");
        System.out.println(s);
        System.out.println("=====================");
    }

    public void printPayment(List<PaymentDTO> paymentList) {
        if (paymentList == null || paymentList.isEmpty()) {
            System.out.println("조회된 결제가 없습니다.");
            return;
        }

        System.out.println("===$$결제 전체 조회 목록 결과===");
        for (PaymentDTO paymentDTO : paymentList) {
            System.out.println(paymentDTO);
        }
    }
}
