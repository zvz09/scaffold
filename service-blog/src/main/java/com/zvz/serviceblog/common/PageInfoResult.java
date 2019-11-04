package com.zvz.serviceblog.common;

import lombok.Data;

import java.util.List;

@Data
public class PageInfoResult<T> {
    private List<T> list;
    private PageConfig pageConfig;

    private PageInfoResult() {
    }

    /**
     * 唯一公有构造器，并完全初始化PageConfig的所有变量
     * @param list List<T>
     * @param pageConfig PageConfig
     * @param infoCounts 总记录数
     */
    public PageInfoResult(List<T> list, PageConfig pageConfig,int infoCounts) {
        this.list = list;
        pageConfig.initAll(infoCounts);
        this.pageConfig = pageConfig;
    }

}
