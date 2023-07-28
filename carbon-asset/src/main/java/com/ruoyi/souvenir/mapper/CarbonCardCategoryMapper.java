package com.ruoyi.souvenir.mapper;

import java.util.List;
import com.ruoyi.souvenir.domain.CarbonCardCategory;

/**
 * 纪念卡分类数据Mapper接口
 * 
 * @author 张宇豪
 * @date 2023-07-28
 */
public interface CarbonCardCategoryMapper 
{
    /**
     * 查询纪念卡分类数据
     * 
     * @param id 纪念卡分类数据主键
     * @return 纪念卡分类数据
     */
    public CarbonCardCategory selectCarbonCardCategoryById(Long id);

    /**
     * 查询纪念卡分类数据列表
     * 
     * @param carbonCardCategory 纪念卡分类数据
     * @return 纪念卡分类数据集合
     */
    public List<CarbonCardCategory> selectCarbonCardCategoryList(CarbonCardCategory carbonCardCategory);

    /**
     * 新增纪念卡分类数据
     * 
     * @param carbonCardCategory 纪念卡分类数据
     * @return 结果
     */
    public int insertCarbonCardCategory(CarbonCardCategory carbonCardCategory);

    /**
     * 修改纪念卡分类数据
     * 
     * @param carbonCardCategory 纪念卡分类数据
     * @return 结果
     */
    public int updateCarbonCardCategory(CarbonCardCategory carbonCardCategory);

    /**
     * 删除纪念卡分类数据
     * 
     * @param id 纪念卡分类数据主键
     * @return 结果
     */
    public int deleteCarbonCardCategoryById(Long id);

    /**
     * 批量删除纪念卡分类数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarbonCardCategoryByIds(Long[] ids);
}