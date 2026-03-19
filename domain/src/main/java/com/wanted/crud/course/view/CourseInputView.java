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

    public List<Long> findCourseId(Long instructorId){
        return controller.findCoursesId(instructorId);
    }

    // CourseInputView.java 내부
    public void viewAllCourses() {
        outputView.printMessage("\n--- 📚 전체 강좌 목록 조회 ---");

        // 파라미터 없이 컨트롤러의 전체 조회 메서드 호출
        List<CourseDTO> allCourses = controller.selectAllCourses();

        // 기존에 만들어둔 출력 메서드 재사용 (출력 형식이 다르다면 printCourses2 사용)
        outputView.printCourses(allCourses);
    }


    public void viewMyCourse(Long id) {
        outputView.printMessage("\n--- 내 강좌 목록 조회 ---");
        List<CourseDTO> findMyCourse = controller.selectCourse(id);

        outputView.printCourses(findMyCourse);
    }

    public void createCourse(Long id) {
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
    public void createSection(Long id) {
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
    public void deleteCourse(Long instructorId) {
        outputView.printMessage("\n--- 🗑️ 강좌 삭제 ---");
        System.out.print("삭제할 본인의 강좌 번호를 정확히 입력해주세요 : ");

        long targetCourseId;

        try {
            targetCourseId = Long.parseLong(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            outputView.printError("[오류] 문자가 아닌 숫자로 된 강좌 번호를 입력해주세요.");
            return;
        }


        boolean result = controller.deleteCourseById(targetCourseId, instructorId);

        if (result) {
            outputView.printSuccess("✅ " + targetCourseId + "번 강좌와 강의가 삭제되었습니다!");
        } else {
            outputView.printError("❌ 강좌 삭제 실패 : 해당 번호의 강좌가 없거나, 본인의 강좌가 아닙니다.");
        }
    }
}