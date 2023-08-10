package com.ruoyi.carbon.service.type.impl;

import com.ruoyi.carbon.domain.carbon.CarbonResourceType;
import com.ruoyi.carbon.domain.vo.ResourceTypeVo;
import com.ruoyi.carbon.mapper.CarbonResourceTypeMapper;
import com.ruoyi.carbon.service.type.ICarbonResourceTypeService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 资源类型数据Service业务层处理
 * 
 * @author 张宇豪
 * @date 2023-07-25
 */
@Service
public class CarbonResourceTypeServiceImpl implements ICarbonResourceTypeService
{
    @Autowired
    private CarbonResourceTypeMapper carbonResourceTypeMapper;

    /**
     * 查询资源类型数据
     * 
     * @param id 资源类型数据主键
     * @return 资源类型数据
     */
    @Override
    public CarbonResourceType selectCarbonResourceTypeById(Long id)
    {
        return carbonResourceTypeMapper.selectCarbonResourceTypeById(id);
    }

    /**
     * 查询资源类型数据列表
     * 
     * @param carbonResourceType 资源类型数据
     * @return 资源类型数据
     */
    @Override
    public List<CarbonResourceType> selectCarbonResourceTypeList(CarbonResourceType carbonResourceType)
    {
        return carbonResourceTypeMapper.selectCarbonResourceTypeList(carbonResourceType);
    }

    /**
     * 新增资源类型数据
     * 
     * @param carbonResourceType 资源类型数据
     * @return 结果
     */
    @Override
    public int insertCarbonResourceType(CarbonResourceType carbonResourceType)
    {
        return carbonResourceTypeMapper.insertCarbonResourceType(carbonResourceType);
    }

    /**
     * 修改资源类型数据
     * 
     * @param carbonResourceType 资源类型数据
     * @return 结果
     */
    @Override
    public int updateCarbonResourceType(CarbonResourceType carbonResourceType)
    {
        return carbonResourceTypeMapper.updateCarbonResourceType(carbonResourceType);
    }

    /**
     * 批量删除资源类型数据
     * 
     * @param ids 需要删除的资源类型数据主键
     * @return 结果
     */
    @Override
    public int deleteCarbonResourceTypeByIds(Long[] ids)
    {
        return carbonResourceTypeMapper.deleteCarbonResourceTypeByIds(ids);
    }

    /**
     * 删除资源类型数据信息
     * 
     * @param id 资源类型数据主键
     * @return 结果
     */
    @Override
    public int deleteCarbonResourceTypeById(Long id)
    {
        return carbonResourceTypeMapper.deleteCarbonResourceTypeById(id);
    }

    @Override
    public AjaxResult selectResourceTypeNameList(CarbonResourceType resourceType) {
        List<CarbonResourceType> carbonResourceTypes = this.selectCarbonResourceTypeList(resourceType);
        if (carbonResourceTypes.size() < 0) {
            return AjaxResult.error("当前还没有添加资源类型");
        }
        List<Object> resultMaps = carbonResourceTypes.stream()
                .map((Function<CarbonResourceType, Object>) carbonResourceType -> {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("label", carbonResourceType.getResourceType());
                    map.put("value", carbonResourceType.getResourceType());
            return map;
        }).collect(Collectors.toList());
        AjaxResult ajax = AjaxResult.success();
        return ajax.put("data",resultMaps);
    }

    @Override
    public AjaxResult selectResourceTypeByCount() {
        List<ResourceTypeVo> resourceTypeVo = carbonResourceTypeMapper.selectResourceTypeByCount();
        if (resourceTypeVo.size() < 0)
        {
            return AjaxResult.error("当前还没有资源分类");
        }
        return AjaxResult.success(resourceTypeVo);
    }
}
