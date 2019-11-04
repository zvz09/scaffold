package com.zvz.serviceblog.model;

import lombok.Data;

import java.util.Date;

/**
 * 文章实体信息
 */
@Data
public class Article {

    private Integer id;
    /**
     * 标题
     */
    private String title;

    /**
     * 概要
     */
    private String summary;


    private String content;

    /**
     * 类别id
     */
    private Integer categoryIds;

    /**
     * 标签id,多个,用,分开
     */
    private String tagIds;

    /**
     * 文章状态：0-草稿，1-博文
     */
    private Integer status;
    /**
     * 撰写时间
     */
    private Date writeTime;

    /**
     * 发布时间
     */
    private Date pubTime;

    /**
     * 点击数
     */
    private int hits;

    /**
     * 标题
     */
    private int countMessages;

    /**
     * 文章对外编号
     */
    private String code;
}
