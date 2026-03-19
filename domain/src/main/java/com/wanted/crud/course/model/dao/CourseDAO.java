package com.wanted.crud.course.model.dao;

import com.wanted.crud.course.model.dto.CourseDTO;
import com.wanted.crud.course.model.dto.SectionDTO;
import com.wanted.crud.global.utils.QueryUtil;

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
        String query = QueryUtil.getQuery("course.findAllCourse");
        List<CourseDTO> courseList = new ArrayList<>();

        // 파라미터(?)가 없으므로 setString, setLong 등이 필요 없습니다.
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


    public List<Long> findcourseid(Long instructorId) throws SQLException {
        // 쿼리 이름 변경 (강사 번호로 강좌 번호들 찾기)
        String query = QueryUtil.getQuery("course.findId");

        // 번호들을 담을 빈 바구니(List) 준비
        List<Long> courseIdList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            // 1. 파라미터로 '강사 번호'를 세팅합니다.
            pstmt.setLong(1, instructorId);

            try (ResultSet rset = pstmt.executeQuery()) {
                // 2. 강좌가 여러 개일 수 있으니 if 대신 ★while★을 사용합니다!
                while (rset.next()) {
                    // 3. DB에서 찾은 강좌 번호를 바구니에 차곡차곡 담습니다.
                    courseIdList.add(rset.getLong("instructor_id"));
                }
            }
        }
        return courseIdList; // 4. 다 담은 바구니를 Service로 리턴!
    }




    // 내 강좌 조회
    public List<CourseDTO> selectCourse(long id) throws SQLException {
        String query = QueryUtil.getQuery("course.selectMyCourse");
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
    // DAO 수정 (ResultSet 부분만 살짝 변경)
    public Long save(CourseDTO newCourse) throws SQLException {
        String query = QueryUtil.getQuery("course.insertCourse");

        try (PreparedStatement pstmt = connection.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, newCourse.getTitle());
            pstmt.setString(2, newCourse.getDescription());
            pstmt.setLong(3, newCourse.getPrice());
            pstmt.setLong(4, newCourse.getInstructorId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                // ★ 수정: ResultSet도 try 괄호 안에서 열어주어 자동으로 닫히게 만듭니다.
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
    public int delete(long id) throws SQLException {

        String query = QueryUtil.getQuery("course.deleteCourse");

        try (
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            return pstmt.executeUpdate();
        }
    }


}