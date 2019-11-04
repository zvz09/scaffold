package com.zvz.serviceblog.vo;

import lombok.Data;

@Data
public class AuthorSessionVo {
	private String account;
	private int id;

	public AuthorSessionVo(String account, int id) {
		super();
		this.account = account;
		this.id = id;
	}



}
