package com.wanted.crud.enrollment.model.service;

import com.wanted.crud.course.model.dto.CourseDTO;
import com.wanted.crud.enrollment.model.dao.EnrollmentDAO;
import com.wanted.crud.enrollment.model.dto.EnrollmentDTO;
import com.wanted.crud.payment.model.dao.PaymentDAO;
import com.wanted.crud.user.model.dto.UserDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class EnrollmentService {
    private final EnrollmentDAO enrollmentDAO;
    private final Connection connection;

    public EnrollmentService(Connection connection) {
        this.connection = connection;
        this.enrollmentDAO = new EnrollmentDAO(connection);
    }

    public Long saveEnrollment(EnrollmentDTO newEnrollment) {
        try {
            return enrollmentDAO.save(newEnrollment);
        } catch (SQLException e) {
            throw new RuntimeException("수강 신청 중 Error 발생!!! 🚨" + e);
        }
    }



}


