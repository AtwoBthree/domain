package com.wanted.crud;

import com.wanted.crud.course.controller.UserController;
import com.wanted.crud.course.model.service.UserService;
import com.wanted.crud.course.view.UserInputView;
import com.wanted.crud.course.view.UserOutputView;
import com.wanted.crud.global.config.JDBCTemplate;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        try (Connection con = JDBCTemplate.getConnection()) {
            System.out.println("✅==데이터베이스 연결 성공==");
            JDBCTemplate.printConnectionStatus();

            UserService service = new UserService(con);
            UserController controller = new UserController(service);
            UserOutputView outputView = new UserOutputView();
            UserInputView inputView = new UserInputView(controller, outputView);

            inputView.displayMainMenu();
        } catch (SQLException e) {
            throw new RuntimeException("🚨데이터베이스 연결 실패");
        }

    }
}
