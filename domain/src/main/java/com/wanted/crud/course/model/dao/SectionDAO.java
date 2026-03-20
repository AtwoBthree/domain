package com.wanted.crud.course.model.dao;


import com.wanted.crud.course.model.dto.SectionDTO;
import com.wanted.crud.global.utils.CourseQueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


}
