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
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("  🌟 [ WELCOME TO WANTED SKILLS ONLINE ]");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("  1. 🔑 로그인");
            System.out.println("  2. 📝 회원가입");
            System.out.println("  3. 🔍 ID/Password 찾기");
            System.out.println("  4. ❌ 프로그램 종료");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.print("  ▶ 메뉴 선택: ");

            int startMenu = -1;
            try {
                startMenu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  🚨 [입력오류] 숫자로 된 메뉴 번호를 입력해주세요.\n");
                continue;
            }

            switch (startMenu) {
                case 1: loginScreen(); break;
                case 2: registerScreen(); break;
                case 3: findIdScreen(); break;
                case 4:
                    System.out.println("\n  👋 이용해주셔서 감사합니다. 프로그램을 종료합니다.");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("  ❌ [오류] 잘못된 선택입니다. 다시 입력해주세요.\n");
                    break;
            }
        }
    }

    private void loginScreen() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  🔑 [ 로그인 시스템 접속 ]");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.print("  ▶ 아이디 입력: ");
        String id = sc.nextLine().trim();
        System.out.print("  ▶ 비밀번호 입력: ");
        String password = sc.nextLine().trim();

        userNo = userInputView.loginGetNo(id, password);
        if (userNo == null) {
            System.out.println("  🚨 로그인 정보를 다시 확인해주세요.");
            return;
        }

        role = userInputView.loginSession(id, password);

