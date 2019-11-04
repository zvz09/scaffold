package com.zvz.serviceblog.model;

import lombok.Data;

/**
 * 文章标签实体信息.
 */
@Data
public class Tag {
	private Integer id;
	private String name; // 标签名称

	public Tag() {
		super();
	}

	public Tag(String name) {
		this.name = name;
	}


}
