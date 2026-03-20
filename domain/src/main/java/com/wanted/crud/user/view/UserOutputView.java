package com.wanted.crud.user.view;

import com.wanted.crud.enrollment.model.dto.EnrollmentStudentDTO;
import com.wanted.crud.user.model.dto.InstructorDTO;
import com.wanted.crud.user.model.dto.StudentDTO;
import com.wanted.crud.user.model.dto.UserDTO;

import java.util.List;

public class UserOutputView {

    // ===== UserOutputView =====
    public void printError(String message) {
        System.out.println(message);
    }

    public void printMessage(String s) {
        System.out.println("=========================================");
        System.out.println(s);
        System.out.println("=========================================");
    }

    public void printUsers(List<UserDTO> userList) {
        if (userList == null || userList.isEmpty()) {
            System.out.println("조회된 사용자가 없습니다.");
            return;
        }

        System.out.println("===사용자 전체 조회 목록 결과===");
        for (UserDTO userDTO : userList) {
            System.out.println(userDTO);
        }
    }

    public void printSuccess(String message) {
        System.out.println("🔰" + message);
    }

    // ===== StudentOutputView =====
    public void printMenu() {
        System.out.println("\n===== 학생 메뉴 =====");
        System.out.println("1. 내 정보 조회");
        System.out.println("2. 수강 강의 조회");
        System.out.println("0. 종료");
    }

    public void printStudent(StudentDTO student) {
        System.out.println(student);
    }

    public void printCourses(List<String> courses) {
        for (String course : courses) {
            System.out.println(course);
        }
    }

    // ===== InstructorOutputView =====
    public void printInstructorMenu() {
        System.out.println("\n===== 강사 메뉴 =====");
        System.out.println("1. 내 강의 조회");
        System.out.println("2. 강의 등록");
        System.out.println("0. 종료");
    }

    public void printInstructors(List<InstructorDTO> instructorList) {
        for (InstructorDTO instructor : instructorList) {
            System.out.println(instructor);
        }
    }

    // ===== AdminOutputView =====
    public void printAdminMenu() {
        System.out.println("\n===== 관리자 메뉴 =====");
        System.out.println("1. 전체 유저 조회");
        System.out.println("2. 학생 조회");
        System.out.println("3. 강사 조회");
        System.out.println("0. 종료");
    }


    public void printStudents(List<StudentDTO> studentList) {
        for (StudentDTO student : studentList) {
            System.out.println(student);
        }
    }

    // 강좌별 수강생 조회
    public void printEnrollmentStudents(List<EnrollmentStudentDTO> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("조회된 수강생이 없습니다.");
            return;
        }

        for (EnrollmentStudentDTO dto : list) {
            System.out.println(dto);
        }
    }
}