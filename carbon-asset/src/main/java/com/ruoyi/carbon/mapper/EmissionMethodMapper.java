package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.EmissionMethod;

import java.util.List;


/**
 * 排放方式的Mapper接口
 *
 * @author 张宇豪
 * @date 2023-07-25
 */
public interface EmissionMethodMapper
{
    /**
     * 查询排放方式的
     *
     * @param id 排放方式的主键
     * @return 排放方式的
     */
    public EmissionMethod selectEmissionMethodById(Long id);

    /**
     * 查询排放方式的列表
     *
     * @param emissionMethod 排放方式的
     * @return 排放方式的集合
     */
    public List<EmissionMethod> selectEmissionMethodList(EmissionMethod emissionMethod);

    /**
     * 新增排放方式的
     *
     * @param emissionMethod 排放方式的
     * @return 结果
     */
    public int insertEmissionMethod(EmissionMethod emissionMethod);

    /**
     * 修改排放方式的
     *
     * @param emissionMethod 排放方式的
     * @return 结果
     */
    public int updateEmissionMethod(EmissionMethod emissionMethod);

    /**
     * 删除排放方式的
     *
     * @param id 排放方式的主键
     * @return 结果
     */
    public int deleteEmissionMethodById(Long id);

    /**
     * 批量删除排放方式的
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmissionMethodByIds(Long[] ids);
}
