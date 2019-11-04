package com.zvz.serviceblog.model;

import lombok.Data;

import java.util.Date;

@Data
public class Recommend {
    private long id;
    private String title;
    private String articleUrl;
    private String summary;
    private Date pubTime;;
    private int hits;

}