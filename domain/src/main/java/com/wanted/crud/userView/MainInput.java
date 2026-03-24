package com.wanted.crud.userView;

import com.wanted.crud.Application;
import com.wanted.crud.course.view.CourseInputView;
import com.wanted.crud.enrollment.view.EnrollmentInputView;
import com.wanted.crud.payment.view.PaymentInputView;
import com.wanted.crud.settlement.view.SettlementInputView;
import com.wanted.crud.user.view.AdminInputView;
import com.wanted.crud.user.view.UserInputView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static com.wanted.crud.Application.*;

public class MainInput {
    // ANSI 색상 코드 정의
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Bold
    public static final String ANSI_RED_BOLD = "\u001B[1;31m";
    public static final String ANSI_GREEN_BOLD = "\u001B[1;32m";
    public static final String ANSI_YELLOW_BOLD = "\u001B[1;33m";
    public static final String ANSI_BLUE_BOLD = "\u001B[1;34m";
    public static final String ANSI_PURPLE_BOLD = "\u001B[1;35m";
    public static final String ANSI_CYAN_BOLD = "\u001B[1;36m";
    public static final String ANSI_WHITE_BOLD = "\u001B[1;37m";

    private static final Logger log = LoggerFactory.getLogger(MainInput.class);
    private Scanner sc = new Scanner(System.in);
    public String role;
    public Long userNo;
    public Long instructorId;
    public Long studentId;
    private CourseInputView courseInputView;
    private EnrollmentInputView enrollmentInputView;
    private SettlementInputView settlementInputView;
    private UserInputView userInputView;
    private PaymentInputView paymentInputView;
    private AdminInputView adminInputView;

    public MainInput(CourseInputView courseInputView, EnrollmentInputView enrollmentInputView, SettlementInputView settlementInputView, UserInputView userInputView, PaymentInputView paymentInputView, AdminInputView adminInputView) {
        this.courseInputView = courseInputView;
        this.enrollmentInputView = enrollmentInputView;
        this.settlementInputView = settlementInputView;
        this.userInputView = userInputView;
        this.paymentInputView = paymentInputView;
        this.adminInputView = adminInputView;
    }

    public void startApp() {
        while (true) {
            System.out.println("\n" + ANSI_BLUE + "┌────────────────────────────────────────────────────┐" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "│                       " + ANSI_GREEN + "/\\_/\\" + ANSI_BLUE + "                        │" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "│                      " + ANSI_GREEN + "( o.o )" + ANSI_BLUE + "                       │" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "│        " + ANSI_WHITE_BOLD + "[ WELCOME TO WANTED SKILLS ONLINE ]" + ANSI_BLUE + "         │" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "└────────────────────────────────────────────────────┘" + ANSI_RESET);
            System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 로그인");
            System.out.println("  " + ANSI_YELLOW + "2." + ANSI_RESET + " 회원가입");
            System.out.println("  " + ANSI_YELLOW + "3." + ANSI_RESET + " ID/Password 찾기");
            System.out.println("  " + ANSI_YELLOW + "4." + ANSI_RESET + " 프로그램 종료");
            System.out.println(ANSI_BLUE + "──────────────────────────────────────────────────────" + ANSI_RESET);
            System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 메뉴 선택: ");

            int startMenu = -1;
            try {
                startMenu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ " + ANSI_RED + "[입력오류] 숫자로 된 메뉴 번호를 입력해주세요.\n" + ANSI_RESET);
                pause();
                continue;
            }

            switch (startMenu) {
                case 1: loginScreen(); break;
                case 2: registerScreen(); pause(); break;
                case 3: findIdScreen(); pause(); break;
                case 4:
                    System.out.println("\n  " + ANSI_YELLOW + "이용해주셔서 감사합니다. 프로그램을 종료합니다." + ANSI_RESET);
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ " + ANSI_RED + "[오류] 잘못된 선택입니다. 다시 입력해주세요.\n" + ANSI_RESET);
                    pause();
                    break;
            }
        }
    }

