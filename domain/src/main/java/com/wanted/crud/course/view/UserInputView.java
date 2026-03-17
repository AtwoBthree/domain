package com.wanted.crud.course.view;

import com.wanted.crud.course.controller.UserController;
import com.wanted.crud.course.model.dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class UserInputView {
    private final UserController controller;
    private final UserOutputView outputView;
    private final Scanner sc = new Scanner(System.in);

    //사용자 역할
    String role;


    public UserInputView(UserController controller, UserOutputView outputView) {
        this.controller = controller;
        this.outputView = outputView;
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println();
            System.out.println("======================================");
            System.out.println("              로그인화면                ");
            System.out.println("======================================");
            System.out.print("아이디: ");
            String id = sc.nextLine();
            System.out.print("비밀번호: ");
            String password = sc.nextLine();

            //받아오는 함수 구현해야함
            role = "student";


            switch (role) {
                case "student":
                    //메소드 구현필요
                    break;
                case "instructor":
                    //메소드 구현필요
                    break;
                case "admin":
                    //메소드 구현필요
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
