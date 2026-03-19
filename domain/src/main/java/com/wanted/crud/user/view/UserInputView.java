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

    // 로그인
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
    // 로그인해서 유저번호 리턴
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

    // 회원가입
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
    // 아이디, 비번 찾기
    public void findIdPassword() {
        while (true) {
            System.out.println();
            System.out.println("===== 아이디/비밀번호 찾기 메뉴 =====");
            System.out.println("1. 아이디 찾기");
            System.out.println("2. 비밀번호 찾기");
            System.out.println("0. 뒤로가기");
            System.out.print("선택: ");

            int choice = inputInt(); // 기존에 만든 inputInt() 사용

            switch (choice) {
                case 1:
                    // 아이디 찾기
                    displayFindId();
                    break;
                case 2:
                    // 비밀번호 찾기
                    displayFindPassword();
                    break;
                case 0:
                    // 메뉴 종료
                    return;
                default:
                    outputView.printError("잘못된 입력입니다. 0~2 사이로 입력해주세요.");
            }
        }
    }
    // 아이디 찾기
    public void displayFindId() {
        System.out.println("이름을 입력하세요");
        String name = inputString();
        System.out.println("휴대폰 번호를 입력하세요 ex) 010-0000-0000");
        String phonenumber = inputString();


        String userid = controller.findId(name, phonenumber);

        if (userid != null) {
            System.out.println("아이디: " + userid); // 아이디 출력
        } else {
            outputView.printError("아이디와 전화번호가 일치하는 사용자가 없습니다.");
        }

    }

    //user no를 입력하면 강사번호가 나온다.
    public Long instructorId(Long userNo) {
        return controller.instructorFindId(userNo);
    }

    public void displayFindPassword() {
        System.out.println("아이디를 입력하세요");
        String userid = inputString();
        System.out.println("전화번호를 입력하세요");
        String phonenumber = inputString();

        String password = controller.findPassword(userid, phonenumber);

        if (password != null) {
            System.out.println("비밀번호: " + password); // 비밀번호 출력
        } else {
            outputView.printError("아이디와 전화번호가 일치하는 사용자가 없습니다.");
        }

    }
    // 유저(수강생) 정보 수정(주체: 수강생)
    public void updateStudent(Long userNo) {
        System.out.println("변경할 이름을 입력하세요: ");
        String name = inputString();

        System.out.println("변경할 비밀번호를 입력하세요");
        String password = inputString();
        System.out.println("변경할 휴대폰번호를 입력하세요");
        String phoneNumber = inputString();
        if(controller.updateStudent(new UserDTO(
                userNo, null, password, name, phoneNumber, null, null, null, true
        ))) {
            outputView.printMessage("학생 정보 업데이트 완료");;
        } else {
            outputView.printError("학생 정보 업데이트 중 오류 발생");
        }
    }
    // 회원탈퇴
    public void deleteUser() {
        System.out.println("회원 탈퇴를 진행합니다.");

        // 1. 아이디 입력
        System.out.print("아이디를 입력하세요: ");
        String id = inputString();

        // 2. 비밀번호 입력
        System.out.print("비밀번호를 입력하세요: ");
        String password = inputString();

        // 3. Controller 호출
        boolean result = controller.dropUser(id, password); // Controller에서 boolean 반환

        // 4. 결과 출력
        if (result) {
            System.out.println("✅ 회원 탈퇴가 완료되었습니다.");
        } else {
            outputView.printError("회원 탈퇴 실패: 아이디 또는 비밀번호가 틀렸거나, 이미 탈퇴한 계정일 수 있습니다.");
        }
    }
    // 관리자 메서드
    // 유저 정보 수정(주체 : 관리자)
  /*  public void viewStudentBycourseId(){
        controller.viewStudentBycourseId();
    }
*/

    // 유저 정보 삭제

    //


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