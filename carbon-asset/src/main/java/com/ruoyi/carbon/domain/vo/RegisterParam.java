package com.ruoyi.carbon.domain.vo;

import lombok.Data;

/**
 * @author 张宇豪
 * @date 2023/7/8 20:24
 * @desc 用户注册的实体信息
 */

@Data
public class RegisterParam {

    private String username;

    private String company;

    private String password;

    private String email;

    private String code;
}
