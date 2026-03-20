package com.wanted.crud.course.model.dao;


import com.wanted.crud.course.model.dto.SectionDTO;
import com.wanted.crud.global.utils.CourseQueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectionDAO {


    private final Connection connection;

    public SectionDAO (Connection connection) {
        this.connection = connection;
    }

    // 내 강의 등록
    // title, video_url, material_url, created_at, course_id
    public Long sectionSave(SectionDTO newSection) throws SQLException {
        String query = CourseQueryUtil.getQuery("course.insertSection");

        try (PreparedStatement pstmt = connection.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, newSection.getTitle());
            pstmt.setString(2, newSection.getVideoUrl());
            pstmt.setString(3, newSection.getMaterialUrl());
            pstmt.setLong(4, newSection.getCourseId());

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

    // 강의 수정
    public List<SectionDTO> selectSectionsByCourseId(long courseId) throws SQLException {
        String query = "SELECT * FROM SECTION WHERE course_id = ?"; // CourseQueryUtil에 등록 권장
        List<SectionDTO> sections = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, courseId);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                sections.add(new SectionDTO(
                        rset.getLong("section_id"),
                        rset.getString("title"),
                        rset.getString("video_url"),
                        rset.getString("material_url"),
                        rset.getDate("created_at"),
                        rset.getLong("course_id")
                ));
            }
        }
        return sections;
    }

    // 강의 정보 수정 (보안을 위해 instructorId 체크 포함)
    public int updateSection(SectionDTO section, Long instructorId) throws SQLException {
        // 1. section_id로 접근하되, 해당 section의 course_id가 로그인한 강사의 것인지 체크
        String query = "UPDATE SECTION s " +
                "JOIN COURSE c ON s.course_id = c.course_id " +
                "SET s.title = ?, s.video_url = ?, s.material_url = ? " +
                "WHERE s.section_id = ? AND c.instructor_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, section.getTitle());      // 새로운 제목
            pstmt.setString(2, section.getVideoUrl());   // 새로운 URL
            pstmt.setString(3, section.getMaterialUrl()); // 새로운 자료
            pstmt.setLong(4, section.getSectionId());    // ★ 수정할 강의 번호 (여기선 2번)
            pstmt.setLong(5, instructorId);              // ★ 로그인한 강사 번호 (여기선 1번)

            return pstmt.executeUpdate();
        }
    }


}
