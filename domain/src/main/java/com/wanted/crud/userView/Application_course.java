package com.wanted.crud.userView;

import com.wanted.crud.course.controller.CourseController;
import com.wanted.crud.course.model.service.CourseService;
import com.wanted.crud.course.view.CourseInputView;
import com.wanted.crud.course.view.CourseOutputView;
import com.wanted.crud.global.config.JDBCTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner; // ★ Scanner 임포트 추가

public class Application_course {

    // 전역 보관함
    public static CourseInputView globalInputView;
    public static long loggedInUserPk = 0L;     // 아이디 보관함.

    public static void main(String[] args) {
        try (Connection con = JDBCTemplate.getConnection()) {

            System.out.println("✅ 데이터베이스 연결 성공!!");

            // 객체 조립 진행
            CourseService service = new CourseService(con);
            CourseController controller = new CourseController(service);
            CourseOutputView outputView = new CourseOutputView();
            CourseInputView inputView = new CourseInputView(controller, outputView);

            // 조립이 끝난 뷰를 전역 변수에 저장
            globalInputView = inputView;

            // ❌ 삭제: 프로그램 켜지자마자 강좌 조회하던 테스트 코드는 지웁니다.
            // inputView.viewMyCourse();

            // ★ 추가: Scanner를 생성하고 강사 메뉴를 화면에 띄웁니다!
            Scanner sc = new Scanner(System.in);
            MainInput mainmenu = new MainInput();
            mainmenu.startApp();
        } catch (SQLException e) {
            System.err.println("🚨 데이터베이스 연결 실패...");
        } finally {
            JDBCTemplate.close();
        }
    }
}