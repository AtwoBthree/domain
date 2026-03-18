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

    @Override
    public String toString() {
        return "CourseSectionDTO{" +
                "courseId=" + courseId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", instructorId=" + instructorId +
                ", sections=" + sections +
                '}';
    }
}