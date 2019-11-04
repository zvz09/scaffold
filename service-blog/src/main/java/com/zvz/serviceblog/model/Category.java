package com.zvz.serviceblog.model;


import com.zvz.serviceblog.common.TreeInfo;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by FirePan on 2016/10/11.
 * 文章类别实体信息.
 * 注意：限制目录级别为两层
 */
@Data
public class Category extends TreeInfo {
    private Integer id;
    @NotEmpty(message = "名称不能为空")
    private String name;//名称
    @NotEmpty(message = "描述不能为空")
    private String description;
    private String code;

}
