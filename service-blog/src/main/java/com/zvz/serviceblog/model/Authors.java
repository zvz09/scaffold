package com.zvz.serviceblog.model;

import lombok.Data;

import java.util.Date;

/**
 * 作者实体信息.
 */
@Data
public class Authors {
	private Integer id;
	private String account;// 账户名
	private String password;// 账户密码
	private Integer userStatus;// 用户状态:0禁用，1启用
	private String penName;// 笔名
	private String email;// 常用邮箱
	private String profile;// 个人介绍
	private String otherInfo;// 其他信息
	private String verificationCode;// 验证码
	private Date verifCodeDeadline;// 验证码截止时间
	private String securityQuestionId;// 密保问题id

}