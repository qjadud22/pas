package com.musinsa.pas.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 상품
 */
@Data
public class Product {
    /**
     * 상품번호
     */
    private int no;

    /**
     * 상품명
     */
    private String name;

    /**
     * 상품설명
     */
    private String content;

    /**
     * 상품정보 최초등록일시
     */
    private LocalDateTime regDate;

    /**
     * 샹품정보 수정일시
     */
    private LocalDateTime modifyDate;

    /**
     * 업체
     */
    private Company company;

    /**
     * select 추가 정보
     */
    private SelectAddData selectAddData;


}
