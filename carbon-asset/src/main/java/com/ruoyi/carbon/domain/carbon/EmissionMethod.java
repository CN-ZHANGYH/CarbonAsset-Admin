package com.ruoyi.carbon.domain.carbon;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 排放方式的对象 emission_method
 * 
 * @author 张宇豪
 * @date 2023-07-25
 */
public class EmissionMethod extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排放的方式ID */
    private Long id;

    /** 排放的方式名称 */
    @Excel(name = "排放的方式名称")
    private String name;

    /** 排放方式的描述 */
    @Excel(name = "排放方式的描述")
    private String description;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("description", getDescription())
            .toString();
    }
}
