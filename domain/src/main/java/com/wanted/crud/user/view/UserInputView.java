package com.wanted.crud.user.view;

import com.wanted.crud.user.controller.UserController;
import com.wanted.crud.user.model.dto.UserDTO;

import java.util.Scanner;

public class UserInputView {

    private final UserController controller;
    private final UserOutputView outputView;

    private final Scanner sc = new Scanner(System.in);

    public UserInputView(UserController controller,
                         UserOutputView outputView) {
        this.controller = controller;
        this.outputView = outputView;
    }


    public String loginSession(String id, String password) {
        UserDTO loginUser = controller.login(id, password);

        if (loginUser == null) {
            outputView.printError("아이디 또는 비밀번호가 틀렸습니다.");
        }

        String role = null;
        if (loginUser != null) {
            role = loginUser.getUserRole();
        }
        return role;
    }

    public Long loginGetNo(String id, String password) {
        UserDTO loginUser = controller.login(id, password);

        if (loginUser == null) {
            outputView.printError("user_no가 없습니다.");
        }

        Long user_no = null;
        if (loginUser != null) {
            user_no = loginUser.getUserNo();
        }
        return user_no;
    }


    public void displayRegister() {
        while (true) {
            System.out.println();
            System.out.println("=================[회원 가입]==============");
            System.out.print("사용할 아이디를 입력해주세요: ");
            String id = inputString();
            System.out.print("사용할 비밀번호 입력해주세요: ");
            String password = inputString();
            System.out.print("사용할 이름을 입력해주세요: ");
            String name = inputString();
            System.out.print("사용할 전화번호를 입력해주세요: ");
            String phone_number = inputString();
            System.out.print("역할을 입력해주세요: ");
            String role = inputString();

            Long result = controller.createUser(id, password, name, phone_number, role);

            if (result != null && result > 0) {
                outputView.printSuccess("회원가입 성공! 생성된 계정 ID : " + result);
            } else {
                outputView.printError("회원가입 실패");
            }
        }
    }


    public void studentMenu(UserDTO loginUser) {
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


    public void instructorMenu(UserDTO loginUser) {
        while (true) {
            outputView.printInstructorMenu();
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


    public void adminMenu() {
        while (true) {
            outputView.printAdminMenu();
            int choice = inputInt();

            switch (choice) {
                case 1:
                    outputView.printUsers(controller.selectAllUsers());
                    break;
                case 2:
                    outputView.printStudents(controller.selectAllStudents());
                    break;
                case 3:
                    outputView.printInstructors(controller.selectAllInstructors());
                    break;
                case 0:
                    return;
                default:
                    outputView.printError("잘못된 입력입니다.");
            }
        }
    }

    // ===== 입력 =====
    private String inputString() {
        return sc.nextLine();
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