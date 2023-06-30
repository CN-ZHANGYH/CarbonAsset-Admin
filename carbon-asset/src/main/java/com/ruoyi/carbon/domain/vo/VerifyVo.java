package com.ruoyi.carbon.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyVo {

    private String regulatorAddress;

    private String enterpriseAddress;

    private Long emissionId;

    private Boolean isApprove;
}
