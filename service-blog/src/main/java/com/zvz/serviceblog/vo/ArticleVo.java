package com.zvz.serviceblog.vo;


import com.zvz.serviceblog.common.CommonConstant;
import com.zvz.serviceblog.model.Article;
import lombok.Getter;
import lombok.Setter;

public class ArticleVo extends Article {
	@Getter
	@Setter
	private String categoryName;
	@Getter
	@Setter
    private String categoryCode;
	@Getter
    private String statusName;
	@Getter
	@Setter
	private String[] tagNames;
	@Getter
	@Setter
	private Boolean onlyChangeStatus=false;
	


	public void setStatusName(int status) {
		if(status== CommonConstant.ACTICLE_STATUS_BLOG){
			this.statusName= CommonConstant.ACTICLE_STATUS_BLOG_NAME;
		}else{
			this.statusName=CommonConstant.ACTICLE_STATUS_DRAFT_NAME;
		}
	}


}
