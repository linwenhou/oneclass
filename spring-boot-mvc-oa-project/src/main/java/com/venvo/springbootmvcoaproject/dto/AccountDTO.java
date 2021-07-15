package com.venvo.springbootmvcoaproject.dto;


import javax.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * @author venvo
 * @date 2021-07-14 17:00
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Data
public class AccountDTO {
    @NotBlank(message = "账号不能为空")
    private String loginName;
    @NotBlank(message = "密码不能为空")
    @Length(min = 3,message = "密码长度要至少3位")
    private String password;

}
