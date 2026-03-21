package com.wanted.crud.course.view;

import com.wanted.crud.course.controller.CourseController;
import com.wanted.crud.course.model.dto.CourseDTO;
import com.wanted.crud.course.model.dto.CourseMyStudentDTO;
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

    public void viewAllCourses() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        outputView.printMessage("✨ [ 전체 강좌 목록 조회 ] ✨");
        List<CourseDTO> allCourses = controller.selectAllCourses();
        outputView.printCourses(allCourses);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    public void viewMyCourse(Long id) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        outputView.printMessage("📂 [ 내 강좌 목록 조회 ]");
        List<CourseDTO> findMyCourse = controller.selectCourse(id);
        outputView.printCourses(findMyCourse);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    public void createCourse(Long id) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        outputView.printMessage("🆕 [ 새 강좌 등록 시작 ]");
        System.out.print("▶ 강좌 제목 입력 : ");
        String title = sc.nextLine();

        System.out.print("▶ 과정 설명 입력 : ");
        String description = sc.nextLine();

        int price = 0;
        while (true) {
            System.out.print("▶ 강좌 가격 입력 : ");
            try {
                price = Integer.parseInt(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                outputView.printError("🚨 [입력오류] 가격은 숫자만 입력 가능합니다.");
            }
        }
        Long result = controller.createCourse(title, description, price, id);

        if (result != null && result > 0) {
            outputView.printSuccess("✅ 강좌 등록 완료! (ID: " + result + ")");
        } else {
            outputView.printError("❌ 강좌 등록에 실패했습니다.");
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    public void createSection(Long instructorId) {
        viewMyCourse(instructorId);

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        outputView.printMessage("🎬 [ 강의(Section) 추가 ]");
        System.out.print("▶ 추가할 강좌 번호 입력: ");
        long courseId = Long.parseLong(sc.nextLine().trim());

        System.out.print("▶ 강의 제목 입력 : ");
        String title = sc.nextLine();

        System.out.print("▶ 영상 URL 입력 : ");
        String videoUrl = sc.nextLine();

        System.out.print("▶ 학습 자료 URL 입력 : ");
        String materialUrl = sc.nextLine();

        Long result = controller.createSection(title, videoUrl, materialUrl, courseId);

        if (result != null && result > 0) {
            outputView.printSuccess("✅ 강의가 성공적으로 추가되었습니다.");
        } else {
            outputView.printError("❌ 강의 추가 실패");
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    public void deleteCourse(Long courseIdToDelete, Long instructorId) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        outputView.printMessage("🗑️ [ 강좌 삭제 진행 ]");
        boolean result = controller.deleteCourseById(courseIdToDelete, instructorId);
        if (result) {
            outputView.printSuccess("✅ " + courseIdToDelete + "번 강좌와 모든 강의가 제거되었습니다.");
        } else {
            outputView.printError("❌ 강좌 삭제 실패 (ID를 확인하세요)");
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    public void updateCourseView(Long instructorId) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        outputView.printMessage("📝 [ 강좌 정보 수정 ]");
        viewMyCourse(instructorId);

        long courseId = 0L;
        while (true) {
            System.out.print("▶ 수정할 강좌 번호 선택 : ");
            try {
                courseId = Long.parseLong(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                outputView.printError("🚨 [입력오류] 강좌 번호는 숫자로 입력해주세요.");
            }
        }

        System.out.print("▶ 새 제목 : ");
        String title = sc.nextLine();

        System.out.print("▶ 새 설명 : ");
        String description = sc.nextLine();

        Long price = 0L;
        while (true) {
            System.out.print("▶ 새 가격 : ");
            try {
                price = Long.parseLong(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                outputView.printError("🚨 [입력오류] 가격은 숫자만 가능합니다.");
            }
        }

        boolean isSuccess = controller.updateCourse(courseId, title, description, price, instructorId);

        if (isSuccess) {
            outputView.printSuccess("✅ " + courseId + "번 강좌 정보가 갱신되었습니다.");
        } else {
            outputView.printError("❌ 수정 실패: 권한 또는 번호를 확인하세요.");
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    // 강의 수정
    public void updateSectionView(Long instructorId) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        outputView.printMessage("📺 [ 강의 상세 내용 수정 ]");

        List<CourseDTO> myCourses = controller.selectCourse(instructorId);

        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│           내 강좌 및 소속 강의 정보 리스트         │");
        System.out.println("├────────────────────────────────────────────────────┤");
        for (CourseDTO course : myCourses) {
            System.out.printf("│ [%d] %-38s │\n", course.getCourseId(), course.getTitle());

            List<SectionDTO> sections = controller.selectSectionsByCourseId(course.getCourseId());

            if (sections != null && !sections.isEmpty()) {
                System.out.print("│  └─ 🎬 강의번호: ");
                for (int i = 0; i < sections.size(); i++) {
                    System.out.print(sections.get(i).getSectionId() + (i < sections.size() - 1 ? ", " : ""));
                }
                System.out.println("                      │");
            } else {
                System.out.println("│  └─ 🔘 등록된 강의 없음                            │");
            }
        }
        System.out.println("└────────────────────────────────────────────────────┘");

        System.out.print("\n▶ 수정할 강의 번호 입력: ");
        long sectionId = Long.parseLong(sc.nextLine().trim());

        System.out.print("▶ 새 강의 제목: ");
        String title = sc.nextLine();
        System.out.print("▶ 새 영상 URL: ");
        String videoUrl = sc.nextLine();
        System.out.print("▶ 새 자료 URL: ");
        String materialUrl = sc.nextLine();

        boolean isSuccess = controller.updateSection(sectionId, title, videoUrl, materialUrl, instructorId);

        if (isSuccess) {
            outputView.printSuccess("✅ 강의 정보 수정 완료!");
        } else {
            outputView.printError("❌ 수정 실패: 입력 정보를 확인하세요.");
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    // 강사별 강좌 조회
    public void viewInstructorCourses() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        outputView.printMessage("🔍 [ 강사별 강좌 조회 ]");
        System.out.print("▶ 조회할 강사 ID 입력: ");

        long targetInstructorId;
        try {
            targetInstructorId = Long.parseLong(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            outputView.printError("🚨 [입력오류] 유효한 숫자를 입력해주세요.");
            return;
        }

        List<CourseDTO> courses = controller.getCoursesByInstructor(targetInstructorId);

        if (courses == null || courses.isEmpty()) {
            outputView.printError("❌ 등록된 강좌가 없거나 존재하지 않는 강사입니다.");
        } else {
            outputView.printMessage("\n📊 [ 강사 ID: " + targetInstructorId + " ] 님의 강좌 데이터");
            outputView.printCourses(courses);
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    public void adminManageCourseDetail() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        outputView.printMessage("🛠️ [ 관리자 모드: 강좌 관리 ]");

        long courseId;
        try {
            System.out.print("▶ 대상 강좌 ID 입력: ");
            courseId = Long.parseLong(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            outputView.printError("🚨 [입력오류] 숫자만 입력 가능합니다.");
            return;
        }

        System.out.println("\n[1] 강좌 삭제  [2] 정보 수정  [0] 취소");
        System.out.print("▶ 메뉴 선택: ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            outputView.printError("🚨 [입력오류] 올바른 메뉴 번호를 선택하세요.");
            return;
        }

        if (choice == 1) {
            System.out.print("⚠️ 정말 삭제하시겠습니까? (Y/N): ");
            if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
                if (controller.deleteCourseByAdmin(courseId)) {
                    outputView.printSuccess("✅ 강좌가 관리자 권한으로 삭제되었습니다.");
                } else {
                    outputView.printError("❌ 삭제 실패: 대상 번호를 확인하세요.");
                }
            } else {
                System.out.println("🚫 삭제 작업이 취소되었습니다.");
            }

        } else if (choice == 2) {
            System.out.print("▶ 새 제목: ");
            String title = sc.nextLine();

            System.out.print("▶ 새 설명: ");
            String description = sc.nextLine();

            long price = 0;
            try {
                System.out.print("▶ 새 가격: ");
                price = Long.parseLong(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                outputView.printError("🚨 [입력오류] 가격은 숫자만 입력 가능합니다.");
                return;
            }

            System.out.print("▶ 새 상태 (1:활성 / 0:비활성): ");
            String status = sc.nextLine().trim();

            if (controller.updateCourseByAdmin(courseId, title, description, price, status)) {
                outputView.printSuccess("✅ 강좌 정보가 관리자 권한으로 갱신되었습니다.");
            } else {
                outputView.printError("❌ 수정 실패: 대상 번호를 확인하세요.");
            }

        } else if (choice == 0) {
            System.out.println("🔙 이전 메뉴로 돌아갑니다.");
        } else {
            outputView.printError("❌ 잘못된 입력입니다.");
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }



    public Long getPrice(Long courseId) {
        return controller.getPirce(courseId);
    }

    // 내 강좌를 수강 중인 전체 학생 현황 조회
    public void studentStatusScreen(Long id) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        //outputView.printMessage("📂 [ 내 강좌 목록 조회 ]");
        List<CourseMyStudentDTO> findStudent = controller.selectFindMyStudent(id);
        outputView.printMyStudent(findStudent);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }




}