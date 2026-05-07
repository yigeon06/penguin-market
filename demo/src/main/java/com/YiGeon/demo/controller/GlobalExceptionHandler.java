package com.YiGeon.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // DTO의 @Valid 유효성 검사 에러 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    // 서비스 단에서 발생시킨 IllegalArgumentException 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Service에서 throw new IllegalArgumentException("메시지") 에 넣은 메시지를 꺼내서 프론트엔드로 보냅니다.
        // 상태 코드도 500(서버 잘못)이 아니라 400(잘못된 요청)으로 설정해 줍니다.
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
