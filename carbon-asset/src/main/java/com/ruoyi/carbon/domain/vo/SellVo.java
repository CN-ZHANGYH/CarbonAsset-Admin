package com.ruoyi.carbon.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellVo {

    public String userAddress;

    public BigInteger amount;

    public BigInteger quality;

    public String title;

    public String description;

    public String image;

}
