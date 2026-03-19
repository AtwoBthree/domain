package com.wanted.crud.course.view;


import com.wanted.crud.course.model.dto.CourseDTO;

import java.util.List;

public class CourseOutputView {

    public void printMessage(String s) {
    }

    public void printCourses2(List<CourseDTO> courseList) {

        if (courseList == null || courseList.isEmpty()) {
            System.out.println("조회된 강좌가 없습니다!!");
            return;
        }

        System.out.println("================강의 전체 조회 목록 결과================");
        for (CourseDTO courseDTO : courseList) {
            System.out.println(courseDTO);
        }

    }


    public void printCourses(List<CourseDTO> findMyCourse) {
        if(findMyCourse == null || findMyCourse.isEmpty()){
            System.out.println("조회된 강좌가 없습니다.");
            return;
        }

        System.out.println("==============내 강좌 조회 목록=============");
        for(CourseDTO courseDTO : findMyCourse){
            System.out.println(courseDTO);
        }
    }

    public void printSuccess(String message) {

        System.out.println("✅" + message);

    }

    public void printError(String message) {

        System.out.println("❌" + message);

    }


}
