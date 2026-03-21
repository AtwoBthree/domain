package com.wanted.crud.userView;

import com.wanted.crud.Application;
import com.wanted.crud.user.model.dto.UserDTO;
import com.wanted.crud.user.view.AdminInputView;

import java.util.Scanner;

import static com.wanted.crud.Application.adminInputView;
import static com.wanted.crud.Application.userInputView;

public class AdminMenu {
    private Scanner sc = new Scanner(System.in);
    private String role;
    private Long userNo;
    private Long instructorId;

    public AdminMenu(Scanner sc, String role, Long userNo, Long instructorId) {
        this.sc = sc;
        this.role = role;
        this.userNo = userNo;
        this.instructorId = instructorId;
    }

    public AdminMenu(Scanner sc) {
        this.sc = sc;
    }

    public void showMenu() {
        boolean isAdminLoggedIn = true;

        while (isAdminLoggedIn) {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("  👑 [ SYSTEM ADMINISTRATOR CONTROL PANEL ]");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("  1. 🧑‍🎓 수강생 관리");
            System.out.println("  2. 👨‍🏫 강사 관리");
            System.out.println("  3. 📚 강좌 관리");
            System.out.println("  4. 📊 대시보드");
            System.out.println("  5. 💰 정산하기");
            System.out.println("  0. 🔙 로그아웃");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.print("  ▶ 메뉴를 선택해주세요: ");

            int menu = -1;
            try {
                menu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  🚨 [오류] 숫자로 된 메뉴 번호를 입력해주세요.");
                continue;
            }

            switch (menu) {
                case 1: adminManageStudentsMenu(); break;
                case 2: adminManageInstructorsMenu(); break;
                case 3: adminManageCoursesMenu(); break;
                case 4: adminDashboardMenu(); break;
                case 5: adminSettlementScreen(); break;
                case 0:
                    System.out.println("\n  👋 [로그아웃] 안전하게 시스템을 종료합니다.");
                    isAdminLoggedIn = false;
                    break;
                default:
                    System.out.println("  ❌ [오류] 잘못된 메뉴 선택입니다. 다시 입력해주세요.");
                    break;
            }
        }
    }

    private void adminManageStudentsMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n  📂 [ 1. 수강생 관리 세부 메뉴 ]");
            System.out.println("  --------------------------------------------------");
            System.out.println("  • 1. 🔍 강좌별 수강생 조회");
            System.out.println("  • 2. 🛠️ 수강생 정보 수정");
            System.out.println("  • 3. 🛠️ 수강생 정보 삭제");
            System.out.println("  • 0. 🔙 뒤로가기");
            System.out.println("  --------------------------------------------------");
            System.out.print("  ▶ 메뉴를 선택해주세요: ");

            int menu = getMenuInput();
            if (menu == 1) userInputView.viewStudentBycourseId();
            else if (menu == 2) {System.out.println("\n  🛠️ [알림] 수강생 수정 로직 준비 중...");

                userInputView.updateStudent();

            }
            else if (menu == 3) {System.out.println("\n  🛠️ [알림] 수강생 삭제 로직 준비 중...");}
            else if (menu == 0) isMenuOpen = false;
            else System.out.println("  ❌ [오류] 잘못된 선택입니다.");
        }
    }

    private void adminManageInstructorsMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n  📂 [ 2. 강사 관리 세부 메뉴 ]");
            System.out.println("  --------------------------------------------------");
            System.out.println("  • 1. 🔍 이름으로 강사 조회");
            System.out.println("  • 2. 🛠️ 정보 수정 및 삭제");
            System.out.println("  • 0. 🔙 뒤로가기");
            System.out.println("  --------------------------------------------------");
            System.out.print("  ▶ 메뉴를 선택해주세요: ");

            int menu = getMenuInput();
            if (menu == 1) adminInputView.searchInstructor();
            else if (menu == 2) System.out.println("\n  🛠️ [알림] 강사 정보 수정/삭제 로직 준비 중...");
            else if (menu == 0) isMenuOpen = false;
            else System.out.println("  ❌ [오류] 잘못된 선택입니다.");
        }
    }

    private void adminManageCoursesMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n  📂 [ 3. 강좌 관리 세부 메뉴 ]");
            System.out.println("  --------------------------------------------------");
            System.out.println("  • 1. 🔍 강사 이름으로 강좌 조회");
            System.out.println("  • 2. 🛠️ 정보 수정 및 삭제");
            System.out.println("  • 0. 🔙 뒤로가기");
            System.out.println("  --------------------------------------------------");
            System.out.print("  ▶ 메뉴를 선택해주세요: ");

            int menu = getMenuInput();
            if (menu == 1) {
                if (Application.courseInputView != null) Application.courseInputView.viewInstructorCourses();
                else System.out.println("  🚨 [시스템 오류] 강좌 뷰가 초기화되지 않았습니다.");
            } else if (menu == 2) {
                if (com.wanted.crud.Application.courseInputView != null) com.wanted.crud.Application.courseInputView.adminManageCourseDetail();
                else System.out.println("  🚨 [시스템 오류] 강좌 뷰가 초기화되지 않았습니다.");
            } else if (menu == 0) isMenuOpen = false;
            else System.out.println("  ❌ [오류] 잘못된 선택입니다.");
        }
    }

    private void adminDashboardMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n  📊 [ 4. 대시보드 세부 메뉴 ]");
            System.out.println("  --------------------------------------------------");
            System.out.println("  • 1. 💰 강좌별 총 결제금액");
            System.out.println("  • 2. 💸 강사별 누적 총수익");
            System.out.println("  • 3. 📈 플랫폼 전체 총수익");
            System.out.println("  • 0. 🔙 뒤로가기");
            System.out.println("  --------------------------------------------------");
            System.out.print("  ▶ 메뉴를 선택해주세요: ");

            int menu = getMenuInput();
            if (menu == 1) System.out.println("\n  📊 [분석] 강좌별 누적 총액 데이터를 산출합니다...");
            else if (menu == 2) System.out.println("\n  💸 [수익] 강사별 수익 데이터를 집계합니다...");
            else if (menu == 3) System.out.println("\n  📈 [통계] 플랫폼 전체 수익을 합산합니다...");
            else if (menu == 0) isMenuOpen = false;
            else System.out.println("  ❌ [오류] 잘못된 선택입니다.");
        }
    }

    private void adminSettlementScreen() {
        System.out.println("\n  💰 [ 5. 정산 관리 시스템 ]");
        System.out.println("  --------------------------------------------------");
        System.out.println("  🛠️  [처리중] 강사료 및 수수료 정산 로직 가동 중...");
        System.out.println("  ✅  정산 완료 시 상태 메일이 자동 발송됩니다.");
        System.out.println("  --------------------------------------------------");
    }

    private int getMenuInput() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}