package com.wanted.crud.payment.controller;

import com.wanted.crud.payment.model.dto.PaymentDTO;
import com.wanted.crud.payment.model.service.PaymentService;

import java.util.List;

public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) { this.service = service;}

    public List<PaymentDTO> selectAllUsers() { return service.selectAllPayment();}




}
