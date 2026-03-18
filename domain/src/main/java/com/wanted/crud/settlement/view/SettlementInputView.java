package com.wanted.crud.settlement.view;

import com.wanted.crud.settlement.controller.SettlementController;

import java.util.Scanner;

public class SettlementInputView {

    private final SettlementController controller;
    private final SettlementOutputView outputView;
    private final Scanner sc = new Scanner(System.in);

    public SettlementInputView(SettlementController controller, SettlementOutputView outputView) {
        this.controller = controller;
        this.outputView = outputView;
    }

    private void displaySettlement() {

    }

}
