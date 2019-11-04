package com.zvz.serviceblog.vo;


import com.zvz.serviceblog.model.Tag;
import lombok.Data;

@Data
public class TagVo extends Tag {
	private Integer counts;
}