// 🔥 역할별로 분기해서 조회
        if ("INSTRUCTOR".equals(role)) {
            instructorId = userInputView.instructorId(userNo);
        } else if ("STUDENT".equals(role)) {
            studentId = userInputView.studentFindId(userNo);
        }

        System.out.println("\n  ✨ [인증성공] " + id + "님, 환영합니다!");
        System.out.println("  📡 권한 확인: " + role);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

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
                System.out.println("  🚨 [오류] 존재하지 않는 역할이거나 권한이 만료되었습니다.\n");
                break;
        }
    }

    private void registerScreen() {
        System.out.println("\n  📝 [ 회원가입 프로세스 시작 ]");
        userInputView.displayRegister();
    }

    private void findIdScreen() {
        System.out.println("\n  🔍 [ 계정 정보 찾기 모드 ]");
        userInputView.findIdPassword();
    }

    //강사 메뉴
    public void instrucotrMenu() {
        boolean isInstructorLoggedIn = true;

        while (isInstructorLoggedIn) {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("  👨‍🏫 [ INSTRUCTOR DASHBOARD ]");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("  1. 🔍 내 강좌 조회");
            System.out.println("  2. 🛠️ 강좌 등록 및 관리");
            System.out.println("  3. 👤 마이페이지");
            System.out.println("  4. 🧑‍🎓 수강생 현황");
            System.out.println("  5. 💰 정산 확인");
            System.out.println("  6. ⚠️ 회원 탈퇴");
            System.out.println("  7. 🌐 전체 강좌 보기");
            System.out.println("  0. 🔙 로그아웃");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.print("  ▶ 메뉴를 선택해주세요: ");

            int menu = getMenuInput();

            switch (menu) {
                case 1: viewMyCourse(); break;
                case 2: courseManagementMenu(); break;
                case 3: myPageScreen(); break;
                case 4: studentStatusScreen(); break;
                case 5: settlementCheckScreen(); break;
                case 6:
                    userInputView.deleteUser();
                    isInstructorLoggedIn = false;
                    break;
                case 7: viewAllCourses(); break;
                case 0:
                    System.out.println("\n  👋 [로그아웃] 강사 세션을 종료합니다. 오늘도 수고하셨습니다!");
                    isInstructorLoggedIn = false;
                    break;
                default:
                    System.out.println("  ❌ [오류] 잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }

    private void viewMyCourse() {
        System.out.println("\n  🔎 [ 내 강좌 목록 조회 중... ]");
        if (courseInputView != null) {
            courseInputView.viewMyCourse(instructorId);
        } else {
            System.out.println("  🚨 [시스템 오류] 강좌 뷰가 초기화되지 않았습니다.");
        }
    }

    private void viewAllCourses() {
        System.out.println("\n  🌐 [ 플랫폼 전체 강좌 목록 조회 ]");
        if (courseInputView != null) {
            courseInputView.viewAllCourses();
        } else {
            System.out.println("  🚨 [시스템 오류] 강좌 뷰가 초기화되지 않았습니다.");
        }
    }

    private void courseManagementMenu() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n  📂 [ 2. 강좌 등록 및 관리 세부 메뉴 ]");
            System.out.println("  --------------------------------------------------");
            System.out.println("  • 1. ✨ 새 강좌 등록하기");
            System.out.println("  • 2. 🗑️ 기존 강좌 삭제하기");
            System.out.println("  • 3. 📝 강좌/강의 수정하기");
            System.out.println("  • 0. 🔙 뒤로가기");
            System.out.println("  --------------------------------------------------");
            System.out.print("  ▶ 메뉴를 선택해주세요: ");

            int menu = getMenuInput();

            switch (menu) {
                case 1:
                    System.out.println("\n  🆕 [ STEP 1 : 강좌 정보 입력 ]");
                    if (courseInputView != null) {
                        courseInputView.createCourse(instructorId);
                    } else {
                        System.out.println("  🚨 시스템 오류: 뷰가 초기화되지 않았습니다.");
                        break;
                    }

                    System.out.println("\n  🎬 [ STEP 2 : 강의(Section) 등록 제안 ]");
                    System.out.println("  강좌에 포함될 세부 강의를 지금 등록하시겠습니까?");
                    System.out.println("  1. 👍 예 (강의 등록 진행) | 2. ✋ 아니오 (나중에 등록)");
                    System.out.print("  ▶ 선택: ");

                    int subMenu = getMenuInput();
                    if (subMenu == 1) {
                        courseInputView.createSection(instructorId);
                    } else {
                        System.out.println("  ✅ 강좌 기본 정보만 저장하고 메뉴로 돌아갑니다.");
                    }
                    break;

                case 2:
                    System.out.println("\n  🗑️ [ 강좌 삭제 프로세스 ]");
                    viewMyCourse();
                    System.out.print("\n  ▶ 삭제할 강좌 번호를 입력하세요 (취소: 0): ");
                    long courseIdToDelete = getLongInput();

                    if (courseIdToDelete == 0) {
                        System.out.println("  🚫 강좌 삭제를 취소했습니다.");
                    } else if (courseInputView != null) {
                        courseInputView.deleteCourse(courseIdToDelete, instructorId);
                    }
                    break;

                case 3: editCourseMenu(); break;
                case 0: isMenuOpen = false; break;
                default: System.out.println("  ❌ 잘못된 입력입니다."); break;
            }
        }
    }

    private void editCourseMenu() {
        boolean isEditMenuOpen = true;
        while (isEditMenuOpen) {
            System.out.println("\n  📝 [ 1.3 강좌 수정 상세 메뉴 ]");
            System.out.println("  --------------------------------------------------");
            System.out.println("  • 1. 📘 강좌 기본정보 수정");
            System.out.println("  • 2. 🎬 강의 세부내용 수정");
            System.out.println("  • 0. 🔙 뒤로가기");
            System.out.println("  --------------------------------------------------");
            System.out.print("  ▶ 메뉴를 선택해주세요: ");

            int menu = getMenuInput();
            switch (menu) {
                case 1:
                    System.out.println("\n  📘 [ 강좌 기본 정보 수정 모드 ]");
                    if (courseInputView != null) courseInputView.updateCourseView(instructorId);
                    break;
                case 2:
                    System.out.println("\n  🎬 [ 강의 세부 내용 수정 모드 ]");
                    if (courseInputView != null) courseInputView.updateSectionView(instructorId);
                    break;
                case 0: isEditMenuOpen = false; break;
                default: System.out.println("  ❌ 잘못된 입력입니다."); break;
            }
        }
    }

    private void myPageScreen() {
        System.out.println("\n  👤 [ 강사 마이페이지 ]");
        userInputView.instructorMyPage(userNo);
    }

    private void studentStatusScreen() {
        System.out.println("\n  🧑‍🎓 [ 수강생 현황 리포트 ]");
        System.out.println("  📊  내 강좌를 수강 중인 전체 학생 목록을 불러오는 중...");
        if (courseInputView != null) {
            courseInputView.studentStatusScreen(instructorId);
        } else {
            System.out.println("  🚨 [시스템 오류] 강좌 뷰가 초기화되지 않았습니다.");
        }

    }

    private void settlementCheckScreen() {
        System.out.println("\n  💰 [ 수익 정산 확인 ]");
        System.out.println("  💸  이번 달 정산 예정 금액 및 이력을 조회합니다.");
    }




    private long getLongInput() {
        try {
            return Long.parseLong(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    //학생 메뉴
    public void StudentMenu() {
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
                    boolean isDeleted = userInputView.deleteUser();
                    if (isDeleted) {
                        isStudentLoggedIn = false; // ✅ 성공했을 때만 로그아웃
                    }
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
            System.out.println("  . 3. ⭐ 강좌 리뷰 보기");
            System.out.println("  • 0. 🔙 뒤로가기");
            System.out.println("  --------------------------------------------------");
            System.out.print("  ▶ 메뉴를 선택해주세요: ");

            int menu = getMenuInput();

            switch (menu) {
                case 1: courseApplyMenu(); break;
                case 2: takingCoursesScreen(); break;
                case 3: courseReviewScreen(); break;
                case 0: isCourseMenuOpen = false; break;
                default: System.out.println("  ❌ [오류] 잘못된 입력입니다."); break;
            }
        }
    }

    private void courseReviewScreen() {
        courseInputView.courseReviewScreen();
    }


    //수강 강좌. 수강 조회 -> 수강 결제 -> 성공시 등록
    private void courseApplyMenu() {
        String paymentMethod = null;
        boolean status;
        long studentCash;
        courseInputView.viewAllCourses();
        System.out.println("어느 강좌를 신청하시겠습니까?");
        System.out.print("신청할 강좌의 강좌번호를 입력해주세요.");
        Long courseId = (long)getMenuInput();
        //중복강좌 신청불가 로직
        //중복강좌 신청했으면 리턴
        //+없는 강좌도 처리해야함.
        if(!courseInputView.isCourseExists(courseId)) {
            System.out.println("해당 강좌는 없거나 오픈되지않았습니다.");
            return;
        }
        if(enrollmentInputView.isStudyingCourse(studentId, courseId)) {
            System.out.println("이미 수강신청한 강좌입니다.");
            return;
        }
        studentCash = userInputView.getAmount(userNo);//강좌가격


        System.out.println("\n  💳 [결제진행]");
        System.out.println("결제 수단을 입력해주세요.");
        System.out.println("1. 신용카드 2.계좌이체 3. 핸드폰결제");
        int methodId = getMenuInput();
        long paymentAmount;
        paymentAmount = courseInputView.getPrice(courseId);
        System.out.println("[보유금액]: " + studentCash + "[강좌가격]: " + paymentAmount);
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

        //수강생이 보유한 금액이 결제금액보다 많은지 확인.
        if(studentCash >= paymentAmount) {
            status = true;
            System.out.println("결제할 수 있음!");
            //수강생 보유금액에서 결제금액 차감
            //userInputView.updateAmount(userNo, 차감될 금액)
            try {userInputView.updateAmount(userNo, -paymentAmount);}
            catch(Exception e) {
                System.out.println("결제 도중 문제가 발생했습니다." + e);
                return;
            }
            //수강생의 수강내역에 저장
            enrollmentInputView.createEnrollment(studentId, courseId);
            //결제내역에 저장.
            paymentInputView.createPayment(paymentAmount, paymentMethod, status, studentId, courseId);
        } else {
            System.out.println("금액부족! 결제할 수 없음.");
        }

    }

    private void takingCoursesScreen() {

        System.out.println("\n  📖 [ 나의 수강 목록 ]");
        System.out.println("  📡  현재 수강 중인 강좌 리스트를 불러오는 중입니다...");
        enrollmentInputView.studentCoursePage(studentId);

        // 강좌 수강하기
        System.out.println("강좌를 수강하시겠습니까?");
        System.out.println("1. 예    2. 아니오");
        System.out.print(" ▶ 선택: ");
        int takeSection = getMenuInput();
        if (takeSection == 1) {
            System.out.print("\n수강할 강좌의 강좌 번호를 입력해주세요 : ");
            long courseId = (long) getMenuInput();

            boolean updateProgress = enrollmentInputView.updateEnrollmentProgress(studentId, courseId);

            if (updateProgress) {
                System.out.println("강좌 수강이 완료되었습니다! (진척도 +20)");
            } else {
                System.out.println("🚨 강좌 수강에 실패하였습니다.");
                System.out.println("🚨 이미 수강 완료한 강좌거나, 잘못된 강좌 번호 입니다.");
            }
        } else {
            System.out.println("이전 화면으로 돌아갑니다.");
        }
    }


    private void reviewScreen() {
        System.out.println("\n  ✍️ [ 강의 리뷰 작성 ]");
        System.out.println("  📝  수강 완료된 강좌를 조회하여 따끈따끈한 후기를 남겨주세요!");
        courseInputView.reviewScreen(studentId);
    }


    //어드민 메뉴
    public void adminMenu() {
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
            else if (menu == 3) {System.out.println("\n  🛠️ [알림] 수강생 삭제 로직 준비 중...");
                userInputView.deleteStudent();
            }
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
            System.out.println("  • 2. 🛠️ 강사 정보 수정");
            System.out.println("  • 3. 🛠️ 강사 정보 삭제");
            System.out.println("  • 0. 🔙 뒤로가기");
            System.out.println("  --------------------------------------------------");
            System.out.print("  ▶ 메뉴를 선택해주세요: ");

            int menu = getMenuInput();
            if (menu == 1) adminInputView.searchInstructor();
            else if (menu == 2) {System.out.println("\n  🛠️ [알림] 강사 정보 수정 로직 준비 중...");
                adminInputView.updateInstructor();
            }
            else if (menu == 3) {System.out.println("\n  🛠️ [알림] 강사 정보 삭제 로직 준비 중...");
                adminInputView.deleteInstructor();
            }
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
                if (courseInputView != null) courseInputView.viewInstructorCourses();
                else System.out.println("  🚨 [시스템 오류] 강좌 뷰가 초기화되지 않았습니다.");
            } else if (menu == 2) {
                if (courseInputView != null) courseInputView.adminManageCourseDetail();
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
            if (menu == 1) {
                if (paymentInputView != null) {
                    paymentInputView.viewRevenueByCourse();
                } else {
                    System.out.println("  🚨 [시스템 오류] 결제 관련 화면을 불러올 수 없습니다.");
                }
            }

            else if (menu == 2) {
                if (settlementInputView != null) {
                    settlementInputView.viewRevenueByInstructor();
                } else {
                    System.out.println("  🚨 [시스템 오류] 정산 관련 화면을 불러올 수 없습니다.");
                }
            }
            else if (menu == 3) System.out.println("\n  📈 [통계] 플랫폼 전체 수익을 합산합니다...");
            else if (menu == 0) isMenuOpen = false;
            else System.out.println("  ❌ [오류] 잘못된 선택입니다.");
        }
    }

    private void adminSettlementScreen() {
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            System.out.println("\n  💰 [ 5. 정산 관리 시스템 ]");
            System.out.println("  --------------------------------------------------");
            System.out.println(" 1. 전체 정산 내역 보기. 2. create 미정산된 결제내역 , select 불러오기 3. 정산내역 처리하기");
            //2. 정산내역 불러오면 status WAIT 인 상태로 불러와지고 3. WAIT 인 상태의 정산내역들이 관리자, 강사 돈이들어감.
            System.out.println("  🛠️  [처리중] 강사료 및 수수료 정산 로직 가동 중...");
            System.out.println("  ✅  정산 완료 시 상태가 업데이트 됩니다.");
            System.out.println("  --------------------------------------------------");

            int menu = getMenuInput();

            // 1. 전체 정산 내역 보기
            if (menu == 1) {
                System.out.println("전체 정산 내역 보기 로직");
                settlementInputView.viewAllSettlement();
            }

            else if(menu == 2){
                System.out.println("create 미정산된 결제내역 조회 로직");
                // Java 8 이상 (권장 방식)
                java.sql.Timestamp now = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
                settlementInputView.paymentToSettlement(now);
            } // 진도 파이팅!

            else if (menu == 3) {
                System.out.println("정산내역 처리하기 로직");
                settlementInputView.processFullSettlement();
            }

            else if (menu == 0) isMenuOpen = false;
            else System.out.println("  ❌ [오류] 잘못된 선택입니다.");
        }

    }


    private int getMenuInput() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }


}