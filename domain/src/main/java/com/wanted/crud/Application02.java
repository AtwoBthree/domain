package com.wanted.crud;



import com.wanted.crud.course.controller.CourseController;
import com.wanted.crud.course.model.service.CourseService;
import com.wanted.crud.course.view.CourseInputView;
import com.wanted.crud.course.view.CourseOutputView;
import com.wanted.crud.global.config.JDBCTemplate;
import com.wanted.crud.settlement.controller.SettlementController;
import com.wanted.crud.settlement.view.SettlementInputView;
import com.wanted.crud.userView.MainInput;
import com.wanted.crud.userView.MainView;

import java.sql.Connection;
import java.sql.SQLException;

public class Application02 {
    public static void main(String[] args) {
        try (Connection con = JDBCTemplate.getConnection()) {
            System.out.println("✅==데이터베이스 연결 성공==");
            JDBCTemplate.printConnectionStatus();

        } catch (SQLException e) {
            throw new RuntimeException("🚨데이터베이스 연결 실패");
        }

    }

}