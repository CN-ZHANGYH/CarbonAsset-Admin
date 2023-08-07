package com.ruoyi.carbon.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseVo {
    /** 企业ID */
    private Integer enterprise_id;

    /** 企业账户地址 */
    private String enterprise_address;

    /** 企业的私钥 */
    @JsonIgnore
    private String priavate_key;

    /** 企业的名称 */
    private String enterprise_name;

    /** 企业的余额 */
    private BigInteger enterprise_balance;

    /** 总需排放的量 */
    private BigInteger enterprise_total_emission;

    /** 已完成的排放量 */
    private BigInteger enterprise_over_emission;

    /** 奖励积分 */
    private BigInteger enterprise_carbon_credits;

    /** 是否通过审核 */
    private Integer enterprise_verified;

    /** 账户角色 */
    private Integer user_type;

    /** 资质信息ID */
    private Integer qualification_id;

    /** 用户名 */
    private String user_name;

    /** 头像 */
    private String avatar;

    /** 邮箱 */
    private String email;

    /** 用户密码 */
    private String password;

    /** 用户手机号 */
    private String phonenumber;

    /** 注册时间 */
    private String create_time;

}
