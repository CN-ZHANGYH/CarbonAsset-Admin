package com.ruoyi.souvenir.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.souvenir.domain.CarbonCardCategory;
import com.ruoyi.souvenir.mapper.CarbonCardCategoryMapper;
import com.ruoyi.souvenir.service.card.ICarbonCardCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 纪念卡分类数据Service业务层处理
 *
 * @author 张宇豪
 * @date 2023-07-28
 */
@Service
public class CarbonCardCategoryServiceImpl implements ICarbonCardCategoryService
{
    @Autowired
    private CarbonCardCategoryMapper carbonCardCategoryMapper;

    /**
     * 查询纪念卡分类数据
     *
     * @param id 纪念卡分类数据主键
     * @return 纪念卡分类数据
     */
    @Override
    public CarbonCardCategory selectCarbonCardCategoryById(Long id)
    {
        return carbonCardCategoryMapper.selectCarbonCardCategoryById(id);
    }

    /**
     * 查询纪念卡分类数据列表
     *
     * @param carbonCardCategory 纪念卡分类数据
     * @return 纪念卡分类数据
     */
    @Override
    public List<CarbonCardCategory> selectCarbonCardCategoryList(CarbonCardCategory carbonCardCategory)
    {
        return carbonCardCategoryMapper.selectCarbonCardCategoryList(carbonCardCategory);
    }

    /**
     * 新增纪念卡分类数据
     *
     * @param carbonCardCategory 纪念卡分类数据
     * @return 结果
     */
    @Override
    public int insertCarbonCardCategory(CarbonCardCategory carbonCardCategory)
    {
        return carbonCardCategoryMapper.insertCarbonCardCategory(carbonCardCategory);
    }

    /**
     * 修改纪念卡分类数据
     *
     * @param carbonCardCategory 纪念卡分类数据
     * @return 结果
     */
    @Override
    public int updateCarbonCardCategory(CarbonCardCategory carbonCardCategory)
    {
        return carbonCardCategoryMapper.updateCarbonCardCategory(carbonCardCategory);
    }

    /**
     * 批量删除纪念卡分类数据
     *
     * @param ids 需要删除的纪念卡分类数据主键
     * @return 结果
     */
    @Override
    public int deleteCarbonCardCategoryByIds(Long[] ids)
    {
        return carbonCardCategoryMapper.deleteCarbonCardCategoryByIds(ids);
    }

    /**
     * 删除纪念卡分类数据信息
     *
     * @param id 纪念卡分类数据主键
     * @return 结果
     */
    @Override
    public int deleteCarbonCardCategoryById(Long id)
    {
        return carbonCardCategoryMapper.deleteCarbonCardCategoryById(id);
    }

    @Override
    public AjaxResult selectCarbonCardCategoryNameList(CarbonCardCategory carbonCardCategory) {
        List<CarbonCardCategory> cardCategories = selectCarbonCardCategoryList(carbonCardCategory);
        List<HashMap<String, Object>> resultMap = cardCategories.stream().map(categories -> {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("label", categories.getName());
            hashMap.put("value", categories.getName());
            return hashMap;
        }).collect(Collectors.toList());
        AjaxResult ajax = AjaxResult.success();
        return ajax.put("data",resultMap);
    }
}
