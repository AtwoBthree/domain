package com.wanted.crud.userView;

import java.util.Scanner;
import static com.wanted.crud.Application.userInputView;

public class StudentMenu {
    private Scanner sc;

    public StudentMenu(Scanner sc) {
        this.sc = sc;
    }

    public void showMenu() {
        boolean isStudentLoggedIn = true;

        while (isStudentLoggedIn) {
            System.out.println("\n========== 학생 화면 ==========");
            System.out.println("1. 강좌조회 | 2. 마이페이지 | 3. 리뷰작성 | 4. 회원탈퇴 | 0. 로그아웃");
            System.out.print("메뉴를 선택해주세요: ");

            int menu = -1;
            try {
                menu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[오류] 숫자로 된 메뉴 번호를 입력해주세요.");
                continue;
            }

            switch (menu) {
                case 1:
                    courseInquiryMenu();
                    break;
                case 2:
                    myPageScreen(); // [Student-03]
                    break;
                case 3:
                    reviewScreen(); // [Student-04]
                    break;
                case 4:
                     // [Student-05]
                    userInputView.deleteUser();
                    isStudentLoggedIn = false;
                    break;
                case 0:
                    System.out.println("로그아웃 되었습니다.");
                    isStudentLoggedIn = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }

    private void courseInquiryMenu() {
        boolean isCourseMenuOpen = true;
        while (isCourseMenuOpen) {
            System.out.println("\n--- 1. 강좌조회 ---");
            System.out.println("1. 강좌 신청 | 2. 수강중인 강좌 | 0. 뒤로가기");
            System.out.print("메뉴를 선택해주세요: ");

            int menu = -1;
            try {
                menu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[오류] 숫자로 된 메뉴 번호를 입력해주세요.");
                continue;
            }

            switch (menu) {
                case 1:
                    courseApplyMenu(); // [Student-01]
                    break;
                case 2:
                    takingCoursesScreen(); // [Student-02]
                    break;
                case 0:
                    isCourseMenuOpen = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }


    private void courseApplyMenu() {
        System.out.println("\n--- 1.1 강좌 신청 ---");
        System.out.println("해당 강좌를 신청하시겠습니까?");
        System.out.println("1. 네 | 2. 아니요");
        System.out.print("선택: ");

        int menu = -1;
        try {
            menu = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[오류] 잘못된 입력입니다. 취소 처리됩니다.");
            return;
        }

        if (menu == 1) System.out.println("[Student-01] 강좌 신청 및 결제 로직 연결 자리");
        else System.out.println("취소했습니다.");
    }

    private void takingCoursesScreen() {
        System.out.println("\n--- 1.2 수강중인 강좌 ---");
        System.out.println("[Student-02] 수강중인 강좌 목록 출력 로직 연결 자리");
    }

    private void myPageScreen() {
        System.out.println("\n--- 2. 마이페이지 ---");

        System.out.println("[Student-03] 마이페이지(학생 정보) 조회/수정 로직 연결 자리");
    }

    private void reviewScreen() {
        System.out.println("\n--- 3. 리뷰작성 ---");
        System.out.println("[Student-04] 수강여부 1인 강좌 조회 및 리뷰 작성 로직 연결 자리");
    }

    private void deleteAccountScreen() {
        System.out.println("\n--- 4. 회원탈퇴 ---");
        System.out.println("[Student-05] 학생 회원탈퇴(상태 변경 또는 삭제) 로직 연결 자리");
    }
}