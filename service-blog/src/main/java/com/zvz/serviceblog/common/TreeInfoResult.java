package com.zvz.serviceblog.common;

import lombok.Data;

import java.util.List;

@Data
public class TreeInfoResult {
    private List<TreeInfoResult> childrens;
    private TreeInfo data;

    private TreeInfoResult(){

    }

    public TreeInfoResult(TreeInfo data) {
        this.data = data;
    }


}
