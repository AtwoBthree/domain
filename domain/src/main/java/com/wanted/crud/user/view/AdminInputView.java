package com.wanted.crud.user.view;

import com.wanted.crud.user.controller.InstructorController;
import com.wanted.crud.user.controller.StudentController;
import com.wanted.crud.user.controller.UserController;
import com.wanted.crud.user.model.dto.InstructorDTO;
import com.wanted.crud.user.model.dto.StudentDTO;
import com.wanted.crud.user.model.dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class AdminInputView {

    private final UserController userController;
    private final StudentController studentController;
    private final InstructorController instructorController;
    private final AdminOutputView outputView;

    private final Scanner sc = new Scanner(System.in);

    public AdminInputView(UserController userController,
                          StudentController studentController,
                          InstructorController instructorController,
                          AdminOutputView outputView) {
        this.userController = userController;
        this.studentController = studentController;
        this.instructorController = instructorController;
        this.outputView = outputView;
    }

    public void adminMenu() {
        while (true) {
            outputView.printMenu();
            int choice = inputInt();

            switch (choice) {
                case 1:
                    selectAllUsers();
                    break;
                case 2:
                    selectAllStudents();
                    break;
                case 3:
                    selectAllInstructors();
                    break;
                case 0:
                    return;
                default:
                    outputView.printError("잘못된 입력입니다.");
            }
        }
    }

    //  전체 유저 조회
    private void selectAllUsers() {
        List<UserDTO> userList = userController.selectAllUsers();
        outputView.printUsers(userList);
    }

    //  수강생 조회
    private void selectAllStudents() {
        List<StudentDTO> studentList = studentController.selectAllStudents();
        outputView.printStudents(studentList);
    }

    //  강사 조회
    private void selectAllInstructors() {
        List<InstructorDTO> instructorList = instructorController.selectAllInstructors();
        outputView.printInstructors(instructorList);
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