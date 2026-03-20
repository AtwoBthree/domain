package com.wanted.crud.test.userView;
import com.wanted.crud.Application;


import static com.wanted.crud.Application.userInputView;
import static com.wanted.crud.userView.MainInput.findcourseid;
import static com.wanted.crud.userView.MainInput.instructorId;

import java.util.Scanner;

public class InstructorMenu {
    private Scanner sc = new Scanner(System.in);
    public String role;
    public Long userNo;


    public InstructorMenu(Scanner sc) {
        this.sc = sc;
    }

    public void showMenu() {
        boolean isInstructorLoggedIn = true;

        while (isInstructorLoggedIn) {
            System.out.println("\n========== 강사 화면 ==========");
            System.out.println("1. 강좌 조회하기 | 2. 강좌 등록 및 관리 | 3. 마이페이지 | 4. 수강생 현황 | 5. 정산 확인 | 6. 탈퇴하기 | 7. 강좌 전체 조회| 0. 로그아웃");
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
                    viewMyCourse(); //강사의 강의 조회해야함              // [완]
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
                    userInputView.deleteUser();
                    // [Inst-08]
                    isInstructorLoggedIn = false;
                    break;
                case 7:
                    viewAllCourses();
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

    /* hi. 추가한 코드_시작 */
    private void viewMyCourse() {

        if (Application.courseInputView != null) {
            Application.courseInputView.viewMyCourse(instructorId);
        } else {
            System.out.println("[시스템 오류] 강좌 뷰가 초기화되지 않았습니다.");
        }

    }

    private void viewAllCourses() {
        System.out.println("\n--- 전체 강좌 조회 로직 실행 ---");

        // ★ Application 보관해둔 뷰를 꺼내서 전체 조회 메서드를 실행합니다!
        if (Application.courseInputView != null) {
            Application.courseInputView.viewAllCourses(); // CourseInputView에 만든 메서드 이름
        } else {
            System.out.println("[시스템 오류] 강좌 뷰가 초기화되지 않았습니다.");
        }
    }

    private void courseManagementMenu() {
        boolean isMenuOpen = true;

        while (isMenuOpen) {
            System.out.println("\n--- 2. 강좌 등록 및 관리 ---");
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
                    System.out.println("\n=========================새로운 강좌 등록=========================");
                    if (Application.courseInputView != null) {
                        Application.courseInputView.createCourse(instructorId);

                    } else {
                        System.out.println("시스템 오류: 뷰가 초기화되지 않았습니다.");
                    }
                    // createSection
                    System.out.println("\n=========================새로운 강의 등록=========================");
                    System.out.println("\n강의를 등록하시겠습니까?");
                    System.out.println("\n1. 예 | 2. 아니오");
                    int subMenu = -1;
                    try {
                        subMenu = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("[오류] 숫자로 입력해주세요. 강의 등록을 건너뜁니다.");
                        break;
                    }

                    // 새로 입력받은 subMenu 값으로 중첩 switch 실행
                    switch (subMenu) {
                        case 1:
                            if (Application.courseInputView != null) {
                                Application.courseInputView.createSection(instructorId);
                            } else {
                                System.out.println("시스템 오류: 뷰가 초기화되지 않았습니다.");
                            }
                            break;
                        case 2:
                            System.out.println("강의 등록을 하지 않고 이전 메뉴로 돌아갑니다.");
                            break;
                        default:
                            System.out.println("잘못된 메뉴 번호입니다. 이전 메뉴로 돌아갑니다.");
                            break;
                    }
                    break;

                // 강좌 삭제
                case 2:
                    System.out.println("\n--- 2. 강좌 삭제 ---");
                    viewMyCourse();

                    System.out.print("\n--- 삭제할 강좌 번호를 입력하세요. (뒤로가기: 0)--- \n ");

                    long courseIdToDelete = -1;
                    try {
                        courseIdToDelete = Long.parseLong(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("[오류] 숫자로 된 강좌 번호를 입력해주세요.");
                        break;
                    }

                    if (courseIdToDelete == 0) {
                        System.out.println("강좌 삭제를 취소합니다.");
                        break;
                    }

                    if (Application.courseInputView != null) {
                        Application.courseInputView.deleteCourse(courseIdToDelete, instructorId);
                    } else {
                        System.out.println("[시스템 오류] 강좌 관리 화면을 불러올 수 없습니다.");
                    }
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
    /* hi. 추가한 코드_끝 */

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
                    System.out.println("\n 강좌 기본 정보 수정");
                    if (Application.courseInputView != null) {
                        Application.courseInputView.updateCourseView(instructorId);
                    }
                    break;
                case 2:
                    System.out.println("\n강의 세부 내용 수정");
                    if (Application.courseInputView != null) {
                        Application.courseInputView.updateSectionView(instructorId);
                    }
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


}