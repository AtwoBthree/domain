package com.wanted.crud.user.view;

import com.wanted.crud.enrollment.model.dto.EnrollmentStudentDTO;
import com.wanted.crud.user.model.dto.InstructorDTO;
import com.wanted.crud.user.model.dto.StudentDTO;
import com.wanted.crud.user.model.dto.UserDTO;

import java.util.List;

public class UserOutputView {

    // 공통 출력 메서드
    public void printError(String message) {
        System.out.println("\n🚨 [ERROR] ❌ " + message);
    }

    public void printSuccess(String message) {
        System.out.println("\n✨ [SUCCESS] ✅ " + message);
    }

    public void printMessage(String s) {
        System.out.println("💬 " + s);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    // User 관련 출력
    public void printUsers(List<UserDTO> userList) {
        System.out.println("👥 [ 시스템 사용자 목록 ]");

        if (userList == null || userList.isEmpty()) {
            System.out.println("🚨 조회된 사용자가 한 명도 없습니다.");
        } else {
            for (UserDTO userDTO : userList) {
                System.out.println(userDTO);
            }
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }



    // 메뉴 출력 (박스 스타일)
    public void printMenu() {
        System.out.println("\n┌────────────────── [ 수강생 메뉴 ] ──────────────────");
        System.out.println("│  1. 내 정보 조회                                      ");
        System.out.println("│  2. 수강 강의 조회                                    ");
        System.out.println("│  0. 종료                                             ");
        System.out.println("└─────────────────────────────────────────────────────");
    }

    public void printInstructorMenu() {
        System.out.println("\n┌─────────────────── [ 강사 메뉴 ] ──────────────────");
        System.out.println("│  1. 내 강의 조회                                   ");
        System.out.println("│  2. 강의 등록                                      ");
        System.out.println("│  0. 종료                                          ");
        System.out.println("└────────────────────────────────────────────────────");
    }

    public void printAdminMenu() {
        System.out.println("\n┌────────────────── [ 관리자 메뉴 ] ─────────────────");
        System.out.println("│  1. 전체 유저 조회                                 ");
        System.out.println("│  2. 학생 목록 조회                                 ");
        System.out.println("│  3. 강사 목록 조회                                 ");
        System.out.println("│  0. 종료                                           ");
        System.out.println("└────────────────────────────────────────────────────");
    }

    // Student 관련 출력
    public void printStudent(StudentDTO student) {
        System.out.println(student);
    }

    public void printStudents(List<StudentDTO> studentList) {
        System.out.println("🧑‍🎓 [ 수강생 명단 ]");
        for (StudentDTO student : studentList) {
            System.out.println(student);
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    public void printAllStudents(List<UserDTO> studentList) {
        System.out.println("🧑‍🎓 [ 수강생 명단 ]");
        for (UserDTO student : studentList) {
            System.out.println(student);
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    // Instructor 관련 출력
    public void printInstructors(List<InstructorDTO> instructorList) {
        System.out.println("👨‍🏫 [ 소속 강사 명단 ]");
        for (InstructorDTO instructor : instructorList) {
            System.out.println(instructor);
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    public void printInstructors2(List<UserDTO> list) {
        System.out.println("🔍 [ 강사 검색 결과 상세 리스트 ]");
        System.out.println("──────────────────────────────────────────────────────");
        System.out.println(" 번호  |  아이디  |  이름  |  전화번호  |  생성일  |  상태 ");
        System.out.println("──────────────────────────────────────────────────────");
        for (UserDTO u : list) {
            System.out.printf(" #%-3d | %-8s | %-5s | %-13s | %s | %s%n",
                    u.getUserNo(),
                    u.getUserId(),
                    u.getUserName(),
                    u.getUserPhoneNumber(),
                    u.getCreatedAt(),
                    u.isStatus() ? "✅ 활성" : "❌ 비활성"
            );
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    // Enrollment 관련 출력
    public void printEnrollmentStudents(List<EnrollmentStudentDTO> list) {
        System.out.println("📊 [ 강좌별 수강 현황 ]");

        if (list == null || list.isEmpty()) {
            System.out.println("🚨 현재 수강 신청한 학생이 없습니다.");
        } else {
            for (EnrollmentStudentDTO dto : list) {
                System.out.println(dto);
            }
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
}