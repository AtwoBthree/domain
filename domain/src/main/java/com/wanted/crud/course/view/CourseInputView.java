package com.wanted.crud.course.view;

import com.wanted.crud.course.controller.CourseController;
import com.wanted.crud.course.model.dto.CourseDTO;
import com.wanted.crud.course.model.dto.SectionDTO;

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

    public List<Long> findCourseId(Long instructorId) {
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

        int price = 0;
        while (true){
            System.out.print("강좌 가격을 입력해주세요 : ");
            try {
                price = Integer.parseInt(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("[오류] 가격은 숫자로 입력해주세요.");
            }
        }
        Long result = controller.createCourse(title, description, price, id);

        if (result != null && result > 0) {
            outputView.printSuccess("강좌 등록 성공! 생성된 과정 ID : " + result);
        } else {
            outputView.printError("강좌 등록 실패");
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
    public void deleteCourse(Long courseIdToDelete, Long instructorId) {
        outputView.printMessage("\n--- 🗑️ 강좌 삭제 ---");
        boolean result = controller.deleteCourseById(courseIdToDelete, instructorId);
        if (result) {
            outputView.printSuccess("✅ " + courseIdToDelete + "번 강좌와 하위 강의가 모두 삭제되었습니다");
        } else {
            outputView.printError("❌ 강좌 삭제 실패");
        }
    }


    public void updateCourseView(Long instructorId) { // ✨ throws 삭제
        outputView.printMessage("\n--- 📝 내 강좌 정보 수정 ---");
        viewMyCourse(instructorId);

        long courseId = 0L;
        while (true) {
            System.out.print("수정할 강좌 번호를 입력하세요 : ");
            try {
                courseId = Long.parseLong(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("[오류] 강좌번호는 숫자로 입력해주세요.");
            }
        }

        System.out.print("새로운 강좌 제목 : ");
        String title = sc.nextLine();

        System.out.print("새로운 강좌 설명 : ");
        String description = sc.nextLine();

        Long price = 0L;
        while (true) {
            System.out.print("새로운 가격 : ");
            try {
                price = Long.parseLong(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("[오류] 가격은 숫자로 입력해주세요.");
            }
        }

        boolean isSuccess = controller.updateCourse(courseId, title, description, price, instructorId);

        if (isSuccess) {
            outputView.printSuccess("✅ " + courseId + "번 강좌가 수정되었습니다.");
        } else {
            outputView.printError("❌ 수정 실패");
        }
    }

    // 내 강의 수정
    public void updateSectionView(Long instructorId) {
        outputView.printMessage("\n--- 📺 강의 내용 수정 (2단계 선택) ---");

        // [1단계] 내 강좌 목록 조회 및 선택
        viewMyCourse(instructorId);
        System.out.print("수정할 강의가 포함된 [강좌 번호]를 입력하세요: ");
        long courseId = Long.parseLong(sc.nextLine().trim());

        // [2단계] 선택한 강좌의 상세 강의 목록 조회 (새로 추가될 로직)
        List<SectionDTO> sections = controller.selectSectionsByCourseId(courseId);

        if (sections == null || sections.isEmpty()) {
            outputView.printError("해당 강좌에 등록된 강의가 없습니다.");
            return;
        }

        outputView.printMessage("\n--- [" + courseId + "]번 강좌의 상세 강의 목록 ---");
        for (SectionDTO s : sections) {
            System.out.println("강의번호: " + s.getSectionId() + " | 제목: " + s.getTitle());
        }

        // [3단계] 실제 수정할 강의 번호 선택 및 데이터 입력
        System.out.print("\n수정할 [강의 번호]를 입력하세요: ");
        long sectionId = Long.parseLong(sc.nextLine().trim());

        System.out.print("새로운 강의 제목: ");
        String title = sc.nextLine();
        System.out.print("새로운 영상 URL: ");
        String videoUrl = sc.nextLine();
        System.out.print("새로운 자료 URL: ");
        String materialUrl = sc.nextLine();

        // 컨트롤러 호출 (instructorId를 같이 보내서 본인 확인)
        boolean isSuccess = controller.updateSection(sectionId, title, videoUrl, materialUrl, instructorId);

        if (isSuccess) {
            outputView.printSuccess("✅ 강의 수정 완료!");
        } else {
            outputView.printError("❌ 수정 실패: 권한이 없거나 번호가 잘못되었습니다.");
        }
    }
}
