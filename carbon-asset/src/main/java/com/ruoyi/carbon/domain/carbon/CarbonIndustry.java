package com.ruoyi.carbon.domain.carbon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 行业分类单对象 carbon_industry
 *
 * @author 张宇豪
 * @date 2023-07-25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonIndustry extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    private Long id;

    /** 行业名称 */
    @Excel(name = "行业名称")
    private String name;

    /** 行业描述 */
    @Excel(name = "行业描述")
    private String description;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("description", getDescription())
                .toString();
    }
}
