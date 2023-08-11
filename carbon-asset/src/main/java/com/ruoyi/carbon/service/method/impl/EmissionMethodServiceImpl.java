package com.ruoyi.carbon.service.method.impl;

import com.ruoyi.carbon.domain.carbon.EmissionMethod;
import com.ruoyi.carbon.mapper.EmissionMethodMapper;
import com.ruoyi.carbon.service.method.IEmissionMethodService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 排放方式的Service业务层处理
 * 
 * @author 张宇豪
 * @date 2023-07-25
 */
@Service
public class EmissionMethodServiceImpl implements IEmissionMethodService
{
    @Autowired
    private EmissionMethodMapper emissionMethodMapper;

    /**
     * 查询排放方式的
     * 
     * @param id 排放方式的主键
     * @return 排放方式的
     */
    @Override
    public EmissionMethod selectEmissionMethodById(Long id)
    {
        return emissionMethodMapper.selectEmissionMethodById(id);
    }

    /**
     * 查询排放方式的列表
     * 
     * @param emissionMethod 排放方式的
     * @return 排放方式的
     */
    @Override
    public List<EmissionMethod> selectEmissionMethodList(EmissionMethod emissionMethod)
    {
        return emissionMethodMapper.selectEmissionMethodList(emissionMethod);
    }

    /**
     * 新增排放方式的
     * 
     * @param emissionMethod 排放方式的
     * @return 结果
     */
    @Override
    public int insertEmissionMethod(EmissionMethod emissionMethod)
    {
        return emissionMethodMapper.insertEmissionMethod(emissionMethod);
    }

    /**
     * 修改排放方式的
     * 
     * @param emissionMethod 排放方式的
     * @return 结果
     */
    @Override
    public int updateEmissionMethod(EmissionMethod emissionMethod)
    {
        return emissionMethodMapper.updateEmissionMethod(emissionMethod);
    }

    /**
     * 批量删除排放方式的
     * 
     * @param ids 需要删除的排放方式的主键
     * @return 结果
     */
    @Override
    public int deleteEmissionMethodByIds(Long[] ids)
    {
        return emissionMethodMapper.deleteEmissionMethodByIds(ids);
    }

    /**
     * 删除排放方式的信息
     * 
     * @param id 排放方式的主键
     * @return 结果
     */
    @Override
    public int deleteEmissionMethodById(Long id)
    {
        return emissionMethodMapper.deleteEmissionMethodById(id);
    }


    /**
     * 查询所有的排放方式
     * @param emissionMethod
     * @return 返回结果
     */
    @Override
    public AjaxResult selectEmissionMethods(EmissionMethod emissionMethod) {
        List<EmissionMethod> emissionMethods = this.selectEmissionMethodList(emissionMethod);
        if (emissionMethods.size() < 0 )
        {
            return AjaxResult.error("当前还没有添加排放方式");
        }
        List<Object> resultMaps = emissionMethods.stream().map(new Function<EmissionMethod, Object>() {
            @Override
            public Object apply(EmissionMethod emissionMethod) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("label", emissionMethod.getName());
                map.put("value", emissionMethod.getName());
                return map;
            }
        }).collect(Collectors.toList());
        AjaxResult ajax = AjaxResult.success();
        return ajax.put("data",resultMaps);
    }
}
