package com.zvz.serviceblog.vo;


import com.zvz.serviceblog.model.Category;
import lombok.Data;

@Data
public class CategoryVo extends Category {
	private Integer counts;
    private int hits;
    private int countMessages;

    public CategoryVo categor2Vo(Category category){
        if(category!=null){
            this.setId(category.getId());
            this.setName(category.getName());
            this.setDescription(category.getDescription());
        }
        return this;
    }

}
