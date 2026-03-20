package com.wanted.crud.course.model.dao;

import com.wanted.crud.course.model.dto.CourseDTO;
import com.wanted.crud.course.model.dto.SectionDTO;
import com.wanted.crud.global.utils.CourseQueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private final Connection connection;

    public CourseDAO (Connection connection) {
        this.connection = connection;
    }

    // 전체 강좌 조회
    public List<CourseDTO> selectAllCourses() throws SQLException {
        String query = CourseQueryUtil.getQuery("course.findAllCourse");
        List<CourseDTO> courseList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rset = pstmt.executeQuery()) {

            while (rset.next()){
                CourseDTO course = new CourseDTO(
                        rset.getLong("course_id"),
                        rset.getString("title"),
                        rset.getString("description"),
                        rset.getLong("price"),
                        rset.getString("status"),
                        rset.getDate("created_at"),
                        rset.getLong("instructor_id")
                );
                courseList.add(course);
            }
        }
        return courseList;
    }


    // courseId 사용
    public List<Long> findcourseid(Long instructorId) throws SQLException {
        String query = CourseQueryUtil.getQuery("course.findId");
        List<Long> courseIdList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, instructorId);

            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    courseIdList.add(rset.getLong("course_id"));
                }
            }
        }
        return courseIdList;
    }




    // 내 강좌 조회
    public List<CourseDTO> selectCourse(long id) throws SQLException {
        String query = CourseQueryUtil.getQuery("course.selectMyCourse");
        List<CourseDTO> courseMyList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setLong(1, id);
            ResultSet rset = pstmt.executeQuery();

            while (rset.next()){
                CourseDTO course = new CourseDTO(
                        rset.getLong("course_id"),
                        rset.getString("title"),
                        rset.getString("description"),
                        rset.getLong("price"),
                        rset.getString("Status"),
                        rset.getDate("created_at"),
                        rset.getLong("instructor_id")
                );

                courseMyList.add(course);
            }

        }
        return courseMyList;
    }


    // 내 강좌 등록
    public Long save(CourseDTO newCourse) throws SQLException {
        String query = CourseQueryUtil.getQuery("course.insertCourse");

        try (PreparedStatement pstmt = connection.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, newCourse.getTitle());
            pstmt.setString(2, newCourse.getDescription());
            pstmt.setLong(3, newCourse.getPrice());
            pstmt.setLong(4, newCourse.getInstructorId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getLong(1);
                    }
                }
            }
        }
        return null;
    }


    // 내 강좌 삭제
    // CourseDAO.java 내부
    public boolean deleteCourse(Long courseId, Long instructorId) throws SQLException {
        String query = CourseQueryUtil.getQuery("course.deleteCourse");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, courseId);
            pstmt.setLong(2, instructorId);

            int result = pstmt.executeUpdate();

            return result > 0;
        }
    }

    // 내 강좌 수정
    public int updateCourse(CourseDTO course) throws SQLException {
        String query = CourseQueryUtil.getQuery("course.updateCourse");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, course.getTitle());
            pstmt.setString(2, course.getDescription());
            pstmt.setLong(3, course.getPrice());
            pstmt.setLong(4, course.getCourseId());
            pstmt.setLong(5, course.getInstructorId());

            return pstmt.executeUpdate();
        }
    }




}