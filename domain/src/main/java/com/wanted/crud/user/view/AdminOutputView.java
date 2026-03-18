package com.wanted.crud.user.view;

import com.wanted.crud.user.model.dto.InstructorDTO;
import com.wanted.crud.user.model.dto.StudentDTO;
import com.wanted.crud.user.model.dto.UserDTO;

import java.util.List;

public class AdminOutputView {

    public void printMenu() {
        System.out.println("\n===== 관리자 메뉴 =====");
        System.out.println("1. 전체 유저 조회");
        System.out.println("2. 학생 조회");
        System.out.println("3. 강사 조회");
        System.out.println("0. 종료");
    }

    public void printUsers(List<UserDTO> userList) {
        for (UserDTO user : userList) {
            System.out.println(user);
        }
    }

    public void printStudents(List<StudentDTO> studentList) {
        for (StudentDTO student : studentList) {
            System.out.println(student);
        }
    }

    public void printInstructors(List<InstructorDTO> instructorList) {
        for (InstructorDTO instructor : instructorList) {
            System.out.println(instructor);
        }
    }

    public void printMessage(String s) {
        System.out.println("=========================================");
        System.out.println(s);
        System.out.println("=========================================");
    }

    public void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
