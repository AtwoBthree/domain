package com.wanted.crud.user.view;

import com.wanted.crud.user.controller.InstructorController;
import com.wanted.crud.user.controller.StudentController;
import com.wanted.crud.user.controller.UserController;
import com.wanted.crud.user.model.dto.UserDTO;

import java.util.Scanner;

public class UserInputView {
    private final UserController controller;
    private final StudentController studentController;
    private final InstructorController instructorController;




    private final UserOutputView outputView;
    private final StudentOutputView studentOutputView;
    private final InstructorOutputView instructorOutputView;
    private final AdminOutputView adminOutputView;

    private final AdminInputView adminInputView;
    private final StudentInputView studentInputView;
    private final InstructorInputView instructorInputView;


    private final Scanner sc = new Scanner(System.in);


    public UserInputView(UserController controller, StudentController studentController,
                         InstructorController instructorController, UserOutputView outputView,
                         StudentOutputView studentOutputView, InstructorOutputView instructorOutputView,
                         AdminOutputView adminOutputView, AdminInputView adminInputView,
                         StudentInputView studentInputView, InstructorInputView instructorInputView) {
        this.controller = controller;
        this.outputView = outputView;
        this.studentOutputView = studentOutputView;
        this.instructorOutputView = instructorOutputView;
        this.instructorController = instructorController;
        this.studentController = studentController;
        this.adminOutputView = adminOutputView;
        this.adminInputView = adminInputView;
        this.studentInputView = studentInputView;
        this.instructorInputView = instructorInputView;
    }


    public String displayMainMenu() {
        while (true) {
            System.out.println();
            System.out.println("======================================");
            System.out.println("              로그인화면                ");
            System.out.println("======================================");
            System.out.print("아이디: ");
            String id = sc.nextLine();
            System.out.print("비밀번호: ");
            String password = sc.nextLine();
            UserDTO loginUser = controller.login(id, password);

            if (loginUser == null) {
                outputView.printError("아이디 또는 비밀번호가 틀렸습니다.");
                continue;
            }

            // TODO: 실제 로그인 로직으로 교체
            String role = loginUser.getUserRole();
            return role;

            // 로그인시 내정보 조회가 가능하게끔 매개변수로 loginUser 전달
            switch (role) {
                case "STUDENT":
                    studentInputView.studentMenu(loginUser);
                    break;
                case "INSTRUCTOR":
                    instructorInputView.instructorMenu(loginUser);
                    break;
                case "ADMIN":
                    adminInputView.adminMenu();
                    break;
                default:
                    outputView.printError("다시 선택해주세요");
            }
        }
    }

}
