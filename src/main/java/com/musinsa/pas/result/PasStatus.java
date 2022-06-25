package com.musinsa.pas.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PasStatus {
    SUCCESS("000000", "success"),
    FAIL("999999", "fail"),
    PRI2001("RPE3001", "파라미터 오류"),
    PRE2001("PRE2001", "서버 내부 오류");

    private final String code;
    private final String message;
}
