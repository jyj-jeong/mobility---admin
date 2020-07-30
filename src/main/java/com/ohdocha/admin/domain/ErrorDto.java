package com.ohdocha.admin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ErrorDto {

    private int status;
    private String error;
    private String path;
    private int code;
    private String message;
    private String timestamp;

}
