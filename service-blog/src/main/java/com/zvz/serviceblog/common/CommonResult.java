package com.zvz.serviceblog.common;


import lombok.Data;

@Data
public class CommonResult {
    private Integer resultCode;
    private String resultMsg;
    private Object resultData;
}
