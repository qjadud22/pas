package com.musinsa.pas.result;

import lombok.Data;

@Data
public class ResultMessage {
    private String status;
    private String desc;
    private Object result;
}
