package com.ruoyi.souvenir.service.card.impl;

import java.util.List;

import com.ruoyi.carbon.domain.carbon.CarbonCollect;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.souvenir.service.card.ICarbonCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.souvenir.mapper.CarbonCollectMapper;

/**
 * 收藏Service业务层处理
 *
 * @author 张宇豪
 * @date 2023-08-21
 */
@Service
public class CarbonCollectServiceImpl implements ICarbonCollectService
{
    @Autowired
    private CarbonCollectMapper carbonCollectMapper;

    /**
     * 查询收藏
     *
     * @param id 收藏主键
     * @return 收藏
     */
    @Override
    public CarbonCollect selectCarbonCollectById(Long id)
    {
        return carbonCollectMapper.selectCarbonCollectById(id);
    }

    /**
     * 查询收藏列表
     *
     * @param carbonCollect 收藏
     * @return 收藏
     */
    @Override
    public List<CarbonCollect> selectCarbonCollectList(CarbonCollect carbonCollect)
    {
        return carbonCollectMapper.selectCarbonCollectList(carbonCollect);
    }

    /**
     * 新增收藏
     *
     * @param carbonCollect 收藏
     * @return 结果
     */
    @Override
    public int insertCarbonCollect(CarbonCollect carbonCollect)
    {
        carbonCollect.setCreateTime(DateUtils.getNowDate());
        return carbonCollectMapper.insertCarbonCollect(carbonCollect);
    }

    /**
     * 修改收藏
     *
     * @param carbonCollect 收藏
     * @return 结果
     */
    @Override
    public int updateCarbonCollect(CarbonCollect carbonCollect)
    {
        return carbonCollectMapper.updateCarbonCollect(carbonCollect);
    }

    /**
     * 批量删除收藏
     *
     * @param ids 需要删除的收藏主键
     * @return 结果
     */
    @Override
    public int deleteCarbonCollectByIds(Long[] ids)
    {
        return carbonCollectMapper.deleteCarbonCollectByIds(ids);
    }

    /**
     * 删除收藏信息
     *
     * @param id 收藏主键
     * @return 结果
     */
    @Override
    public int deleteCarbonCollectById(Long id)
    {
        return carbonCollectMapper.deleteCarbonCollectById(id);
    }

    @Override
    public CarbonCollect selectCarbonCollectByEnterpriseIdAndCardId(Long enterprise_id, Long card_id) {
        return carbonCollectMapper.selectCarbonCollectByEnterpriseIdAndCardId(enterprise_id,card_id);
    }
}
