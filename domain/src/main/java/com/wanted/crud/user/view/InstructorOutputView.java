package com.wanted.crud.user.view;

import com.wanted.crud.user.model.dto.InstructorDTO;

import java.util.List;

public class InstructorOutputView {

    public void printMenu() {
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

    public void printCourses(List<String> courses) {
        for (String course : courses) {
            System.out.println(course);
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
