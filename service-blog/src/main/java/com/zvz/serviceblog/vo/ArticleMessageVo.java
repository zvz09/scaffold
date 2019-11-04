package com.zvz.serviceblog.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleMessageVo {
    private String guestNickname;//昵称
    private String guestWebsite;//个人网址
    private String messageId;
    private String messageContent;//留言内容
    private Date messagePubTime;
    private int messageSupportCount;

}
