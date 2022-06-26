package com.musinsa.pas.exception;

import com.musinsa.pas.result.PasStatus;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 오류 처리를 위한 내부 정의 Exception
 */
@Data
public class HttpStatusException extends RuntimeException {
    private HttpStatus httpStatus;
    private String errorCode;
    private String errorMessage;

    public HttpStatusException(HttpStatus httpStatus, PasStatus pasStatus) {
        super(pasStatus.getMessage());
        this.setHttpStatus(httpStatus);
        this.setErrorCode(pasStatus.getCode());
        this.setErrorMessage(pasStatus.getMessage());
    }

}
