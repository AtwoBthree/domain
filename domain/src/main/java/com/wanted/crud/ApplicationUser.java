package com.wanted.crud;



import com.wanted.crud.global.config.JDBCTemplate;
import com.wanted.crud.user.controller.InstructorController;
import com.wanted.crud.user.controller.StudentController;
import com.wanted.crud.user.controller.UserController;
import com.wanted.crud.user.model.service.InstructorService;
import com.wanted.crud.user.model.service.StudentService;
import com.wanted.crud.user.model.service.UserService;
import com.wanted.crud.user.view.*;

import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationUser {
    public static void main(String[] args) {
        try (Connection con = JDBCTemplate.getConnection()) {
            System.out.println("✅==데이터베이스 연결 성공==");
            JDBCTemplate.printConnectionStatus();

            // ===== 서비스 생성 =====
            UserService userService = new UserService(con);
            StudentService studentService = new StudentService(con);
            InstructorService instructorService = new InstructorService(con);


            // ===== 컨트롤러 생성 =====
            UserController userController = new UserController(userService);
            StudentController studentController = new StudentController(studentService);
            var instructorController = new InstructorController(instructorService);


            // ===== 아웃풋뷰 생성 =====
            UserOutputView userOutput = new UserOutputView();
            StudentOutputView studentOutput = new StudentOutputView();
            InstructorOutputView instructorOutput = new InstructorOutputView();
            AdminOutputView adminOutput = new AdminOutputView();

            StudentInputView studentInput = new StudentInputView(studentController, studentOutput);
            InstructorInputView instructorInput = new InstructorInputView(instructorController, instructorOutput);
            AdminInputView adminInput = new AdminInputView(userController, studentController,instructorController,adminOutput);

                    // ===== 인풋뷰 생성 =====
            UserInputView userInput = new UserInputView(
                    userController,
                    studentController,
                    instructorController,
                    userOutput,
                    studentOutput,
                    instructorOutput,
                    adminOutput,
                    adminInput,
                    studentInput,
                    instructorInput
            );


            userInput.displayMainMenu();
        } catch (SQLException e) {
            throw new RuntimeException("🚨데이터베이스 연결 실패");
        }

    }
}
