package com.wanted.crud.course.view;

import com.wanted.crud.course.controller.UserController;
import com.wanted.crud.course.model.dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class UserInputView {
    private final UserController controller;
    private final UserOutputView outputView;
    private final Scanner sc = new Scanner(System.in);

    public UserInputView(UserController controller, UserOutputView outputView) {
        this.controller = controller;
        this.outputView = outputView;
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println();
            System.out.println("======================================");
            System.out.println("              쿼리테스트 환경            ");
            System.out.println("======================================");
            System.out.println("1. User Select All");
            System.out.println("2. User Select All where ID");
            System.out.println("3. add User");
            System.out.println("4. delete User");

            int menu = inputInt();

            switch (menu) {
                case 1:
                    UserSelect();
                    break;
                default:
                    outputView.printError("다시선택해주세요");
            }
        }
    }

    private void UserSelect() {
        outputView.printMessage("\n--- [Select * 실행] --");
        List<UserDTO> userList = controller.selectAllUsers();
        outputView.printUsers(userList);
    }

    private int inputInt() {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요 : ");
            }
        }
    }
}
