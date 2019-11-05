package com.zvz.serviceblog.service;


import com.zvz.serviceblog.model.Guest;
import com.zvz.serviceblog.model.Message;
import com.zvz.serviceblog.vo.ArticleMessageVo;

import java.util.List;


public interface IMessageService {


    /**
     * 游客评论操作
     * @param guest
     * @param message
     * @return
     */
    boolean guestMessage(Guest guest, Message message);

    /**
     * 通过评论id删除评论
     *
     * @param messageId 评论id
     * @param authorId  操作的作者id
     * @return
     */
    Boolean messageDeleteById(int messageId, int authorId);

    /**
     * 通过文章id删除相关评论
     *
     * @param messageArticleId 文章id
     * @return
     */
    Boolean messageDeleteBy(int messageArticleId);


    /**
     * 获取所有留言信息
     * @param messageArticleId 文章id
     * @return
     */
    List<ArticleMessageVo> list(int messageArticleId);
}
