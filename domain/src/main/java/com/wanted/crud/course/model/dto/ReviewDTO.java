package com.wanted.crud.course.model.dto;

import java.util.Date;

public class ReviewDTO {

    private Long reviewId;
    private Long rating;
    private Date createdAt;
    private Long courseId;
    private Long studentId;

    public ReviewDTO(Long reviewId, Long rating, Date createdAt, Long courseId, Long studentId) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.createdAt = createdAt;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewId=" + reviewId +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                '}';
    }
}
