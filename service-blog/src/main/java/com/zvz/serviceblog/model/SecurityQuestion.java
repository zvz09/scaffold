package com.zvz.serviceblog.model;

import lombok.Data;

/**
 * 安全问题实体信息.
 */
@Data
public class SecurityQuestion {
    private Integer id;
    private Integer userType;//用户类别（管理员，作者）
    private String questoin01;
    private String answer01;
    private String question02;
    private String answer02;
    private String question03;
    private String answer03;
}
