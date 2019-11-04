package com.zvz.serviceblog.vo;


import com.zvz.serviceblog.model.Guest;
import com.zvz.serviceblog.model.Message;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class ArticleMessageFormVo {
    @NotEmpty(message = "邮箱不能为空")
    private String email;//通讯邮箱
    @NotEmpty(message = "昵称不能为空")
    private String nickname;//昵称
    private String personalWebsite;//个人网址
    @NotEmpty(message = "留言内容不能为空")
    private String messageContent;//留言内容
    @NotNull(message = "对应文章不能为空")
    private Integer articleId;

    public Guest getGuest(){
        Guest guest = new Guest();
        guest.setEmail(this.email);
        guest.setNickname(this.nickname);
        guest.setPersonalWebsite(this.personalWebsite);
        return guest;
    }

    public Message getMessage(){
        Message message = new Message();
        message.setArticleId(this.articleId);
        message.setContent(this.messageContent);
        return message;
    }

}
