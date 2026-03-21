package com.wanted.crud.course.model.dto;

import java.util.Date;

public class CourseDTO {

    private Long courseId;
    private String title;
    private String description;
    private Long price;
    private String status;
    private Date createdAt;
    private Long instructorId;

    public CourseDTO(Long courseId, String title, String description, Long price, String status, Date createdAt, Long instructorId) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.instructorId = instructorId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }


    // 한글 길이를 계산해 오른쪽 여백을 채워주는 메서드
    private String padRight(String text) {
        int targetWidth = 57; // 박스 안쪽의 고정 너비
        int width = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // 한글은 콘솔에서 2칸을 차지하므로 +2
            if (c >= '가' && c <= '힣') {
                width += 2;
            } else {
                width += 1; // 영어, 숫자, 공백, 기호 등은 1칸
            }
        }

        int padding = targetWidth - width;
        if (padding > 0) {
            return text + " ".repeat(padding); // 모자란 길이만큼 공백을 반복해서 붙임
        }
        return text;
    }


    @Override
    public String toString() {
        // 공개 여부를 이모지와 함께 텍스트로 변환
        String statusText = "1".equals(status) ? "🔹 공개" : "❌ 비공개";

        return "\n" +
                "  ╔══════════════════════════════════════════════════\n" +
                "  ║   ____ ___  _   _ ____  ____  _____\n" +
                "  ║  / ___/ _ \\| | | |  _ \\/ ___|| ____|\n" +
                "  ║ | |  | | | | | | | |_) \\___ \\|  _|\n" +
                "  ║ | |__| |_| | |_| |  _ < ___) | |___\n" +
                "  ║  \\____\\___/ \\___/|_| \\_\\____/|_____|\n" +
                "  ╠══════════════════════════════════════════════════\n" +
                "  ║  /\\_/\\    [ NO." + String.format("%04d", courseId) + " ]\n" +
                "  ║ ( o.o )    📚 강좌 제목 : " + title +
                "  ║  > ^ <     📃 강좌 설명 : " + description + "\n" +
                "  ║ /  _  \\   💰 강좌 가격 : " + String.format("%,d원", price) + "\n" +
                "  ║( ( | | )   ✅ 개방 상태 : " + statusText + "\n" +
                "  ║ \\| | | |/ 📅 등 록 일 : " + createdAt + "\n" +
                "  ║            👨‍🏫 강사 번호 : " + instructorId + "\n" +
                "  ╚══════════════════════════════════════════════════";
    }
}
