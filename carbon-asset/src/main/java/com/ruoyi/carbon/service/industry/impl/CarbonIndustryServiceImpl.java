package com.ruoyi.carbon.service.industry.impl;

import com.ruoyi.carbon.domain.carbon.CarbonIndustry;
import com.ruoyi.carbon.mapper.CarbonIndustryMapper;
import com.ruoyi.carbon.service.industry.ICarbonIndustryService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 行业分类单Service业务层处理
 *
 * @author 张宇豪
 * @date 2023-07-25
 */
@Service
public class CarbonIndustryServiceImpl implements ICarbonIndustryService
{
    @Autowired
    private CarbonIndustryMapper carbonIndustryMapper;

    /**
     * 查询行业分类单
     *
     * @param id 行业分类单主键
     * @return 行业分类单
     */
    @Override
    public CarbonIndustry selectCarbonIndustryById(Long id)
    {
        return carbonIndustryMapper.selectCarbonIndustryById(id);
    }

    /**
     * 查询行业分类单列表
     *
     * @param carbonIndustry 行业分类单
     * @return 行业分类单
     */
    @Override
    public List<CarbonIndustry> selectCarbonIndustryList(CarbonIndustry carbonIndustry)
    {
        return carbonIndustryMapper.selectCarbonIndustryList(carbonIndustry);
    }

    /**
     * 新增行业分类单
     *
     * @param carbonIndustry 行业分类单
     * @return 结果
     */
    @Override
    public int insertCarbonIndustry(CarbonIndustry carbonIndustry)
    {
        return carbonIndustryMapper.insertCarbonIndustry(carbonIndustry);
    }

    /**
     * 修改行业分类单
     *
     * @param carbonIndustry 行业分类单
     * @return 结果
     */
    @Override
    public int updateCarbonIndustry(CarbonIndustry carbonIndustry)
    {
        return carbonIndustryMapper.updateCarbonIndustry(carbonIndustry);
    }

    /**
     * 批量删除行业分类单
     *
     * @param ids 需要删除的行业分类单主键
     * @return 结果
     */
    @Override
    public int deleteCarbonIndustryByIds(Long[] ids)
    {
        return carbonIndustryMapper.deleteCarbonIndustryByIds(ids);
    }

    /**
     * 删除行业分类单信息
     *
     * @param id 行业分类单主键
     * @return 结果
     */
    @Override
    public int deleteCarbonIndustryById(Long id)
    {
        return carbonIndustryMapper.deleteCarbonIndustryById(id);
    }

    @Override
    public AjaxResult selectIndustryNameList(CarbonIndustry carbonIndustry) {
        List<CarbonIndustry> carbonIndustries = this.selectCarbonIndustryList(carbonIndustry);
        if (carbonIndustries.size() < 0 ){
            return AjaxResult.error("当前还没有添加行业");
        }
        List<Object> resultMaps = carbonIndustries.stream()
                .map((Function<CarbonIndustry, Object>) industry -> {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("label", industry.getName());
                    map.put("key", industry.getName());
                    return map;
        }).collect(Collectors.toList());
        AjaxResult ajax = AjaxResult.success();
        return ajax.put("data",resultMaps);
    }
}
