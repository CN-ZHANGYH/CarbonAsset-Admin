package com.ruoyi.carbon.domain.carbon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;

/**
 * 企业出售资产对象 carbon_enterprise_asset
 *
 * @author 张宇豪
 * @date 2023-07-08
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonEnterpriseAsset extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 企业出售碳资产的列表ID */
    private Long assetId;

    /** 企业的ID */
    @Excel(name = "企业的ID")
    private Long enterpriseId;

    /** 企业出售碳资产的账户地址 */
    @Excel(name = "企业出售碳资产的账户地址")
    private String enterpriseAddress;

    /** 企业出售碳资产的数量 */
    @Excel(name = "企业出售碳资产的数量")
    private BigInteger assetQuantity;

    /** 企业出售碳资产的价钱 */
    @Excel(name = "企业出售碳资产的价钱")
    private BigInteger assetAmount;

    /** 企业出售碳资产的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "企业出售碳资产的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String  time;

    /** 出售订单的状态 */
    @Excel(name = "出售订单的状态")
    private Integer status;

    @Excel(name = "出售名称")
    private String title;

    @Excel(name = "出售订单描述")
    private String description;

    @Excel(name = "出售的碳额度封面")
    private String image;
}
