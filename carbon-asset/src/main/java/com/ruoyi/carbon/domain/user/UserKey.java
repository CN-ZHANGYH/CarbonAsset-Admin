package com.ruoyi.carbon.domain.user;

import lombok.Data;

/**
 * @author 张宇豪
 * @date 2023/7/8 19:53
 * @desc 用户的私钥公钥以及地址信息
 */

@Data
public class UserKey {

    /**
     * 用户地址
     */
    private String address;

    /**
     * 用户私钥
     */
    private String privateKey;

    /**
     * 用户地址
     */
    private String publicKey;
}
