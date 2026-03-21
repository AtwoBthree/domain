package com.wanted.crud.user.view;

import com.wanted.crud.user.controller.UserController;
import com.wanted.crud.user.model.dto.UserDTO;
import java.util.List;
import java.util.Scanner;

public class AdminInputView {

    private final UserController controller;
    private final UserOutputView outputView;
    private final Scanner sc = new Scanner(System.in);

    public AdminInputView(UserController controller, UserOutputView outputView) {
        this.controller = controller;
        this.outputView = outputView;
    }

    // 관리자의 강사 이름 조회
    public void searchInstructor() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        outputView.printMessage("🔍 [ 관리자 전용: 강사 이름으로 검색 ]");

        System.out.print("▶ 조회할 강사의 이름을 입력하세요: ");
        String name = inputString();


        List<UserDTO> instructorList = controller.findInstructorByName(name);

        // 결과 출력
        if (instructorList != null && !instructorList.isEmpty()) {
            outputView.printMessage("📊 '" + name + "' 검색 결과 리포트");
            outputView.printInstructors2(instructorList);
        } else {
            outputView.printError("🚨 [조회결과없음] '" + name + "' 강사가 존재하지 않습니다.");
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }



    private String inputString() {
        return sc.nextLine();
    }
}