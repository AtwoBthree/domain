package com.wanted.crud.course.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseSectionDTO {

    private Long courseId;
    private String title;
    private String description;
    private Long price;
    private String status;
    private Date createdAt;
    private Long instructorId;

    // 강좌에 포함된 섹션 목록을 저장하는 리스트
    private List<SectionDTO> sections = new ArrayList<>();

    public CourseSectionDTO() {}

    public CourseSectionDTO(Long courseId, String title, String description, Long price, String status, Date createdAt, Long instructorId) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.instructorId = instructorId;
    }

    // Getter & Setter
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getPrice() { return price; }
    public void setPrice(Long price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Long getInstructorId() { return instructorId; }
    public void setInstructorId(Long instructorId) { this.instructorId = instructorId; }

    public List<SectionDTO> getSections() { return sections; }
    public void setSections(List<SectionDTO> sections) { this.sections = sections; }

    // ✨ 에러의 원인이었던 여백 계산기(마법봉)를 이 클래스 안에도 추가해 줍니다!
    private String padRight(String text) {
        int targetWidth = 57; // 박스 안쪽의 고정 너비
        int width = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= '가' && c <= '힣') {
                width += 2; // 한글은 2칸 차지
            } else {
                width += 1; // 나머지는 1칸 차지
            }
        }

        int padding = targetWidth - width;
        if (padding > 0) {
            return text + " ".repeat(padding); // 모자란 칸만큼 공백 채우기
        }
        return text;
    }

    @Override
    public String toString() {
        // 제목과 설명이 너무 길어질 때를 대비 (오른쪽 벽이 없으므로 조금 더 길게 허용)
        String safeTitle = (title != null && title.length() > 25) ? title.substring(0, 22) + "..." : title;
        String safeDesc = (description != null && description.length() > 25) ? description.substring(0, 22) + "..." : description;

        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        sb.append("  ╔═════════════════════════════════════════════════════════\n");
        sb.append("  ║      +----+  +----+  +----+      +----+  +----+ \n");
        sb.append("  ║      | 강 |  | 좌 |  | 와 |  ⭐   | 강 |  | 의 | \n");
        sb.append("  ║      +----+  +----+  +----+      +----+  +----+ \n");
        sb.append("  ╠═════════════════════════════════════════════════════════\n");

        // 귀여운 강아지 조수와 상세 정보 (오른쪽 ║ 제거)
        sb.append("  ║   / \\__\n");
        sb.append("  ║  (  o  \\_    [ NO.").append(String.format("%04d", courseId)).append(" ]\n");
        sb.append("  ║   /      O   🔹 강좌 제목 : ").append(safeTitle).append("\n");
        sb.append("  ║  /   (___/   📃 강좌 설명 : ").append(safeDesc).append("\n");
        sb.append("  ║ /_/ /__|_|   💰 강좌 가격 : ").append(String.format("%,d원", price)).append("\n");
        sb.append("  ║ (__) (__)    ✅ 개방 상태 : ").append("1".equals(status) ? "공개" : "비공개").append("\n");
        sb.append("  ║     ~        📅 등 록 일  : ").append(createdAt).append("\n");
        sb.append("  ║              👨‍🏫 강사 번호 : ").append(instructorId).append("\n");

        // 세부 강의 목록 섹션
        sb.append("  ╠═════════════════════════════════════════════════════════\n");
        sb.append("  ║ 📺 [ 포함된 세부 강의 목록 ]\n");

        if (sections == null || sections.isEmpty()) {
            sb.append("  ║  ❌  텅~ (아직 등록된 강의가 없습니다.)\n");
        } else {
            int count = 1;
            for (Object section : sections) {
                String secStr = String.valueOf(section);
                // 내부 DTO 문자열 정제
                if (secStr.contains("{")) {
                    secStr = secStr.substring(secStr.indexOf("{") + 1, secStr.lastIndexOf("}"));
                }
                sb.append("  ║    🎬 ").append(count).append("강 : ").append(secStr).append("\n");
                count++;
            }
        }
        sb.append("  ╚═════════════════════════════════════════════════════════\n");

        return sb.toString();
    }

}