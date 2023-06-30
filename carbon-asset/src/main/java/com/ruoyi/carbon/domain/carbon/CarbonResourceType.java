package com.ruoyi.carbon.domain.carbon;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 资源类型数据对象 carbon_resource_type
 * 
 * @author 张宇豪
 * @date 2023-07-25
 */
public class CarbonResourceType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资源类型ID */
    private Long id;

    /** 资源类型 */
    @Excel(name = "资源类型")
    private String resourceType;

    /** 资源大类 */
    @Excel(name = "资源大类")
    private String category;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createdAt;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date updatedAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setResourceType(String resourceType) 
    {
        this.resourceType = resourceType;
    }

    public String getResourceType() 
    {
        return resourceType;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("resourceType", getResourceType())
            .append("category", getCategory())
            .append("description", getDescription())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
