package com.wanted.crud.settlement.controller;

import com.wanted.crud.settlement.model.dto.SettlementDTO;
import com.wanted.crud.settlement.model.service.SettlementService;

public class SettlementController {
    private final SettlementService settlementService;

    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    public SettlementDTO findSettlementById(long id){
        return settlementService.findbyId(id);
    }
}
