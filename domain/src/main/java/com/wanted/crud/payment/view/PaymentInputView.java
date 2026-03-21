package com.wanted.crud.payment.view;

import com.wanted.crud.course.controller.CourseController;
import com.wanted.crud.course.model.dto.CourseDTO;
import com.wanted.crud.course.model.dto.CourseSectionDTO;
import com.wanted.crud.payment.controller.PaymentController;
import com.wanted.crud.payment.model.service.PaymentService;

import java.util.List;
import java.util.Scanner;

public class PaymentInputView {
    /* comment.
     *   View 계층의 책임
     *   - 사용자의 입력 or 출력을 담당한다.
     *   - InputView 의 할 일
     *   - 사용자가 고를 수 있는 메뉴를 출력한다.
     *   - Scanner 를 활용한 입력을 처리한다.
     *   - Controller 를 사용자 입력에 맞게 호출한다.
     *   - OutputView 를 호출하여 결과를 출력할 수 있게 한다.
     *   .
     *   - InputView 가 하면 안 되는 일
     *   - SQL 작성 X
     *   - 비즈니스 로직 처리 X
     *   - Commit / Rollback X
     * */

    private final PaymentController controller;

    public PaymentInputView(PaymentController controller, PaymentOutputView outputView) {
        this.controller = controller;
        this.outputView = outputView;
    }

    //모든 결제내역 조회
    public void allPayment() {
        System.out.println(controller.selectAllUsers());
    }

    //결제내역 추가 (paymentAmount, paymentMethod, studentId, courseId)
    public void createPayment(Long paymentAmount, String paymentMethod, boolean status, Long studentId, Long courseId) {
        controller.createPayment(paymentAmount, paymentMethod, status,studentId,  courseId);
    }

    private final PaymentOutputView outputView;
    private final Scanner sc = new Scanner(System.in);
}
