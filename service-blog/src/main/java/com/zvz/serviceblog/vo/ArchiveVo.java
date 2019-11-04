package com.zvz.serviceblog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Pan
 * 没有明确对应的实体，主要用到Article实体中的部分数据，只是用来前台数据展示
 */
@Data
public class ArchiveVo {
	
	private String name;//导航的名称，如2016年10月
	private Integer counts;//文章数量
	
	private List<ArchiveVo> childArchive;
	

	
	
}
