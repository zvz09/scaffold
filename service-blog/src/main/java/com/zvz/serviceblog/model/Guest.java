package com.zvz.serviceblog.model;

import lombok.Data;

/**
 * 访客实体信息.
 */
@Data
public class Guest {
    private Integer id;
    private String email;//通讯邮箱
    private String nickname;//昵称
    private String personalWebsite;//个人网址
    private String accessIp;//访问IP

}
