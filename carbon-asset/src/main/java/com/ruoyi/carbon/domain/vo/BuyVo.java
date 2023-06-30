package com.ruoyi.carbon.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyVo {

    private String ownerAddress;

    private String sellerAddress;

    private BigInteger assetId;

    private BigInteger quality;
}
