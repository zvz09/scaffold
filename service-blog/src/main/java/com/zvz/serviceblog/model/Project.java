package com.zvz.serviceblog.model;

import lombok.Data;

import java.util.Date;

@Data
public class Project {
    private int id;
    private String name;
    private String introduction;
    private Date pubTime;
    private int hits;
    private String downUrl;
    private String articleUrl;
    private boolean status;

}
