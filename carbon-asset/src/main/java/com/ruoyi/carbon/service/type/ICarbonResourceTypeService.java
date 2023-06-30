package com.ruoyi.carbon.service.type;

import com.ruoyi.carbon.domain.carbon.CarbonResourceType;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;


/**
 * 资源类型数据Service接口
 * 
 * @author 张宇豪
 * @date 2023-07-25
 */
public interface ICarbonResourceTypeService 
{
    /**
     * 查询资源类型数据
     * 
     * @param id 资源类型数据主键
     * @return 资源类型数据
     */
    public CarbonResourceType selectCarbonResourceTypeById(Long id);

    /**
     * 查询资源类型数据列表
     * 
     * @param carbonResourceType 资源类型数据
     * @return 资源类型数据集合
     */
    public List<CarbonResourceType> selectCarbonResourceTypeList(CarbonResourceType carbonResourceType);

    /**
     * 新增资源类型数据
     * 
     * @param carbonResourceType 资源类型数据
     * @return 结果
     */
    public int insertCarbonResourceType(CarbonResourceType carbonResourceType);

    /**
     * 修改资源类型数据
     * 
     * @param carbonResourceType 资源类型数据
     * @return 结果
     */
    public int updateCarbonResourceType(CarbonResourceType carbonResourceType);

    /**
     * 批量删除资源类型数据
     * 
     * @param ids 需要删除的资源类型数据主键集合
     * @return 结果
     */
    public int deleteCarbonResourceTypeByIds(Long[] ids);

    /**
     * 删除资源类型数据信息
     * 
     * @param id 资源类型数据主键
     * @return 结果
     */
    public int deleteCarbonResourceTypeById(Long id);

    public AjaxResult selectResourceTypeNameList(CarbonResourceType resourceType);
}