    private void loginScreen() {
        System.out.println("\n" + ANSI_BLUE + "┌────────────────────────────────────────────────────┐" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "│                " + ANSI_WHITE_BOLD + "[ 로그인 시스템 접속 ]" + ANSI_BLUE + "                 │" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "└────────────────────────────────────────────────────┘" + ANSI_RESET);
        System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 아이디 입력: ");
        String id = sc.nextLine().trim();
        System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 비밀번호 입력: ");
        String password = sc.nextLine().trim();

        userNo = userInputView.loginGetNo(id, password);
        if (userNo == null) {
            System.out.println("❌ " + ANSI_RED + "[경고] 로그인 정보를 다시 확인해주세요." + ANSI_RESET);
            pause();
            return;
        }

        role = userInputView.loginSession(id, password);

        // 역할별로 분기해서 조회
        if ("INSTRUCTOR".equals(role)) {
            instructorId = userInputView.instructorId(userNo);
        } else if ("STUDENT".equals(role)) {
            studentId = userInputView.studentFindId(userNo);
        }

        System.out.println("\n  " + ANSI_GREEN + "[인증성공] " + id + "님, 환영합니다!" + ANSI_RESET);
        System.out.println("  " + ANSI_CYAN + "[권한확인] " + role + ANSI_RESET);
        System.out.println(ANSI_BLUE + "──────────────────────────────────────────────────────" + ANSI_RESET);

        switch (role) {
            case "STUDENT":
                StudentMenu();
                break;
            case "INSTRUCTOR":
                instrucotrMenu();
                break;
            case "ADMIN":
                adminMenu();
                break;
            default:
                System.out.println("❌ " + ANSI_RED + "[오류] 존재하지 않는 역할이거나 권한이 만료되었습니다.\n" + ANSI_RESET);
                pause();
                break;
        }
    }

    private void registerScreen() {
        System.out.println("\n  " + ANSI_CYAN + "[ 회원가입 프로세스 시작 ]" + ANSI_RESET);
        userInputView.displayRegister();
    }

    private void findIdScreen() {
        System.out.println("\n  " + ANSI_CYAN + "[ 계정 정보 찾기 모드 ]" + ANSI_RESET);
        userInputView.findIdPassword();
    }

    // 강사 메뉴
    public void instrucotrMenu() {
        boolean isInstructorLoggedIn = true;

        while (isInstructorLoggedIn) {
            System.out.println("\n" + ANSI_PURPLE + "┌────────────────────────────────────────────────────┐" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "│                       " + ANSI_YELLOW + ",___," + ANSI_PURPLE + "                        │" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "│                       " + ANSI_YELLOW + "[O.o]" + ANSI_PURPLE + "                        │" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "│              " + ANSI_WHITE_BOLD + "[ INSTRUCTOR DASHBOARD ]" + ANSI_PURPLE + "              │" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "└────────────────────────────────────────────────────┘" + ANSI_RESET);
            System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 내 강좌 조회");
            System.out.println("  " + ANSI_YELLOW + "2." + ANSI_RESET + " 강좌 등록 및 관리");
            System.out.println("  " + ANSI_YELLOW + "3." + ANSI_RESET + " 마이페이지");
            System.out.println("  " + ANSI_YELLOW + "4." + ANSI_RESET + " 수강생 현황");
            System.out.println("  " + ANSI_YELLOW + "5." + ANSI_RESET + " 정산 확인");
            System.out.println("  " + ANSI_YELLOW + "6." + ANSI_RESET + " 회원 탈퇴");
            System.out.println("  " + ANSI_YELLOW + "7." + ANSI_RESET + " 전체 강좌 보기");
            System.out.println("  " + ANSI_YELLOW + "0." + ANSI_RESET + " 로그아웃");
            System.out.println(ANSI_PURPLE + "──────────────────────────────────────────────────────" + ANSI_RESET);
            System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 메뉴를 선택해주세요: ");

            int menu = getMenuInput();

            switch (menu) {
                case 1: viewMyCourse(); pause(); break;
                case 2: courseManagementMenu(); break;
                case 3: InstructorMyPageScreen(); pause(); break;
                case 4: studentStatusScreen(); pause(); break;
                case 5: settlementCheckScreen(); pause(); break;
                case 6:
                    userInputView.deleteUser();
                    isInstructorLoggedIn = false;
                    pause();
                    break;
                case 7: viewAllCourses(); pause(); break;
                case 0:
                    System.out.println("\n  " + ANSI_YELLOW + "[로그아웃] 강사 세션을 종료합니다." + ANSI_RESET);
                    isInstructorLoggedIn = false;
                    break;
                default:
                    System.out.println("❌ " + ANSI_RED + "[오류] 잘못된 입력입니다. 다시 선택해주세요." + ANSI_RESET);
                    pause();
                    break;
            }
        }
    }

