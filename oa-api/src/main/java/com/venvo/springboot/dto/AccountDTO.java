package com.venvo.springboot.dto;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author venvo
 * @date 2021-07-14 17:00
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Data
public class AccountDTO implements Serializable {
    @NotBlank(message = "账号不能为空")
    private String loginName;
    @NotBlank(message = "密码不能为空")
    @Length(min = 3,message = "密码长度要至少3位")
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
