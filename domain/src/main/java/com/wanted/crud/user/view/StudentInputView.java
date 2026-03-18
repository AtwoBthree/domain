package com.wanted.crud.user.view;

import com.wanted.crud.user.controller.StudentController;
import com.wanted.crud.user.model.dto.UserDTO;

import java.util.Scanner;

public class StudentInputView {

    private final StudentController controller;
    private final StudentOutputView outputView;
    private final Scanner sc = new Scanner(System.in);

    public StudentInputView(StudentController controller, StudentOutputView outputView) {
        this.controller = controller;
        this.outputView = outputView;
    }

    public void studentMenu(UserDTO loginUser) {
        while (true) {
            outputView.printMenu();
            int choice = inputInt();

            switch (choice) {
                case 1:
                    //selectMyInfo(loginUser);
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

    /* 내정보 조회 메서드는 추후 제작
    private void selectMyInfo(UserDTO loginUser) {
        StudentDTO student = controller.findByUserId(loginUser.getUserId());
        outputView.printStudent(student);
    }*/


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