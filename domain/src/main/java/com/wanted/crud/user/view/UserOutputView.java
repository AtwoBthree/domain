package com.wanted.crud.user.view;

import com.wanted.crud.user.model.dto.UserDTO;

import java.util.List;

public class UserOutputView {
    public void printError(String message) {
        System.out.println(message);
    }

    public void printMessage(String s) {
        System.out.println("=========================================");
        System.out.println(s);
        System.out.println("=========================================");

    }

    public void printUsers(List<UserDTO> userList) {

        if (userList == null || userList.isEmpty()) {
            System.out.println("조회된 사용자가 없습니다.");
            return;
        }

        System.out.println("===사용자 전체 조회 목록 결과===");
        for (UserDTO userDTO : userList) {
            System.out.println(userDTO);
        }
    }
}
