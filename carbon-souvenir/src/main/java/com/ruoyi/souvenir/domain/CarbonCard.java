package com.ruoyi.souvenir.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 纪念卡数据对象 carbon_card
 * 
 * @author 张宇豪
 * @date 2023-07-28
 */
public class CarbonCard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 纪念卡ID */
    private Long id;

    /** 星数等级 */
    @Excel(name = "星数等级")
    private Long level;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 图标 */
    @Excel(name = "图标")
    private String url;

    /** 分类 */
    @Excel(name = "分类")
    private String category;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
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
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("level", getLevel())
            .append("name", getName())
            .append("description", getDescription())
            .append("url", getUrl())
            .append("category", getCategory())
            .toString();
    }
}
