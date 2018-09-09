package com.ack.productconsumerapi.advice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String exception;
    private int code;
}
