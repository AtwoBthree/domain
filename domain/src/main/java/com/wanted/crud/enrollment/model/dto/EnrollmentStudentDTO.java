package com.wanted.crud.enrollment.model.dto;


public class EnrollmentStudentDTO {

    private Long courseId;
    private String courseTitle;

    private Long userNo;
    private String userId;
    private String userName;
    private String userPhoneNumber;

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

    @Override
    public String toString() {
        return "EnrollmentStudentDTO{" +
                "courseId=" + courseId +
                ", courseTitle='" + courseTitle + '\'' +
                ", userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                '}';
    }
}
