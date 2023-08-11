package com.ruoyi.carbon.domain.vo;

import lombok.Data;

@Data
public class RankingEmissionVo {
    private String enterprise_address;

    private String enterprise_name;

    private Integer enterprise_carbon_credits;

    private Integer enterprise_verified;

    private Long enterprise_over_emission;

    private Long enterprise_total_emission;

    private String avatar;

    private Long total_emissions;
}
