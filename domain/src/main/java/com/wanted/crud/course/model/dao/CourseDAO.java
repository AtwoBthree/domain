package com.wanted.crud.course.model.dao;

import com.wanted.crud.course.model.dto.CourseDTO;
import com.wanted.crud.course.model.dto.CourseSectionDTO;
import com.wanted.crud.course.model.dto.SectionDTO;
import com.wanted.crud.global.utils.QueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private final Connection connection;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    // ... findAll, save, delete, find 메소드들은 기존과 동일 (CourseDTO 기준) ...

    /**
     * 코스 + 섹션으로 이루어진 데이터 반환 (JOIN 쿼리 결과 처리)
     */
    public CourseSectionDTO findCourseWithSections(long courseId) throws SQLException {
        String query = QueryUtil.getQuery("course.findCourseWithSections");

        CourseSectionDTO courseSectionDTO = null;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, courseId);

            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {

                // 1. 강좌 정보 세팅 (첫 행에서만 생성)
                if(courseSectionDTO == null) {
                    courseSectionDTO = new CourseSectionDTO(
                            rset.getLong("course_id"),
                            rset.getString("title"),
                            rset.getString("description"),
                            rset.getLong("price"),
                            rset.getString("status"),
                            rset.getDate("created_at"),
                            rset.getLong("instructor_id")
                    );
                }

                // 2. 섹션 정보 세팅 (새로운 SectionDTO 구조 적용)
                Long sectionId = rset.getLong("section_id");

                // LEFT JOIN 결과에서 섹션이 존재하는 경우만 리스트에 추가
                if(!rset.wasNull()) {
                    SectionDTO section = new SectionDTO(
                            sectionId,                        // 1. sectionId
                            rset.getString("section_title"),   // 2. title
                            rset.getString("video_url"),       // 3. videoUrl (추가됨)
                            rset.getString("material_url"),    // 4. materialUrl (추가됨)
                            rset.getDate("section_created_at"),// 5. createdAt (추가됨)
                            rset.getLong("course_id")          // 6. courseId
                    );
                    courseSectionDTO.getSections().add(section);
                }
            }
        }

        return courseSectionDTO;
    }

    public List<CourseDTO> findAll() {
        return null;
    }

    public Long save(CourseDTO newCourse) {
        return 0L;
    }

    public int delete(long id) {
        return 0;
    }

    public CourseDTO find(long id) {
        return new CourseDTO(null,null,null,null,null,null,null);
    }


//    public CourseDTO find(long id) {
//        return new CourseDTO();
//    }
}