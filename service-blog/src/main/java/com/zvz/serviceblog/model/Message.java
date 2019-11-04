package com.zvz.serviceblog.model;



import com.zvz.serviceblog.common.CommonConstant;
import com.zvz.serviceblog.common.TreeInfo;
import lombok.Data;

import java.util.Date;

/**
 * 文章留言实体信息.
 */
@Data
public class Message extends TreeInfo {
    private Integer id;
    private Integer articleId;//评论的文章id
    private Integer blockId;//所在评论的文章的评论区的第几块区域id
    private Integer parentId = CommonConstant.MESSAGE_DEFAULT_PARENT_ID;//父id
    private String content;//内容
    private Integer userType;//留言作者类别（author作者，guest访客）
    private Integer authorId;//作者id
    private Date pubTime;//评论时间


}
