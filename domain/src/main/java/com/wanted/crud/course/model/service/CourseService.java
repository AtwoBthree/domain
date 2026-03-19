package com.wanted.crud.course.model.service;

import com.wanted.crud.course.model.dao.CourseDAO;
import com.wanted.crud.course.model.dao.SectionDAO;
import com.wanted.crud.course.model.dto.CourseDTO;
import com.wanted.crud.course.model.dto.SectionDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseService {

    private final CourseDAO courseDAO;
    private final SectionDAO sectionDAO;
    private final Connection connection;

    // 강좌 번호 리턴
    public List<Long> findCoursesId(Long instructorId) {
        try {
            // ★ 2. 바구니 모양 맞추기: DAO가 List<Long>을 주니까, 받는 변수도 List<Long>으로 맞춰줍니다.
            // (DB 조회를 try 블록 안으로 넣어서 에러 처리도 깔끔하게 묶었습니다.)
            List<Long> courseList = courseDAO.findcourseid(instructorId);

            if (courseList != null && !courseList.isEmpty()) {
                return courseList; // 강좌 번호들이 담긴 리스트를 통째로 반환!
            } else {
                System.out.println("등록된 강좌가 없습니다.");
                return null; // 없으면 null 반환
            }
        } catch (SQLException e) {
            throw new RuntimeException("🚨강좌 목록 조회 중 Error 발생🚨" + e);
        }
    }




    public List<CourseDTO> findAllCourses() {
        try {
            return courseDAO.selectAllCourses();
        } catch (SQLException e) {
            throw new RuntimeException("🚨전체 강좌 조회 중 Error 발생🚨" + e);
        }
    }


    public CourseService(Connection connection) {
        this.courseDAO = new CourseDAO(connection);
        this.sectionDAO = new SectionDAO(connection);
        this.connection = connection;
    }

    public List<CourseDTO> findMyCourse(Long id) {
        try {
            return courseDAO.selectCourse(id);
        } catch (SQLException e) {
            throw new RuntimeException("🚨내 강좌 조회 중 Error 발생🚨" + e);
        }
    }

    public Long saveCourse(CourseDTO newCourse) {
        // save를 throw 해둠. 그래서 try-catch로 감쌈.
        try {
            return courseDAO.save(newCourse);
        } catch (SQLException e) {
            throw new RuntimeException("강좌 등록 중 Error 발생!!! 🚨");
        }
    }

    public Long saveSection(SectionDTO newSection) {
        // save를 throw 해둠. 그래서 try-catch로 감쌈.
        try {
            return sectionDAO.sectionSave(newSection);
        } catch (SQLException e) {
            throw new RuntimeException("강좌 등록 중 Error 발생!!! 🚨");
        }
    }

    // 내 강좌 삭제
    public int deleteCourse(long id){
        try{
            return courseDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("강좌 삭제 중 Error 발생!! 🚨");
        }
    }



}