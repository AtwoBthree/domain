package com.wanted.crud.enrollment.view;

import com.wanted.crud.enrollment.controller.EnrollmentController;
import com.wanted.crud.enrollment.model.dto.EnrollmentStudentDTO;
import com.wanted.crud.payment.controller.PaymentController;
import com.wanted.crud.payment.view.PaymentOutputView;

import java.util.List;
import java.util.Scanner;

public class EnrollmentInputView {

    private final EnrollmentController controller;
    private final EnrollmentOutputView outputView;

    public EnrollmentInputView(EnrollmentController controller, EnrollmentOutputView outputView) {
        this.controller = controller;
        this.outputView = outputView;
    }

    public boolean studentCoursePage(Long studentId) {
        return controller.studentCoursePage(studentId);

    }

    public boolean createEnrollment(Long studentId, Long courseId) {
        if(controller.createEnrollment(studentId, courseId)) {
            System.out.println("수강신청 완료!");
            return true;
        } else {
            System.out.println("수강신청 실패!");
        }
        return false;
    }

    private String inputString() {
        return sc.nextLine();
    }

    private int inputInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }

    // 중복 수강인지 아닌지 체크.
    public boolean isStudyingCourse(long studentId, long courseId) {
        return controller.isStudyingCoruse(studentId, courseId);
    }


    private final Scanner sc = new Scanner(System.in);


    public boolean updateEnrollmentProgress(Long studentId, Long courseId){
        return controller.updateEnrollmentProgress(studentId, courseId);
    }
}