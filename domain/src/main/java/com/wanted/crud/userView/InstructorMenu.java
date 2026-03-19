package com.wanted.crud.userView;

import java.util.Scanner;

public class InstructorMenu {
    private Scanner sc;

    public InstructorMenu(Scanner sc) {
        this.sc = sc;
    }

    public void showMenu() {
        boolean isInstructorLoggedIn = true;

        while (isInstructorLoggedIn) {
            System.out.println("\n========== 강사 화면 ==========");
            System.out.println("1. 강좌 조회하기 | 2. 강좌 등록 및 관리 | 3. 마이페이지 | 4. 수강생 현황 | 5. 정산 확인 | 6. 탈퇴하기 | 0. 로그아웃");
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
                    viewMyCourse(); //강사의 강의 조회해야함
                    break;
                case 2:
                    courseManagementMenu();
                    break;
                case 3:
                    myPageScreen(); // [Inst-05]
                    break;
                case 4:
                    studentStatusScreen(); // [Inst-06]
                    break;
                case 5:
                    settlementCheckScreen(); // [Inst-07]
                    break;
                case 6:
                    deleteAccountScreen(); // [Inst-08]
                    isInstructorLoggedIn = false;
                    break;
                case 0:
                    System.out.println("로그아웃 되었습니다.");
                    isInstructorLoggedIn = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }

    private void viewMyCourse() {
    }

    private void courseManagementMenu() {
        boolean isMenuOpen = true;

        while (isMenuOpen) {
            System.out.println("\n--- 1. 강좌 등록 및 관리 ---");
            System.out.println("1. 강좌 등록하기 | 2. 강좌 삭제하기 | 3. 강좌 수정하기 | 0. 뒤로가기");
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
                    System.out.println("\n[Inst-01] 새로운 강좌 등록 로직 연결 자리");
                    break;
                case 2:
                    System.out.println("\n--- 강좌 삭제 ---");
                    System.out.print("강좌번호 혹은 제목을 입력해주세요: ");
                    String input = sc.nextLine();
                    System.out.println("[Inst-02] 입력받은 강좌[" + input + "] 삭제 로직 연결 자리");
                    break;
                case 3:
                    editCourseMenu();
                    break;
                case 0:
                    isMenuOpen = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }

    private void editCourseMenu() {
        boolean isEditMenuOpen = true;

        while (isEditMenuOpen) {
            System.out.println("\n--- 1.3 강좌 수정하기 ---");
            System.out.println("1. 강좌 수정 | 2. 강의 수정 | 0. 뒤로가기");
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
                    System.out.println("\n[Inst-03] 강좌 기본 정보(제목, 가격 등) 수정 로직 연결 자리");
                    break;
                case 2:
                    System.out.println("\n[Inst-04] 강좌에 속한 세부 '강의' 내용 수정 로직 연결 자리");
                    break;
                case 0:
                    isEditMenuOpen = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }

    private void myPageScreen() {
        System.out.println("\n--- 2. 마이페이지 ---");
        System.out.println("[Inst-05] 강사 마이페이지 정보 출력 및 수정 로직 연결 자리");
    }

    private void studentStatusScreen() {
        System.out.println("\n--- 3. 수강생 현황 ---");
        System.out.println("[Inst-06] 내 강좌를 수강 중인 전체 학생 목록 출력 로직 연결 자리");
    }

    private void settlementCheckScreen() {
        System.out.println("\n--- 4. 정산 확인 ---");
        System.out.println("[Inst-07] 강사 본인의 정산된 수익 확인 로직 연결 자리");
    }

    private void deleteAccountScreen() {
        System.out.println("\n--- 5. 탈퇴하기 ---");
        System.out.println("[Inst-08] 강사 회원 탈퇴(상태 변경 등) 처리 로직 연결 자리");
    }
}