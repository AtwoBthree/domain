package com.wanted.crud.settlement.view;

public class SettlementOutputView {

    public void printError(String message) {
        System.out.println("\n🚨 [ERROR] ❌ " + message);
    }

    public void printSuccess(String message) {
        System.out.println("\n✨ [SUCCESS] ✅ " + message);
    }
}
