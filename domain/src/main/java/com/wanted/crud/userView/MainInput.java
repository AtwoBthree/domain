package com.wanted.crud.userView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class MainInput {
    private static final Logger log = LoggerFactory.getLogger(MainInput.class);
    private Scanner sc = new Scanner(System.in);

    public void startApp() {
        while(true) {
            System.out.println("1. 로그인 2. 회원가입 3.ID/password 찾기 4. 프로그램 종료");
            System.out.print("메뉴 선택: ");

            int startMenu = -1;
            try {
                startMenu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[오류] 숫자로 된 메뉴 번호를 입력해주세요.\n");
                continue;
            }

            switch (startMenu) {
                case 1:
                    loginScreen();
                    break;
                case 2:
                    registerScreen(); // [Main-01]
                    break;
                case 3:
                    findIdScreen(); // [Main-02]
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 입력입니다.\n");
                    break;
            }
        }
    }

    private void findIdScreen() {
        System.out.println("\n--- ID/Password 찾기 ---");
        System.out.println("[Main-02] ID/PW 찾기 로직을 연결할 자리입니다.");
    }

    private void registerScreen() {
        System.out.println("\n--- 회원가입 ---");
        System.out.println("[Main-01] 회원가입 로직(Controller)을 연결할 자리입니다.");
    }

    private void loginScreen() {
        System.out.println("\n--- 로그인 화면 ---");
        System.out.println("아이디를 입력해주세요:");
        String id = sc.nextLine().trim();
        System.out.println("비밀번호를 입력해주세요:");
        String password = sc.nextLine().trim();

        System.out.println("역할을 입력해주세요 (STUDENT / INSTRUCTOR / ADMIN):");
        String role = sc.nextLine().trim().toUpperCase();

        // [Main-03] 실제 DB 조회 후 role을 판단하는 로그인 인증 로직이 들어갈 자리입니다.
        System.out.println("[Main-03] 로그인 인증 및 세션 처리 로직 구현 예정");

        switch (role) {
            case "STUDENT":
                StudentMenu studentMenu = new StudentMenu(sc);
                studentMenu.showMenu();
                break;
            case "INSTRUCTOR":
                InstructorMenu instructorMenu = new InstructorMenu(sc);
                instructorMenu.showMenu();
                break;
            case "ADMIN":
                AdminMenu adminMenu = new AdminMenu(sc);
                adminMenu.showMenu();
                break;
            default:
                System.out.println("존재하지 않는 역할이거나 잘못된 입력입니다.\n");
                break;
        }
    }
}