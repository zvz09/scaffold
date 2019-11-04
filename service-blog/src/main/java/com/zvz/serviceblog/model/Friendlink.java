package com.zvz.serviceblog.model;

import lombok.Data;

import java.util.Date;

@Data
public class Friendlink {

    private int id;
    private String name;//网站名称
    private String website;//网站地址
    private String description;//网站介绍
    private int hits;//点击量
    private int priority;//优先级
    private String webAuthorEmail;//网站作者联系邮箱
    private String webAuthorName;//网站作者名称
    private Date addTime;

}
