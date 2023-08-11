package com.ruoyi.carbon.domain.carbon;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.math.BigInteger;

/**
 * 企业信息对象 carbon_enterprise
 *
 * @author ruoyi
 * @date 2023-07-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonEnterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 企业ID */
    private Integer enterpriseId;

    /** 企业账户地址 */
    @Excel(name = "企业账户地址")
    private String enterpriseAddress;

    /** 企业的私钥 */
    @Excel(name = "企业的私钥")
    private String priavateKey;

    /** 企业的名称 */
    @Excel(name = "企业的名称")
    private String enterpriseName;

    @Transient
    private String enterpriseNickName;

    @Transient
    private String enterprisePass;

    @Transient
    private String enterpriseEmail;

    /** 企业的余额 */
    @Excel(name = "企业的余额")
    private BigInteger enterpriseBalance;

    /** 总需排放的量 */
    @Excel(name = "总需排放的量")
    private BigInteger enterpriseTotalEmission;

    /** 已完成的排放量 */
    @Excel(name = "已完成的排放量")
    private BigInteger enterpriseOverEmission;

    /** 奖励积分 */
    @Excel(name = "奖励积分")
    private BigInteger enterpriseCarbonCredits;

    /** 是否通过审核 */
    @Excel(name = "是否通过审核")
    private Integer enterpriseVerified;

    /** 账户角色 */
    @Excel(name = "账户角色")
    private Integer userType;

    /** 资质信息ID */
    @Excel(name = "资质信息ID")
    private Integer qualificationId;


}
