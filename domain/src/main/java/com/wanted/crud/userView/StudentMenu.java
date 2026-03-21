package com.wanted.crud.userView;

import java.util.Scanner;

import static com.wanted.crud.Application.*;

public class StudentMenu {
    private Scanner sc = new Scanner(System.in);
    private String role;
    private Long userNo;
    private Long studentId;

    public StudentMenu(Scanner sc) {
        this.sc = sc;
    }

    public StudentMenu(Scanner sc, String role, Long userNo, Long studentId) {
        this.sc = sc;
        this.role = role;
        this.userNo = userNo;
        this.studentId = studentId;
    }

    public void showMenu() {
        boolean isStudentLoggedIn = true;

        while (isStudentLoggedIn) {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("  🧑‍🎓 [ STUDENT DASHBOARD ]");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("  1. 📚 강좌 조회 및 신청");
            System.out.println("  2. 👤 마이페이지");
            System.out.println("  3. ✍️ 리뷰 작성");
            System.out.println("  4. ⚠️ 회원 탈퇴");
            System.out.println("  0. 🔙 로그아웃");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.print("  ▶ 메뉴를 선택해주세요: ");

            int menu = getMenuInput();

            switch (menu) {
                case 1: courseInquiryMenu(); break;
                case 2: myPageScreen(); break;
                case 3: reviewScreen(); break;
                case 4:
                    userInputView.deleteUser();
                    isStudentLoggedIn = false;
                    break;
                case 0:
                    System.out.println("\n  👋 [로그아웃] 안전하게 로그아웃 되었습니다. 다음에 또 봐요!");
                    isStudentLoggedIn = false;
                    break;
                default:
                    System.out.println("  ❌ [오류] 잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }

    private void courseInquiryMenu() {
        boolean isCourseMenuOpen = true;
        while (isCourseMenuOpen) {
            System.out.println("\n  📂 [ 1. 강좌 조회 세부 메뉴 ]");
            System.out.println("  --------------------------------------------------");
            System.out.println("  • 1. ✨ 새로운 강좌 신청하기");
            System.out.println("  • 2. 📖 수강 중인 강좌 보기");
            System.out.println("  • 0. 🔙 뒤로가기");
            System.out.println("  --------------------------------------------------");
            System.out.print("  ▶ 메뉴를 선택해주세요: ");

            int menu = getMenuInput();

            switch (menu) {
                case 1: courseApplyMenu(); break;
                case 2: takingCoursesScreen(); break;
                case 0: isCourseMenuOpen = false; break;
                default: System.out.println("  ❌ [오류] 잘못된 입력입니다."); break;
            }
        }
    }

    private void courseApplyMenu() {
        String paymentMethod = null;
        boolean status;
        courseInputView.viewAllCourses();
        System.out.println("어느 강좌를 신청하시겠습니까?");
        System.out.print("신청할 강좌의 아이디를 입력해주세요.");
        Long courseId = (long)getMenuInput();
        System.out.println("\n  💳 [결제진행]");
        System.out.println("결제 수단을 입력해주세요.");
        System.out.println("1. 신용카드 2.계좌이체 3. 핸드폰결제");
        int methodId = getMenuInput();
        long paymentAmount;
        switch (methodId) {
            case 1:
                paymentMethod = "creditCard";
                break;
            case 2:
                paymentMethod = "accountTransfer";
                break;
            case 3:
                paymentMethod = "phonePayment";
                break;
        }
        paymentAmount = courseInputView.getPrice(courseId); //강좌가격
        if(userInputView.getAmount(userNo) > paymentAmount) {
            status = true;
            System.out.println("결제할 수 있음!");

        } else {
            status = false;
            System.out.println("금액부족! 결제할 수 없음.");
            return;
        }
        //수강생의 수강내역에 저장
        enrollmentInputView.createEnrollment(userInputView.studentFindId(userNo), courseId);
        //결제내역에 저장. 오류나면 진짜 위험
        paymentInputView.createPayment(paymentAmount, paymentMethod, status, userInputView.studentFindId(userNo), courseId);
    }

    private void takingCoursesScreen() {
        System.out.println("\n  📖 [ 나의 수강 목록 ]");
        System.out.println("  📡  현재 수강 중인 강좌 리스트를 불러오는 중입니다...");
    }

    private void myPageScreen() {
        System.out.println("\n  👤 [ 수강생 마이페이지 ]");
        userInputView.studentMyPage(userNo);
    }

    private void reviewScreen() {
        System.out.println("\n  ✍️ [ 강의 리뷰 작성 ]");
        System.out.println("  📝  수강 완료된 강좌를 조회하여 따끈따끈한 후기를 남겨주세요!");
    }

    // 공통 입력 예외 처리 메서드
    private int getMenuInput() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}