    private void viewMyCourse() {
        System.out.println("\n  " + ANSI_CYAN + "[ 내 강좌 목록 조회 중... ]" + ANSI_RESET);
        if (courseInputView != null) {
            courseInputView.viewMyCourse(instructorId);
        } else {
            System.out.println("❌ " + ANSI_RED + "[시스템 오류] 강좌 뷰가 초기화되지 않았습니다." + ANSI_RESET);
        }
    }

    private void viewAllCourses() {
        System.out.println("\n  " + ANSI_CYAN + "[ 플랫폼 전체 강좌 목록 조회 ]" + ANSI_RESET);
        if (courseInputView != null) {
            courseInputView.viewAllCourses();
        } else {
            System.out.println("❌ " + ANSI_RED + "[시스템 오류] 강좌 뷰가 초기화되지 않았습니다." + ANSI_RESET);
        }
    }

    private void courseManagementMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n  " + ANSI_CYAN + "[ 강좌 등록 및 관리 세부 메뉴 ]" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 새 강좌 등록하기");
            System.out.println("  " + ANSI_YELLOW + "2." + ANSI_RESET + " 기존 강좌 삭제하기");
            System.out.println("  " + ANSI_YELLOW + "3." + ANSI_RESET + " 강좌/강의 수정하기");
            System.out.println("  " + ANSI_YELLOW + "0." + ANSI_RESET + " 뒤로가기");
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 메뉴를 선택해주세요: ");

            int menu = getMenuInput();

            switch (menu) {
                case 1:
                    System.out.println("\n  " + ANSI_CYAN + "[ STEP 1 : 강좌 정보 입력 ]" + ANSI_RESET);
                    if (courseInputView != null) {
                        courseInputView.createCourse(instructorId);
                    } else {
                        System.out.println("❌ " + ANSI_RED + "[시스템 오류] 뷰가 초기화되지 않았습니다." + ANSI_RESET);
                        pause();
                        break;
                    }

                    System.out.println("\n  " + ANSI_CYAN + "[ STEP 2 : 강의(Section) 등록 제안 ]" + ANSI_RESET);
                    System.out.println("  강좌에 포함될 세부 강의를 지금 등록하시겠습니까?");
                    System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 예 (강의 등록 진행) | " + ANSI_YELLOW + "2." + ANSI_RESET + " 아니오 (나중에 등록)");
                    System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 선택: ");

                    int subMenu = getMenuInput();
                    if (subMenu == 1) {
                        courseInputView.createSection(instructorId);
                    } else {
                        System.out.println("  " + ANSI_YELLOW + "[안내] 강좌 기본 정보만 저장하고 메뉴로 돌아갑니다." + ANSI_RESET);
                    }
                    pause();
                    break;

                case 2:
                    System.out.println("\n  " + ANSI_CYAN + "[ 강좌 삭제 프로세스 ]" + ANSI_RESET);
                    viewMyCourse();
                    System.out.print("\n  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 삭제할 강좌 번호를 입력하세요 (취소: 0): ");
                    long courseIdToDelete = getLongInput();

                    if (courseIdToDelete == 0) {
                        System.out.println("  " + ANSI_YELLOW + "[안내] 강좌 삭제를 취소했습니다." + ANSI_RESET);
                    } else if (courseInputView != null) {
                        courseInputView.deleteCourse(courseIdToDelete, instructorId);
                    }
                    pause();
                    break;

                case 3: editCourseMenu(); break;
                case 0: isMenuOpen = false; break;
                default:
                    System.out.println("❌ " + ANSI_RED + "[오류] 잘못된 입력입니다." + ANSI_RESET);
                    pause();
                    break;
            }
        }
    }

    private void editCourseMenu() {
        boolean isEditMenuOpen = true;
        while (isEditMenuOpen) {
            System.out.println("\n  " + ANSI_CYAN + "[ 강좌 수정 상세 메뉴 ]" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 강좌 기본정보 수정");
            System.out.println("  " + ANSI_YELLOW + "2." + ANSI_RESET + " 강의 세부내용 수정");
            System.out.println("  " + ANSI_YELLOW + "0." + ANSI_RESET + " 뒤로가기");
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 메뉴를 선택해주세요: ");

            int menu = getMenuInput();
            switch (menu) {
                case 1:
                    System.out.println("\n  " + ANSI_CYAN + "[ 강좌 기본 정보 수정 모드 ]" + ANSI_RESET);
                    if (courseInputView != null) courseInputView.updateCourseView(instructorId);
                    pause();
                    break;
                case 2:
                    System.out.println("\n  " + ANSI_CYAN + "[ 강의 세부 내용 수정 모드 ]" + ANSI_RESET);
                    if (courseInputView != null) courseInputView.updateSectionView(instructorId);
                    pause();
                    break;
                case 0: isEditMenuOpen = false; break;
                default:
                    System.out.println("❌ " + ANSI_RED + "[오류] 잘못된 입력입니다." + ANSI_RESET);
                    pause();
                    break;
            }
        }
    }

    private void InstructorMyPageScreen() {
        System.out.println("\n  " + ANSI_CYAN + "[ 강사 마이페이지 ]" + ANSI_RESET);
        userInputView.instructorMyPage(userNo);
    }

    private void studentStatusScreen() {
        System.out.println("\n  " + ANSI_CYAN + "[ 수강생 현황 리포트 ]" + ANSI_RESET);
        System.out.println("  내 강좌를 수강 중인 전체 학생 목록을 불러오는 중...");
        if (courseInputView != null) {
            courseInputView.studentStatusScreen(instructorId);
        } else {
            System.out.println("❌ " + ANSI_RED + "[시스템 오류] 강좌 뷰가 초기화되지 않았습니다." + ANSI_RESET);
        }
    }

    private void settlementCheckScreen() {
        System.out.println("\n  " + ANSI_CYAN + "[ 내 잔액 확인 ]" + ANSI_RESET);
        System.out.println("  내 잔액을 조회합니다.");
        System.out.println(" 내 잔액: " + userInputView.getAmount(userNo));
    }

    private long getLongInput() {
        try {
            return Long.parseLong(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // 학생 메뉴
    public void StudentMenu() {
        boolean isStudentLoggedIn = true;

        while (isStudentLoggedIn) {
            System.out.println("\n" + ANSI_CYAN + "┌────────────────────────────────────────────────────┐" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "│                       " + ANSI_GREEN + "(\\(\\" + ANSI_CYAN + "                         │" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "│                       " + ANSI_GREEN + "( -.-)" + ANSI_CYAN + "                       │" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "│                " + ANSI_WHITE_BOLD + "[ STUDENT DASHBOARD ]" + ANSI_CYAN + "               │" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "└────────────────────────────────────────────────────┘" + ANSI_RESET);
            System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 강좌 조회 및 신청");
            System.out.println("  " + ANSI_YELLOW + "2." + ANSI_RESET + " 마이페이지");
            System.out.println("  " + ANSI_YELLOW + "3." + ANSI_RESET + " 리뷰 작성");
            System.out.println("  " + ANSI_YELLOW + "4." + ANSI_RESET + " 회원 탈퇴");
            System.out.println("  " + ANSI_YELLOW + "0." + ANSI_RESET + " 로그아웃");
            System.out.println(ANSI_CYAN + "──────────────────────────────────────────────────────" + ANSI_RESET);
            System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 메뉴를 선택해주세요: ");

            int menu = getMenuInput();

            switch (menu) {
                case 1: courseInquiryMenu(); break;
                case 2: StudentMyPageScreen(); pause(); break;
                case 3: reviewScreen(); pause(); break;
                case 4:
                    boolean isDeleted = userInputView.deleteUser();
                    if (isDeleted) {
                        isStudentLoggedIn = false;
                    }
                    pause();
                    break;
                case 0:
                    System.out.println("\n  " + ANSI_YELLOW + "[로그아웃] 안전하게 로그아웃 되었습니다." + ANSI_RESET);
                    isStudentLoggedIn = false;
                    break;
                default:
                    System.out.println("❌ " + ANSI_RED + "[오류] 잘못된 입력입니다. 다시 선택해주세요." + ANSI_RESET);
                    pause();
                    break;
            }
        }
    }

    private void StudentMyPageScreen() {
        System.out.println("\n  " + ANSI_CYAN + "[ 학생 마이페이지 ]" + ANSI_RESET);
        userInputView.studentMyPage(userNo);
    }

    private void courseInquiryMenu() {
        boolean isCourseMenuOpen = true;
        while (isCourseMenuOpen) {
            System.out.println("\n  " + ANSI_CYAN + "[ 강좌 조회 세부 메뉴 ]" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 새로운 강좌 신청하기");
            System.out.println("  " + ANSI_YELLOW + "2." + ANSI_RESET + " 수강 중인 강좌 보기");
            System.out.println("  " + ANSI_YELLOW + "3." + ANSI_RESET + " 강좌 리뷰 보기");
            System.out.println("  " + ANSI_YELLOW + "0." + ANSI_RESET + " 뒤로가기");
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 메뉴를 선택해주세요: ");

            int menu = getMenuInput();

            switch (menu) {
                case 1: courseApplyMenu(); pause(); break;
                case 2: takingCoursesScreen(); pause(); break;
                case 3: courseReviewScreen(); pause(); break;
                case 0: isCourseMenuOpen = false; break;
                default:
                    System.out.println("❌ " + ANSI_RED + "[오류] 잘못된 입력입니다." + ANSI_RESET);
                    pause();
                    break;
            }
        }
    }

    private void courseReviewScreen() {
        courseInputView.courseReviewScreen();
    }

    // 수강 강좌. 수강 조회 -> 수강 결제 -> 성공시 등록
    private void courseApplyMenu() {
        String paymentMethod = null;
        boolean status;
        long studentCash;
        courseInputView.viewAllCourses();
        System.out.println("\n  " + ANSI_CYAN + "어느 강좌를 신청하시겠습니까?" + ANSI_RESET);
        System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 신청할 강좌의 강좌번호를 입력해주세요: ");
        Long courseId = (long)getMenuInput();

        if(!courseInputView.isCourseExists(courseId)) {
            System.out.println("❌ " + ANSI_RED + "[경고] 해당 강좌는 없거나 오픈되지 않았습니다." + ANSI_RESET);
            return;
        }
        if(enrollmentInputView.isStudyingCourse(studentId, courseId)) {
            System.out.println("❌ " + ANSI_RED + "[경고] 이미 수강신청한 강좌입니다." + ANSI_RESET);
            return;
        }
        studentCash = userInputView.getAmount(userNo);

        System.out.println("\n  " + ANSI_CYAN + "[결제진행]" + ANSI_RESET);
        System.out.println("  결제 수단을 입력해주세요.");
        System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 신용카드 | " + ANSI_YELLOW + "2." + ANSI_RESET + " 계좌이체 | " + ANSI_YELLOW + "3." + ANSI_RESET + " 핸드폰결제");
        System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 선택: ");
        int methodId = getMenuInput();
        long paymentAmount = courseInputView.getPrice(courseId);

        System.out.println("  [보유금액]: " + ANSI_GREEN + studentCash + ANSI_RESET + "  [강좌가격]: " + ANSI_RED + paymentAmount + ANSI_RESET);
        switch (methodId) {
            case 1: paymentMethod = "creditCard"; break;
            case 2: paymentMethod = "accountTransfer"; break;
            case 3: paymentMethod = "phonePayment"; break;
        }

        if(studentCash >= paymentAmount) {
            status = true;
            System.out.println("  " + ANSI_CYAN + "결제 진행 중..." + ANSI_RESET);
            try {
                userInputView.updateAmount(userNo, -paymentAmount);
            } catch(Exception e) {
                System.out.println("❌ " + ANSI_RED + "[오류] 결제 도중 문제가 발생했습니다: " + e + ANSI_RESET);
                return;
            }
            enrollmentInputView.createEnrollment(studentId, courseId);
            paymentInputView.createPayment(paymentAmount, paymentMethod, status, studentId, courseId);
            System.out.println("✅ " + ANSI_GREEN + "[완료] 결제가 정상적으로 처리되었습니다." + ANSI_RESET);
        } else {
            System.out.println("❌ " + ANSI_RED + "[경고] 금액 부족으로 결제할 수 없습니다." + ANSI_RESET);
        }
    }

    private void takingCoursesScreen() {
        System.out.println("\n  " + ANSI_CYAN + "[ 나의 수강 목록 ]" + ANSI_RESET);
        System.out.println("  현재 수강 중인 강좌 리스트를 불러오는 중입니다...");
        boolean hasCourses = enrollmentInputView.studentCoursePage(studentId);

        if (!hasCourses) {
            System.out.println("  " + ANSI_YELLOW + "[안내] 수강할 강좌가 없습니다. 이전 화면으로 돌아갑니다." + ANSI_RESET);
            return;
        }

        System.out.println("\n  강좌를 수강하시겠습니까?");
        System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 예 | " + ANSI_YELLOW + "2." + ANSI_RESET + " 아니오");
        System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 선택: ");
        int takeSection = getMenuInput();

        if (takeSection == 1) {
            System.out.print("\n  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 수강할 강좌의 강좌 번호를 입력해주세요: ");
            long courseId = (long) getMenuInput();

            boolean updateProgress = enrollmentInputView.updateEnrollmentProgress(studentId, courseId);

            if (updateProgress) {
                System.out.println("✅ " + ANSI_GREEN + "[성공] 강좌 수강이 완료되었습니다! (진척도 +20)" + ANSI_RESET);
            } else {
                System.out.println("❌ " + ANSI_RED + "[실패] 이미 수강 완료한 강좌거나, 잘못된 강좌 번호 입니다." + ANSI_RESET);
            }
        } else {
            System.out.println("  " + ANSI_YELLOW + "이전 화면으로 돌아갑니다." + ANSI_RESET);
        }
    }

    private void reviewScreen() {
        System.out.println("\n  " + ANSI_CYAN + "[ 강의 리뷰 작성 ]" + ANSI_RESET);
        System.out.println("  수강 완료된 강좌를 조회하여 후기를 남겨주세요!");
        courseInputView.reviewScreen(studentId);
    }

    // 어드민 메뉴
    public void adminMenu() {
        boolean isAdminLoggedIn = true;

        while (isAdminLoggedIn) {
            System.out.println("\n" + ANSI_RED + "┌────────────────────────────────────────────────────┐" + ANSI_RESET);
            System.out.println(ANSI_RED + "│                       " + ANSI_YELLOW + "ʕ·͡ᴥ·ʔ" + ANSI_RED + "                       │" + ANSI_RESET);
            System.out.println(ANSI_RED + "│        " + ANSI_WHITE_BOLD + "[ SYSTEM ADMINISTRATOR CONTROL PANEL ]" + ANSI_RED + "      │" + ANSI_RESET);
            System.out.println(ANSI_RED + "└────────────────────────────────────────────────────┘" + ANSI_RESET);
            System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 수강생 관리");
            System.out.println("  " + ANSI_YELLOW + "2." + ANSI_RESET + " 강사 관리");
            System.out.println("  " + ANSI_YELLOW + "3." + ANSI_RESET + " 강좌 관리");
            System.out.println("  " + ANSI_YELLOW + "4." + ANSI_RESET + " 대시보드");
            System.out.println("  " + ANSI_YELLOW + "5." + ANSI_RESET + " 정산하기");
            System.out.println("  " + ANSI_YELLOW + "0." + ANSI_RESET + " 로그아웃");
            System.out.println(ANSI_RED + "──────────────────────────────────────────────────────" + ANSI_RESET);
            System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 메뉴를 선택해주세요: ");

            int menu = -1;
            try {
                menu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ " + ANSI_RED + "[오류] 숫자로 된 메뉴 번호를 입력해주세요." + ANSI_RESET);
                pause();
                continue;
            }

            switch (menu) {
                case 1: adminManageStudentsMenu(); break;
                case 2: adminManageInstructorsMenu(); break;
                case 3: adminManageCoursesMenu(); break;
                case 4: adminDashboardMenu(); break;
                case 5: adminSettlementScreen(); break;
                case 0:
                    System.out.println("\n  " + ANSI_YELLOW + "[로그아웃] 안전하게 시스템을 종료합니다." + ANSI_RESET);
                    isAdminLoggedIn = false;
                    break;
                default:
                    System.out.println("❌ " + ANSI_RED + "[오류] 잘못된 메뉴 선택입니다. 다시 입력해주세요." + ANSI_RESET);
                    pause();
                    break;
            }
        }
    }

    private void adminManageStudentsMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n  " + ANSI_CYAN + "[ 수강생 관리 세부 메뉴 ]" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 강좌별 수강생 조회");
            System.out.println("  " + ANSI_YELLOW + "2." + ANSI_RESET + " 수강생 정보 수정");
            System.out.println("  " + ANSI_YELLOW + "3." + ANSI_RESET + " 수강생 정보 삭제");
            System.out.println("  " + ANSI_YELLOW + "0." + ANSI_RESET + " 뒤로가기");
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 메뉴를 선택해주세요: ");

            int menu = getMenuInput();
            if (menu == 1) {
                userInputView.viewStudentBycourseId();
                pause();
            }
            else if (menu == 2) {
                System.out.println("\n  " + ANSI_CYAN + "[알림] 수강생 수정 로직 준비 중..." + ANSI_RESET);
                userInputView.updateStudent();
                pause();
            }
            else if (menu == 3) {
                System.out.println("\n  " + ANSI_CYAN + "[알림] 수강생 삭제 로직 준비 중..." + ANSI_RESET);
                userInputView.deleteStudent();
                pause();
            }
            else if (menu == 0) {
                isMenuOpen = false;
            }
            else {
                System.out.println("❌ " + ANSI_RED + "[오류] 잘못된 선택입니다." + ANSI_RESET);
                pause();
            }
        }
    }

    private void adminManageInstructorsMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n  " + ANSI_CYAN + "[ 강사 관리 세부 메뉴 ]" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 이름으로 강사 조회");
            System.out.println("  " + ANSI_YELLOW + "2." + ANSI_RESET + " 강사 정보 수정");
            System.out.println("  " + ANSI_YELLOW + "3." + ANSI_RESET + " 강사 정보 삭제");
            System.out.println("  " + ANSI_YELLOW + "0." + ANSI_RESET + " 뒤로가기");
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 메뉴를 선택해주세요: ");

            int menu = getMenuInput();
            if (menu == 1) {
                adminInputView.searchInstructor();
                pause();
            }
            else if (menu == 2) {
                System.out.println("\n  " + ANSI_CYAN + "[알림] 강사 정보 수정 로직 준비 중..." + ANSI_RESET);
                adminInputView.updateInstructor();
                pause();
            }
            else if (menu == 3) {
                System.out.println("\n  " + ANSI_CYAN + "[알림] 강사 정보 삭제 로직 준비 중..." + ANSI_RESET);
                adminInputView.deleteInstructor();
                pause();
            }
            else if (menu == 0) {
                isMenuOpen = false;
            }
            else {
                System.out.println("❌ " + ANSI_RED + "[오류] 잘못된 선택입니다." + ANSI_RESET);
                pause();
            }
        }
    }

    private void adminManageCoursesMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n  " + ANSI_CYAN + "[ 강좌 관리 세부 메뉴 ]" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 강사 이름으로 강좌 조회");
            System.out.println("  " + ANSI_YELLOW + "2." + ANSI_RESET + " 정보 수정 및 삭제");
            System.out.println("  " + ANSI_YELLOW + "0." + ANSI_RESET + " 뒤로가기");
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 메뉴를 선택해주세요: ");

            int menu = getMenuInput();
            if (menu == 1) {
                if (courseInputView != null) courseInputView.viewInstructorCourses();
                else System.out.println("❌ " + ANSI_RED + "[시스템 오류] 강좌 뷰가 초기화되지 않았습니다." + ANSI_RESET);
                pause();
            } else if (menu == 2) {
                if (courseInputView != null) courseInputView.adminManageCourseDetail();
                else System.out.println("❌ " + ANSI_RED + "[시스템 오류] 강좌 뷰가 초기화되지 않았습니다." + ANSI_RESET);
                pause();
            } else if (menu == 0) {
                isMenuOpen = false;
            } else {
                System.out.println("❌ " + ANSI_RED + "[오류] 잘못된 선택입니다." + ANSI_RESET);
                pause();
            }
        }
    }

    private void adminDashboardMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n  " + ANSI_CYAN + "[ 대시보드 세부 메뉴 ]" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 강좌별 총 결제금액");
            System.out.println("  " + ANSI_YELLOW + "2." + ANSI_RESET + " 관리자 전체 총수익");
            System.out.println("  " + ANSI_YELLOW + "0." + ANSI_RESET + " 뒤로가기");
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 메뉴를 선택해주세요: ");

            int menu = getMenuInput();
            if (menu == 1) {
                if (paymentInputView != null) paymentInputView.viewRevenueByCourse();
                else System.out.println("❌ " + ANSI_RED + "[시스템 오류] 결제 관련 화면을 불러올 수 없습니다." + ANSI_RESET);
                pause();
            } else if (menu == 2) {
                System.out.println("\n  " + ANSI_CYAN + "[통계] 플랫폼 전체 수익을 합산합니다..." + ANSI_RESET);
                System.out.println("  관리자의 총 수익: " + ANSI_GREEN + userInputView.getAmount(1L) + ANSI_RESET);
                pause();
            } else if (menu == 0) {
                isMenuOpen = false;
            } else {
                System.out.println("❌ " + ANSI_RED + "[오류] 잘못된 선택입니다." + ANSI_RESET);
                pause();
            }
        }
    }

    private void adminSettlementScreen() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n  " + ANSI_CYAN + "[ 정산 관리 시스템 ]" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.println("  " + ANSI_YELLOW + "1." + ANSI_RESET + " 전체 정산 내역 보기");
            System.out.println("  " + ANSI_YELLOW + "2." + ANSI_RESET + " 미정산 결제내역 조회 및 생성");
            System.out.println("  " + ANSI_YELLOW + "3." + ANSI_RESET + " 정산내역 처리하기");
            System.out.println("  " + ANSI_YELLOW + "0." + ANSI_RESET + " 뒤로가기");
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.println("  [처리중] 강사료 및 수수료 정산 로직 가동 중...");
            System.out.println("  [안내] 정산 완료 시 상태가 업데이트 됩니다.");
            System.out.println(ANSI_CYAN + "  ────────────────────────────────────────" + ANSI_RESET);
            System.out.print("  " + ANSI_GREEN_BOLD + "▶" + ANSI_RESET + " 메뉴를 선택해주세요: ");

            int menu = getMenuInput();

            if (menu == 1) {
                System.out.println("  전체 정산 내역 보기 로직");
                settlementInputView.viewAllSettlement();
                pause();
            } else if(menu == 2){
                System.out.println("  미정산된 결제내역 조회 로직");
                java.sql.Timestamp now = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
                settlementInputView.paymentToSettlement(now);
                pause();
            } else if (menu == 3) {
                System.out.println("  정산내역 처리하기 로직");
                settlementInputView.processFullSettlement();
                pause();
            } else if (menu == 0) {
                isMenuOpen = false;
            } else {
                System.out.println("❌ " + ANSI_RED + "[오류] 잘못된 선택입니다." + ANSI_RESET);
                pause();
            }
        }
    }

    private int getMenuInput() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // 화면 멈춤(Pause) 기능 추가
    private void pause() {
        System.out.print("\n  " + ANSI_YELLOW_BOLD + "▶ 내용을 모두 확인하셨으면 [Enter] 키를 눌러주세요..." + ANSI_RESET);
        sc.nextLine(); // 엔터 입력 대기
    }
}