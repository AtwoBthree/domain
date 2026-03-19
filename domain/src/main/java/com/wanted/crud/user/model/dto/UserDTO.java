package com.wanted.crud.user.model.dto;

import java.util.Date;

public class UserDTO {
    private Long userNo;
    private String userId;
    private String userPassword;
    private String userName;
    private String userPhoneNumber;
    private Long userPrice;
    private String userRole;
    private Date createdAt;
    private boolean status;

    @Override
    public String toString() {
        return "UserDTO{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userPrice=" + userPrice +
                ", userRole='" + userRole + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Long getUserPrice() {
        return userPrice;
    }

    public void setUserPrice(Long userPrice) {
        this.userPrice = userPrice;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserDTO(Long userNo, String userId, String userPassword, String userName, String userPhoneNumber, Long userPrice, String userRole, Date createdAt, boolean status) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userPrice = userPrice;
        this.userRole = userRole;
        this.createdAt = createdAt;
        this.status = status;
    }

    // 로그인 전용 생성자입니다.
    public UserDTO(Long userNo, String userId, String password, String role) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPassword = password;
        this.userRole = role;
    }


    public UserDTO(Long userNo, String userId, String password, String role, String userPhoneNumber) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPassword = password;
        this.userRole = role;
    }

    // 아이디, 비번 찾기 생성자
    public UserDTO(String userId, String password) {
        this.userId = userId;
        this.userPassword = password;
        //this.userRole = role;
    }







}
