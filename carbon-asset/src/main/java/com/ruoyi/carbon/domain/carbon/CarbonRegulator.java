package com.ruoyi.carbon.domain.carbon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

/**
 * 监管机构信息对象 carbon_regulator
 * 
 * @author 张宇豪
 * @date 2023-07-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonRegulator extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 监管机构ID */
    private Integer regulatorId;

    /** 监管机构账户地址 */
    @Excel(name = "监管机构账户地址")
    private String regulatorAddress;

    @Transient
    private String regulatorUser;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String regulatorName;

    @Excel(name = "机构地址")
    private String privateKey;

    @Transient
    private String regulatorPass;


    /** 账户类型 */
    @Excel(name = "账户类型")
    private Integer userType;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("regulatorId", getRegulatorId())
            .append("regulatorAddress", getRegulatorAddress())
            .append("regulatorName", getRegulatorName())
            .append("userType", getUserType())
            .toString();
    }
}
