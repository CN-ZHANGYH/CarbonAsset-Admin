package com.ruoyi.carbon.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmissionVo {


    private String enterpriseAddress;

    private Long emissionId;

    private BigInteger emissionLimit;
}
