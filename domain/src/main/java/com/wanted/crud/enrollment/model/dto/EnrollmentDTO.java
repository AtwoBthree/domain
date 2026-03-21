package com.wanted.crud.enrollment.model.dto;

import java.util.Date;

public class EnrollmentDTO {
    private Long enrollmentId;
    private Long progressRate;
    private Date startDate;
    private Date endDate;
    private String status;
    private Long studentId;
    private Long courseId;

    public EnrollmentDTO(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public EnrollmentDTO(Long enrollmentId, Long progressRate, Date startDate, Date endDate, String status, Long studentId, Long courseId) {
        this.enrollmentId = enrollmentId;
        this.progressRate = progressRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Long getProgressRate() {
        return progressRate;
    }

    public void setProgressRate(Long progressRate) {
        this.progressRate = progressRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    // ✨ 여백 계산기 (너비 58 고정)
    private String padRight(String text) {
        int targetWidth = 58;
        int width = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= '가' && c <= '힣') width += 2;
            else width += 1;
        }
        int padding = targetWidth - width;
        if (padding > 0) return text + " ".repeat(padding);
        return text;
    }

    @Override
    public String toString() {
        // 1. 상태값 비교 (null 방어 포함)
        boolean isActive = "1".equals(status);

        // 2. 상태에 따른 캐릭터 표정 및 메시지
        // isActive가 true면 기쁜 표정(^ω^), false면 시무룩한 표정(-ㅅ-)
        String dogFace    = isActive ? "  ( ^ω^ ) " : "  ( -ㅅ- ) ";
        String statusMsg  = isActive ? " [ 🔥 열공 모드 작동 중! ] " : " [ ❄️ 수강이 종료되었습니다 ] ";
        String statusIcon = isActive ? "✅ ACTIVE " : "❌ CLOSED ";

        // 3. 날짜 null 방어
        String safeStart = (startDate != null) ? String.valueOf(startDate) : "미정";
        String safeEnd   = (endDate != null) ? String.valueOf(endDate) : "미정";

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("  ╔══════════════════════════════════════════════════════════\n");
        sb.append("  ║          [ 공 식 수 강 증 명 서 ]\n");
        sb.append("  ╠══════════════════════════════════════════════════════════\n");
        sb.append("  ║   /\\___/\\ \n");
        sb.append("  ║ ").append(dogFace).append("  🆔 등록 번호 : EN-").append(String.format("%04d", enrollmentId)).append("\n");
        sb.append("  ║  |  v  |   🧑‍🎓 수 강 생 : ").append(studentId).append("번 학생\n");
        sb.append("  ║  |     |   📘 수강 강좌 : ").append(courseId).append("번 강좌\n");
        sb.append("  ║  |_____|  ").append(statusMsg).append("\n");
        sb.append("  ╟──────────────────────────────────────────────────────────\n");
        sb.append("  ║  ✨ 현재 상태 : ").append(statusIcon).append("\n");
        sb.append("  ║  📅 수강 기간 : ").append(safeStart).append(" ~ ").append(safeEnd).append("\n");
        sb.append("  ╚══════════════════════════════════════════════════════════\n");

        return sb.toString();
    }
}
