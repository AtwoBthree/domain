package com.wanted.crud.enrollment.model.dto;


public class EnrollmentStudentDTO {

    private Long courseId;
    private String courseTitle;

    private Long userNo;
    private String userId;
    private String userName;
    private String userPhoneNumber;
    private Long progressRate;

    public EnrollmentStudentDTO(Long courseId, String courseTitle,
                                Long userNo, String userId,
                                String userName, String userPhoneNumber) {

        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.userNo = userNo;
        this.userId = userId;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
    }

    public EnrollmentStudentDTO(Long courseId, String courseName, Long progressRate) {
        this.courseId = courseId;
        this.courseTitle = courseName;
        this.progressRate = progressRate;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public Long getUserNo() {
        return userNo;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public Long getProgress(){ return progressRate; }

    // ✨ 정밀 정렬을 위한 여백 계산기 (너비 50으로 정교화)
    private String padRight(String text) {
        int targetWidth = 50;
        int width = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // 한글 및 이모지(🔹, 📚)는 보통 2칸을 차지합니다.
            // c가 char 타입일 때
            String s = String.valueOf(c);

            if ((c >= '가' && c <= '힣') || s.equals("🔹") || s.equals("📚") || s.equals("▶")) {
                width += 2;
            } else {
                width += 1;
            }
        }
        int padding = targetWidth - width;
        if (padding > 0) return text + " ".repeat(padding);
        return text;
    }

    @Override
    public String toString() {
        // 테두리가 없으므로 글자 수 제한을 훨씬 넉넉하게 (25자) 둡니다.
        String safeName = (userName != null) ? userName : "Unknown";
        String safeTitle = (courseTitle != null) ? courseTitle : "No Title";

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("  ╔══════════════════════════════════════════════════════\n");
        sb.append("  ║      [ DIGITAL STUDENT IDENTIFICATION ]\n");
        sb.append("  ╠══════════════════════════════════════════════════════\n");
        sb.append("  ║\n");
        // 증명사진 칸과 기본 정보 정렬
        sb.append("  ║    ╭───────────╮   🆔 이 름   : ").append(safeName).append("\n");
        sb.append("  ║    │   (^_^)   │   🆔 아이디  : ").append(userId).append("\n");
        sb.append("  ║    │   /| |\\  │   🆔 번 호   : #").append(userNo).append("\n");
        sb.append("  ║    │   /   \\  │   📞 연락처  : ").append(userPhoneNumber).append("\n");
        sb.append("  ║    ╰───────────╯\n");
        sb.append("  ╟──────────────────────────────────────────────────────\n");
        sb.append("  ║  📚 수강 강좌 정보 (ENROLLED COURSE)\n");
        sb.append("  ╟──────────────────────────────────────────────────────\n");
        sb.append("  ║  🔹 강좌 번호 : ").append(courseId).append("\n");
        sb.append("  ║  🔹 강좌 제목 : ").append(safeTitle).append("\n");
        sb.append("  ╚══════════════════════════════════════════════════════\n");

        return sb.toString();
    }
}
