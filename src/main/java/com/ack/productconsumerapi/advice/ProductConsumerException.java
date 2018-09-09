package com.ack.productconsumerapi.advice;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductConsumerException extends RuntimeException{

    private String detail;
    private int code;

    public ProductConsumerException(String detail, int code) {
        this.detail = detail;
        this.code = code;
    }
}
