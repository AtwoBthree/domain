package com.wanted.crud.userView;

import java.util.Scanner;

public class AdminMenu {
    private Scanner sc;

    public AdminMenu(Scanner sc) {
        this.sc = sc;
    }

    public void showMenu() {
        boolean isAdminLoggedIn = true;

        while (isAdminLoggedIn) {
            System.out.println("\n========== 관리자 화면 ==========");
            System.out.println("1. 수강생 관리 | 2. 강사 관리 | 3. 강좌 관리 | 4. 대시보드 | 5. 정산하기 | 0. 로그아웃");
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
                    adminManageStudentsMenu();
                    break;
                case 2:
                    adminManageInstructorsMenu();
                    break;
                case 3:
                    adminManageCoursesMenu();
                    break;
                case 4:
                    adminDashboardMenu();
                    break;
                case 5:
                    adminSettlementScreen(); // [Admin-10]
                    break;
                case 0:
                    System.out.println("로그아웃 되었습니다.");
                    isAdminLoggedIn = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }

    private void adminManageStudentsMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n--- 1. 수강생 관리 ---");
            System.out.println("1. 강좌별 수강생 조회 | 2. 수정/삭제 | 0. 뒤로가기");
            System.out.print("메뉴를 선택해주세요: ");

            int menu = -1;
            try {
                menu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[오류] 숫자로 된 메뉴 번호를 입력해주세요.");
                continue;
            }

            if (menu == 1) { } // 강좌별 수강생 조회
            else if (menu == 2) System.out.println("[Admin-02] 수강생 정보 수정/삭제 로직 연결 자리");
            else if (menu == 0) isMenuOpen = false;
            else System.out.println("잘못된 입력입니다.");
        }
    }

    private void adminManageInstructorsMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n--- 2. 강사 관리 ---");
            System.out.println("1. 이름으로 조회 | 2. 수정/삭제 | 0. 뒤로가기");
            System.out.print("메뉴를 선택해주세요: ");

            int menu = -1;
            try {
                menu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[오류] 숫자로 된 메뉴 번호를 입력해주세요.");
                continue;
            }

            if (menu == 1) System.out.println("[Admin-03] 강사 이름 검색 및 조회 로직 연결 자리");
            else if (menu == 2) System.out.println("[Admin-04] 강사 정보 수정/삭제 로직 연결 자리");
            else if (menu == 0) isMenuOpen = false;
            else System.out.println("잘못된 입력입니다.");
        }
    }

    private void adminManageCoursesMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n--- 3. 강좌 관리 ---");
            System.out.println("1. 강사 이름으로 조회 | 2. 수정/삭제 | 0. 뒤로가기");
            System.out.print("메뉴를 선택해주세요: ");

            int menu = -1;
            try {
                menu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[오류] 숫자로 된 메뉴 번호를 입력해주세요.");
                continue;
            }

            if (menu == 1) System.out.println("[Admin-05] 강사 이름으로 강좌 목록 조회 로직 연결 자리");
            else if (menu == 2) System.out.println("[Admin-06] 강좌 정보 수정/삭제 로직 연결 자리");
            else if (menu == 0) isMenuOpen = false;
            else System.out.println("잘못된 입력입니다.");
        }
    }

    private void adminDashboardMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n--- 4. 대시보드 ---");
            System.out.println("1. 강좌별 총금액 | 2. 강사별 총수익 | 3. 총수익 | 0. 뒤로가기");
            System.out.print("메뉴를 선택해주세요: ");

            int menu = -1;
            try {
                menu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[오류] 숫자로 된 메뉴 번호를 입력해주세요.");
                continue;
            }

            if (menu == 1) System.out.println("[Admin-07] 강좌별 누적 결제 총금액 계산/출력 로직 연결 자리");
            else if (menu == 2) System.out.println("[Admin-08] 강사별 누적 총수익 계산/출력 로직 연결 자리");
            else if (menu == 3) System.out.println("[Admin-09] 플랫폼 전체 총수익 계산/출력 로직 연결 자리");
            else if (menu == 0) isMenuOpen = false;
            else System.out.println("잘못된 입력입니다.");
        }
    }

    private void adminSettlementScreen() {
        System.out.println("\n--- 5. 정산하기 ---");
        System.out.println("[Admin-10] 강사료 및 수수료 정산(상태 변경 등) 처리 로직 연결 자리");
    }
}