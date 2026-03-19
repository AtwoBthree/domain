package com.wanted.crud.enrollment.model.dao;

import com.wanted.crud.enrollment.model.dto.EnrollmentDTO;
import com.wanted.crud.global.utils.QueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    private final Connection connection;

    public EnrollmentDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * 신규 수강 신청 기록 저장
     */
    public Long save(EnrollmentDTO enrollment) throws SQLException {
        String query = QueryUtil.getQuery("enrollment.save");

        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            // progress_rate, start_date, end_date, status, student_id, course_id
            pstmt.setLong(1, enrollment.getProgressRate());
            pstmt.setDate(2, new Date(enrollment.getStartDate().getTime()));
            pstmt.setDate(3, enrollment.getEndDate() != null ? new Date(enrollment.getEndDate().getTime()) : null);
            pstmt.setString(4, enrollment.getStatus());
            pstmt.setLong(5, enrollment.getStudentId());
            pstmt.setLong(6, enrollment.getCourseId());


            int result = pstmt.executeUpdate();

            if (result > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return null;
    }

    /**
     * 특정 수강 신청 내역 조회
     */
    public EnrollmentDTO findById(long id) throws SQLException {
        String query = QueryUtil.getQuery("enrollment.findById");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            ResultSet rset = pstmt.executeQuery();

            if (rset.next()) {
                return new EnrollmentDTO(
                        rset.getLong("enrollment_id"),
                        rset.getLong("progress_rate"),
                        rset.getDate("start_date"),
                        rset.getDate("end_date"),
                        rset.getString("status"),
                        rset.getLong("student_id"),
                        rset.getLong("course_id")
                );
            }
        }
        return null;
    }

    /**
     * 특정 학생의 전체 수강 목록 조회
     */
    public List<EnrollmentDTO> findByStudentId(long studentId) throws SQLException {
        String query = QueryUtil.getQuery("enrollment.findByStudentId");
        List<EnrollmentDTO> list = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, studentId);
            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                list.add(new EnrollmentDTO(
                        rset.getLong("enrollment_id"),
                        rset.getLong("progress_rate"),
                        rset.getDate("start_date"),
                        rset.getDate("end_date"),
                        rset.getString("status"),
                        rset.getLong("student_id"),
                        rset.getLong("course_id")
                ));
            }
        }
        return list;
    }

    /**
     * 수강 상태 및 진도율 업데이트
     */
    public int updateProgress(long id, long progressRate, String status) throws SQLException {
        String query = QueryUtil.getQuery("enrollment.updateProgress");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, progressRate);
            pstmt.setString(2, status);
            pstmt.setLong(3, id);

            return pstmt.executeUpdate();
        }
    }
}