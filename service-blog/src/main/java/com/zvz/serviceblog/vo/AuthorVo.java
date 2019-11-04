package com.zvz.serviceblog.vo;

import lombok.Data;

@Data
public class AuthorVo {
    private String account;
    private String password;
    private String captcha;//验证码
}
