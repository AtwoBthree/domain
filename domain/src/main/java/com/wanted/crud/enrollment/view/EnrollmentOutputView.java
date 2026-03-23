package com.wanted.crud.enrollment.view;

import com.wanted.crud.enrollment.model.dto.EnrollmentStudentDTO;

import java.util.List;

public class EnrollmentOutputView {
    public static void printStudentCourses(List<EnrollmentStudentDTO> list) {

        System.out.println("\n📖 [ 나의 수강 강좌 목록 ]");

// 1. 데이터 없을 때
        if (list == null || list.isEmpty()) {
            System.out.println("수강 중인 강좌가 없습니다.");
            return;
        }

// 2. 헤더
        System.out.println("-------------------------------------------------");
        System.out.printf("%-10s %-20s %-10s\n", "강좌 번호", "강좌명", "진척도");
        System.out.println("-------------------------------------------------");

// 3. 데이터 출력
        for (EnrollmentStudentDTO dto : list) {
            System.out.printf("%-10d %-20s %-10d%%\n",
                    dto.getCourseId(),
                    dto.getCourseTitle(),
                    dto.getProgress()
            );
        }

        System.out.println("-------------------------------------------------");
    }
}