package com.ruoyi.souvenir.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditVo
{
    private String userName;

    private String cardName;

    private BigInteger credit;


}
