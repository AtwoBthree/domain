package com.wanted.crud.enrollment.model.dto;

public class ForStudentEnrollmentDTO {
    // --- ANSI 컬러 상수 ---
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String B_CYAN = "\u001B[1;36m";
    private static final String B_YELLOW = "\u001B[1;33m";
    private static final String B_MAGENTA = "\u001B[1;35m";
    private static final String B_WHITE = "\u001B[1;37m";
    private static final String B_GREEN = "\u001B[1;32m";

    private Long courseId;
    private String courseTitle;
    private Long progressRate;

    public ForStudentEnrollmentDTO(Long courseId, String courseTitle, Long progressRate) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.progressRate = progressRate;
    }

    /**
     * 토끼 캐릭터와 뱃지 스타일이 적용된 프리미엄 toString
     */
    public String toString(boolean isAdmin) {
        // [데이터 처리]
        String safeTitle = (courseTitle != null) ? courseTitle : "미지정 강좌";
        int progress = (progressRate != null) ? progressRate.intValue() : 0;

        // ID 포맷팅 (예시: EN-001)
        String formattedEnrollmentId = String.format("%03d", courseId);
        String formattedStudentId = isAdmin ? "ADMIN" : "USER-77"; // 예시 데이터

        // 진도율 바 (요청하신 스타일 반영)
        int gaugeCount = progress / 10;
        String progressBar = B_GREEN + "■".repeat(gaugeCount) + RESET + "□".repeat(10 - gaugeCount);
        String paddedProgressNum = String.format("%3d%%", progress);

        // 상태 아이콘 설정
        String statusIcon = (progress >= 100) ? "✅ 수강 완료" : (progress > 0 ? "✍️ 학습 중" : "⏳ 시작 전");

        // 더미 수강 기간 (실제 데이터가 있다면 필드 추가 후 연결하세요)
        String startStr = "2024-01-01";
        String endStr = "2024-12-31";

        // 테마 설정
        String mainColor = isAdmin ? B_CYAN : CYAN;

        return "\n" +
                mainColor + "  ╭━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                mainColor + "  ┃ " + B_YELLOW + "      * ﾟ  +  .  * " + mainColor + "           " + B_MAGENTA + "    🆔 [ 등록 번호: EN-" + formattedEnrollmentId + " ]    " + B_CYAN + " 🧑‍🎓 [ 학생 번호: " + formattedStudentId + " ]\n" +
                mainColor + "  ┃ " + B_WHITE + "        (\\_/)      " + mainColor + " " + B_YELLOW + " OFFICIAL" + B_GREEN + "     📘 수강 강좌 : " + RESET + safeTitle + " (" + courseId + "번)\n" +
                mainColor + "  ┃ " + B_WHITE + "       ( •_•)      " + mainColor + " " + B_YELLOW + " ENROLL  " + B_MAGENTA + "     📊 진 도 율 : " + progressBar + " " + B_WHITE + paddedProgressNum + "   " + B_GREEN + "          📌 상 태 : " + RESET + statusIcon + "\n" +
                mainColor + "  ┃ " + B_WHITE + "       / >🎓      " + mainColor + " " + B_YELLOW + " RECORD  " + B_GREEN + "      📅 수강 기간 : " + RESET + startStr + " ~ " + endStr + "\n" +
                mainColor + "  ┃ " + B_YELLOW + "      * .  +  ﾟ * " + mainColor + "           \n" +
                mainColor + "  ╰━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET;
    }

    @Override
    public String toString() {
        return toString(false);
    }

    // --- Getter & Setter ---
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
    public Long getProgressRate() { return progressRate; }
    public void setProgressRate(Long progressRate) { this.progressRate = progressRate; }
}