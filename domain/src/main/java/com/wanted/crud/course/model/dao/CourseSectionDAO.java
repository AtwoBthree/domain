package com.wanted.crud.course.model.dao;

import com.wanted.crud.course.model.dto.SectionDTO;
import com.wanted.crud.global.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseSectionDAO {

    /* comment.
     * CourseSectionDAO는 섹션(단위 강의) 테이블 전용 데이터 접근 객체입니다.
     */
    private final Connection connection;

    public CourseSectionDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * 새로운 섹션을 DB에 저장합니다.
     * @param newSection 저장할 섹션 정보가 담긴 DTO
     * @return 영향받은 행의 수 (성공 시 1)
     */
    public int save(SectionDTO newSection) throws SQLException {

        // XML 파일에서 "courseSection.save" 키로 저장된 SQL 구문을 가져옴
        String query = QueryUtil.getQuery("courseSection.save");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            /* * SQL 쿼리 예상 구조:
             * INSERT INTO sections (title, video_url, material_url, created_at, course_id)
             * VALUES (?, ?, ?, ?, ?)
             */

            pstmt.setString(1, newSection.getTitle());
            pstmt.setString(2, newSection.getVideoUrl());
            pstmt.setString(3, newSection.getMaterialUrl());

            // java.util.Date를 java.sql.Date로 형변환하여 전달
            // 만약 시/분/초까지 필요하다면 setTimestamp를 사용하는 것이 좋습니다.
            if (newSection.getCreatedAt() != null) {
                pstmt.setDate(4, new java.sql.Date(newSection.getCreatedAt().getTime()));
            } else {
                pstmt.setNull(4, java.sql.Types.DATE);
            }

            pstmt.setLong(5, newSection.getCourseId());

            // DML(Insert) 실행 후 결과 반환
            return pstmt.executeUpdate();
        }
    }
}