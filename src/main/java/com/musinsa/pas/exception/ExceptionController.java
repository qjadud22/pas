package com.musinsa.pas.exception;

import com.musinsa.pas.result.ResultMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(HttpStatusException.class)
    public ResponseEntity<ResultMessage> httpStatusExceptionHandler(HttpStatusException e) {
        // 에러 내용 설정
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(e.getErrorCode());
        resultMessage.setDesc(e.getErrorMessage());
        return new ResponseEntity<>(resultMessage, e.getHttpStatus());
    }
}
