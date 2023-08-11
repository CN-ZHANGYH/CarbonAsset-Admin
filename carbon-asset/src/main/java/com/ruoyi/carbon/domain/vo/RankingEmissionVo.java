package com.ruoyi.carbon.domain.vo;

import lombok.Data;

@Data
public class RankingEmissionVo {
    private String enterprise_address;

    private String enterprise_name;

    private Integer enterprise_carbon_credits;

    private Integer enterprise_verified;

    private String avatar;

    private Long total_emissions;
}
