package com.wanted.crud.user.view;

import com.wanted.crud.user.controller.InstructorController;
import com.wanted.crud.user.model.dto.UserDTO;

import java.util.Scanner;

public class InstructorInputView {

    private final InstructorController controller;
    private final InstructorOutputView outputView;
    private final Scanner sc = new Scanner(System.in);

    public InstructorInputView(InstructorController controller, InstructorOutputView outputView) {
        this.controller = controller;
        this.outputView = outputView;
    }

    public void instructorMenu(UserDTO loginUser) {
        while (true) {
            outputView.printMenu();
            int choice = inputInt();

            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 0:
                    return;
                default:
                    outputView.printError("잘못된 입력입니다.");
            }
        }
    }





    private int inputInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }
}