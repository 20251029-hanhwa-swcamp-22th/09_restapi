package com.ohgiraffers.restapi.section03.valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* 프로젝트 전역에서 발생하는 Exception을 처리하기 위한 클래스 */
@RestControllerAdvice // @RestController 예외만 처리
public class ExceptionController {

    /**
     * 프로젝트 전역에서 발생하는 UserNotFoundException 처리 메서드
     * @param e
     * @return
     */
    @ExceptionHandler(UserNotFoundException.class) // class를 왜 붙이는지 알아보기
    public ResponseEntity<ErrorResponse>
    handleUserException(UserNotFoundException e){

        String code = "ERROR_CODE_00000";
        String desciption = "회원 정보 조회 실패";
        String detail = e.getMessage();

        /*return new ResponseEntity<>(
                new ErrorResponse(code, desciption, detail),
                HttpStatus.NOT_FOUND // 404 상수
        );*/

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(code, desciption, detail));
    }
}
