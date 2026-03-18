package com.wanted.crud.user.view;

import com.wanted.crud.user.model.dto.StudentDTO;

import java.util.List;

public class StudentOutputView {

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

    public void printMessage(String s) {
        System.out.println("=========================================");
        System.out.println(s);
        System.out.println("=========================================");
    }

    public void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
