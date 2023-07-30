package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonResourceType;
import com.ruoyi.carbon.domain.vo.ResourceTypeVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 资源类型数据Mapper接口
 * 
 * @author 张宇豪
 * @date 2023-07-25
 */
public interface CarbonResourceTypeMapper
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
     * 删除资源类型数据
     * 
     * @param id 资源类型数据主键
     * @return 结果
     */
    public int deleteCarbonResourceTypeById(Long id);

    /**
     * 批量删除资源类型数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarbonResourceTypeByIds(Long[] ids);


    @Select("SELECT category AS name, COUNT(*) AS value FROM carbon.carbon_resource_type GROUP BY category;")
    public List<ResourceTypeVo> selectResourceTypeByCount();

}
