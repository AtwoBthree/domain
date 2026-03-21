package com.wanted.crud.course.model.dto;

import java.util.Date;

public class CourseMyStudentDTO {

    private Long courseId;
    private String title;
    private Long studentId;
    private String userName;
    private Long progressRate;
    private Date startDate;
    private Date endDate;
    private Boolean status;

    public CourseMyStudentDTO() {}

    public CourseMyStudentDTO(Long courseId, String title, Long studentId, String userName, Long progressRate, Date startDate, Date endDate, Boolean status){
        this.courseId = courseId;
        this.title = title;
        this.studentId = studentId;
        this.userName =  userName;
        this.progressRate = progressRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getter & Setter
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public Long getProgressRate() { return progressRate; }
    public void setProgressRate(Long progressRate) { this.progressRate = progressRate; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    @Override
    public String toString() {
        return  "\n" +
                "╔════════════════════════ [ Course Student Info ] ════════════════════════ \n" +
                "  ▣ Course ID    : " + courseId + "\n" +
                "  ▣ Course Title : " + title + "\n" +
                "  ▣ Student ID   : " + studentId + "\n" +
                "  ▣ Student Name : " + userName + "\n" +
                "  ▣ Progress     : " + progressRate + "%\n" +
                "  ▣ Period       : " + startDate + " ~ " + (endDate != null ? endDate : "Ongoing") + "\n" +
                "  ▣ Status       : " + (status != null && status ? "✅ Active" : "❌ Inactive") + "\n" +
                "╚═════════════════════════════════════════════════════════════════════════";
    }

}


