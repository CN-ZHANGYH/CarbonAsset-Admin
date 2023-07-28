package com.ruoyi.souvenir.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.souvenir.mapper.CarbonCardMapper;
import com.ruoyi.souvenir.domain.CarbonCard;
import com.ruoyi.souvenir.service.ICarbonCardService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 纪念卡数据Service业务层处理
 *
 * @author 张宇豪
 * @date 2023-07-28
 */
@Service
public class CarbonCardServiceImpl implements ICarbonCardService
{
    @Autowired
    private CarbonCardMapper carbonCardMapper;

    /**
     * 查询纪念卡数据
     *
     * @param id 纪念卡数据主键
     * @return 纪念卡数据
     */
    @Override
    public CarbonCard selectCarbonCardById(Long id)
    {
        return carbonCardMapper.selectCarbonCardById(id);
    }

    /**
     * 查询纪念卡数据列表
     *
     * @param carbonCard 纪念卡数据
     * @return 纪念卡数据
     */
    @Override
    public List<CarbonCard> selectCarbonCardList(CarbonCard carbonCard)
    {
        return carbonCardMapper.selectCarbonCardList(carbonCard);
    }

    /**
     * 新增纪念卡数据
     *
     * @param carbonCard 纪念卡数据
     * @return 结果
     */
    @Override
    public int insertCarbonCard(CarbonCard carbonCard)
    {
        return carbonCardMapper.insertCarbonCard(carbonCard);
    }

    /**
     * 修改纪念卡数据
     *
     * @param carbonCard 纪念卡数据
     * @return 结果
     */
    @Override
    public int updateCarbonCard(CarbonCard carbonCard)
    {
        return carbonCardMapper.updateCarbonCard(carbonCard);
    }

    /**
     * 批量删除纪念卡数据
     *
     * @param ids 需要删除的纪念卡数据主键
     * @return 结果
     */
    @Override
    public int deleteCarbonCardByIds(Long[] ids)
    {
        return carbonCardMapper.deleteCarbonCardByIds(ids);
    }

    /**
     * 删除纪念卡数据信息
     *
     * @param id 纪念卡数据主键
     * @return 结果
     */
    @Override
    public int deleteCarbonCardById(Long id)
    {
        return carbonCardMapper.deleteCarbonCardById(id);
    }

    @Override
    public AjaxResult uploadCardImg(MultipartFile file) {
        return null;
    }
}
