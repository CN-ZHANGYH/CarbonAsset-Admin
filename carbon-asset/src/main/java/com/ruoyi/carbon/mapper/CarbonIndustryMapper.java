package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonIndustry;

import java.util.List;

/**
 * 行业分类单Mapper接口
 *
 * @author 张宇豪
 * @date 2023-07-25
 */
public interface CarbonIndustryMapper
{
    /**
     * 查询行业分类单
     *
     * @param id 行业分类单主键
     * @return 行业分类单
     */
    public CarbonIndustry selectCarbonIndustryById(Long id);

    /**
     * 查询行业分类单列表
     *
     * @param carbonIndustry 行业分类单
     * @return 行业分类单集合
     */
    public List<CarbonIndustry> selectCarbonIndustryList(CarbonIndustry carbonIndustry);

    /**
     * 新增行业分类单
     *
     * @param carbonIndustry 行业分类单
     * @return 结果
     */
    public int insertCarbonIndustry(CarbonIndustry carbonIndustry);

    /**
     * 修改行业分类单
     *
     * @param carbonIndustry 行业分类单
     * @return 结果
     */
    public int updateCarbonIndustry(CarbonIndustry carbonIndustry);

    /**
     * 删除行业分类单
     *
     * @param id 行业分类单主键
     * @return 结果
     */
    public int deleteCarbonIndustryById(Long id);

    /**
     * 批量删除行业分类单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarbonIndustryByIds(Long[] ids);
}
