package com.wanted.crud.course.model.dto;

import java.util.Date;

public class SectionDTO {

    private Long sectionId;
    private String title;
    private String videoUrl;
    private String materialUrl;
    private Date createdAt;
    private Long courseId;

    public SectionDTO() {}

    public SectionDTO(Long sectionId, String title, String videoUrl, String materialUrl, Date createdAt, Long courseId) {
        this.sectionId = sectionId;
        this.title = title;
        this.videoUrl = videoUrl;
        this.materialUrl = materialUrl;
        this.createdAt = createdAt;
        this.courseId = courseId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "SectionDTO{" +
                "sectionId=" + sectionId +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", materialUrl='" + materialUrl + '\'' +
                ", createdAt=" + createdAt +
                ", courseId=" + courseId +
                '}';
    }
}
