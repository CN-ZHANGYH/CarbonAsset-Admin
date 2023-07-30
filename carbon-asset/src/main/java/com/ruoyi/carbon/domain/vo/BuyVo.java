package com.ruoyi.carbon.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


/**
 * 购买碳额度入参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyVo {

    /**
     * 买家地址
     */
    private String ownerAddress;

    /**
     * 卖家地址
     */
    private String sellerAddress;

    /**
     * 资产ID
     */
    private BigInteger assetId;

    /**
     * 数量
     */
    private BigInteger quality;
}
