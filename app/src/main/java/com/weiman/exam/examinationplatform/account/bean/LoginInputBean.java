package com.weiman.exam.examinationplatform.account.bean;

/**
 * 创建 by 刘宇飞 on 2017/7/22.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */

public class LoginInputBean {
    public String email;
    public String password;
    public String osType;
    public String code;
    public String name;
    public int type=0;

    public LoginInputBean(String email, String password, String osType) {
        this.email = email;
        this.password = password;
        this.osType = osType;
    }

    public LoginInputBean(String email, String password, String code, String name,int type) {
        this.email = email;
        this.password = password;
        this.code = code;
        this.name = name;
        this.type=type;
    }
}
