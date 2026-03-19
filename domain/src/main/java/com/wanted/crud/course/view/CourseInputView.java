package com.wanted.crud.course.view;

import com.wanted.crud.course.controller.CourseController;
import com.wanted.crud.course.model.dto.CourseDTO;

import java.util.List;
import java.util.Scanner;

public class CourseInputView {

    private final CourseController controller;
    private final CourseOutputView outputView;
    private final Scanner sc = new Scanner(System.in);

    public CourseInputView(CourseController controller, CourseOutputView outputView) {
        this.controller = controller;
        this.outputView = outputView;
    }

    // CourseInputView.java 내부
    public void viewAllCourses() {
        outputView.printMessage("\n--- 📚 전체 강좌 목록 조회 ---");

        // 파라미터 없이 컨트롤러의 전체 조회 메서드 호출
        List<CourseDTO> allCourses = controller.selectAllCourses();

        // 기존에 만들어둔 출력 메서드 재사용 (출력 형식이 다르다면 printCourses2 사용)
        outputView.printCourses(allCourses);
    }


    public void viewMyCourse() {
        outputView.printMessage("\n--- 내 강좌 목록 조회 ---");
//        System.out.print("조회하실 본인의 강사 번호(ID)를 입력해주세요: ");
//        long id = 0L;
//
//        try {
//            id = Long.parseLong(sc.nextLine().trim());
//        } catch (NumberFormatException e) {
//            System.out.println("[오류] 숫자로 된 강사 번호를 입력해주세요.");
//            return; // 입력 오류 시 종료
//        }

        long id = com.wanted.crud.userView.Application_course.loggedInUserPk;

        // 입력받은 파라미터 값(id)을 컨트롤러로 전달!
        List<CourseDTO> findMyCourse = controller.selectCourse(id);

        outputView.printCourses(findMyCourse);
    }

    public void createCourse() {
        long id = 0L;
        System.out.print("생성할 강좌 제목을 입력해주세요 : ");
        String title = sc.nextLine();

        System.out.print("과정 설명을 입력해주세요 : ");
        String description = sc.nextLine();

        System.out.print("강좌 가격을 입력해주세요 : ");
        int price = Integer.parseInt(sc.nextLine());
        Long result = controller.createCourse(title, description, price, id);

        if (result != null && result > 0) {
            outputView.printSuccess("과정 등록 성공! 생성된 과정 ID : " + result);
        } else {
            outputView.printError("과정 등록 실패");
        }
    }

    // title, video_url, material_url, created_at, course_id
    public void createSection() {
        long id = 0L;
        System.out.print("생성할 강의 제목을 입력해주세요 : ");
        String title = sc.nextLine();

        System.out.print("강의 URL을 입력해주세요 : ");
        String video_url = sc.nextLine();

        System.out.print("강의 자료 URL을 입력해주세요 : ");
        String material_url = sc.nextLine();

        Long result = controller.createSection(title, video_url, material_url, id);

        if (result != null && result > 0) {
            outputView.printSuccess("강의 등록 성공! 생성된 과정 ID : " + result);
        } else {
            outputView.printError("강의 등록 실패");
        }
    }

    // 내 강좌 삭제
//    private void deleteCourse() {
//        System.out.print("삭제할 강좌 번호를 입력해주세요 : ");
//        int course_id = Integer.parseInt(sc.nextLine());
//        boolean result = controller.deleteCourse();
//
//        if(deleteCourse(); == null){
//            outputView.printMessage("");
//        }
//
//
//    }


}