package com.ruoyi.carbon.domain.carbon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * 企业排放资源对象 carbon_emission_resource
 * 
 * @author 张宇豪
 * @date 2023-07-08
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonEmissionResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排放资源的ID */
    private Long emissionId;

    /** 企业的ID */
    @Excel(name = "企业的ID")
    private Long enterpriseId;

    /** 排放的企业 */
    @Excel(name = "排放的企业")
    private String enterpriseAddress;

    /** 排放的量 */
    @Excel(name = "排放的量")
    private BigInteger emissions;

    /** 排放的资源描述 */
    @Excel(name = "排放的资源描述")
    private String description;

    /** 排放的方式 */
    @Excel(name = "排放的方式")
    private String emissionWay;

    /** 是否批准排放 */
    @Excel(name = "是否批准排放")
    private Integer isApprove;

    /** 排放时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "排放时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String emissionTime;

    /** 资源分类 */
    @Excel(name = "资源分类")
    private String resourceType;
}
