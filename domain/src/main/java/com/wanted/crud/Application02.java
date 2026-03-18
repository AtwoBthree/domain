package com.wanted.crud;

import com.wanted.crud.global.config.JDBCTemplate;

import com.wanted.crud.user.controller.UserController;
import com.wanted.crud.user.model.service.UserService;
import com.wanted.crud.user.view.UserInputView;
import com.wanted.crud.user.view.UserOutputView;

import java.sql.Connection;
import java.sql.SQLException;

public class Application02 {

    public static void main(String[] args) {

        try (Connection con = JDBCTemplate.getConnection()) {

            System.out.println("✅==데이터베이스 연결 성공==");
            JDBCTemplate.printConnectionStatus();

            // ===== Service =====
            UserService userService = new UserService(con);

            // ===== Controller =====
            UserController userController = new UserController(userService);

            // ===== View =====
            UserOutputView outputView = new UserOutputView();
            UserInputView inputView = new UserInputView(
                    userController,
                    outputView
            );

            // 실행
            inputView.displayRegister();

        } catch (SQLException e) {
            throw new RuntimeException("🚨데이터베이스 연결 실패", e);
        }
    }
}