package com.ruoyi.carbon.domain.carbon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 交易碳额度记录对象 carbon_transaction
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarbonTransaction extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单的交易ID */
    private Long transactionId;

    /** 买家ID */
    @Excel(name = "买家ID")
    private Long buyerId;

    @Excel(name = "交易HASH")
    private String txHash;

    /** 卖家的ID */
    @Excel(name = "卖家的ID")
    private Long sellerId;

    /** 订单的名字 */
    @Excel(name = "订单的名字")
    private String transactionOrderName;

    /** 买家地址 */
    @Excel(name = "买家地址")
    private String buyAddress;

    /** 卖家地址 */
    @Excel(name = "卖家地址")
    private String sellerAddress;

    /** 订单创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String transactionTime;

    /** 购买碳额度的数量 */
    @Excel(name = "购买碳额度的数量")
    private Long transactionQuantity;
}
