package com.musinsa.pas.model.dto;

import lombok.Data;

/**
 * select 추가 데이터 정의
 */
@Data
public class SelectAddData {
    private int offset;
    private int limit;
}
