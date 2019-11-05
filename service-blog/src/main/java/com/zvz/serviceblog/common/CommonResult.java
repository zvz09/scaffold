package com.zvz.serviceblog.common;


import lombok.Data;

@Data
public class CommonResult {
    private Integer resultCode;
    private String resultMsg;
    private Object resultData;

    public CommonResult() {
    }

    public CommonResult(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public CommonResult(Integer resultCode, String resultMsg, Object resultData) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }
}
