package com.ruoyi.souvenir.service.card;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.souvenir.domain.CarbonCard;
import org.springframework.web.multipart.MultipartFile;

/**
 * 纪念卡数据Service接口
 *
 * @author 张宇豪
 * @date 2023-07-28
 */
public interface ICarbonCardService
{
    /**
     * 查询纪念卡数据
     *
     * @param id 纪念卡数据主键
     * @return 纪念卡数据
     */
    public CarbonCard selectCarbonCardById(Long id);

    /**
     * 查询纪念卡数据列表
     *
     * @param carbonCard 纪念卡数据
     * @return 纪念卡数据集合
     */
    public List<CarbonCard> selectCarbonCardList(CarbonCard carbonCard);

    /**
     * 新增纪念卡数据
     *
     * @param carbonCard 纪念卡数据
     * @return 结果
     */
    public int insertCarbonCard(CarbonCard carbonCard);

    /**
     * 修改纪念卡数据
     *
     * @param carbonCard 纪念卡数据
     * @return 结果
     */
    public int updateCarbonCard(CarbonCard carbonCard);

    /**
     * 批量删除纪念卡数据
     *
     * @param ids 需要删除的纪念卡数据主键集合
     * @return 结果
     */
    public int deleteCarbonCardByIds(Long[] ids);

    /**
     * 删除纪念卡数据信息
     *
     * @param id 纪念卡数据主键
     * @return 结果
     */
    public int deleteCarbonCardById(Long id);

    public AjaxResult uploadCardImg(MultipartFile file);
}
