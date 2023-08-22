package com.ruoyi.souvenir.service.card;

import com.ruoyi.carbon.domain.carbon.CarbonCollect;

import java.util.List;

/**
 * 收藏Service接口
 *
 * @author 张宇豪
 * @date 2023-08-21
 */
public interface ICarbonCollectService
{
    /**
     * 查询收藏
     *
     * @param id 收藏主键
     * @return 收藏
     */
    public CarbonCollect selectCarbonCollectById(Long id);

    /**
     * 查询收藏列表
     *
     * @param carbonCollect 收藏
     * @return 收藏集合
     */
    public List<CarbonCollect> selectCarbonCollectList(CarbonCollect carbonCollect);

    /**
     * 新增收藏
     *
     * @param carbonCollect 收藏
     * @return 结果
     */
    public int insertCarbonCollect(CarbonCollect carbonCollect);

    /**
     * 修改收藏
     *
     * @param carbonCollect 收藏
     * @return 结果
     */
    public int updateCarbonCollect(CarbonCollect carbonCollect);

    /**
     * 批量删除收藏
     *
     * @param ids 需要删除的收藏主键集合
     * @return 结果
     */
    public int deleteCarbonCollectByIds(Long[] ids);

    /**
     * 删除收藏信息
     *
     * @param id 收藏主键
     * @return 结果
     */
    public int deleteCarbonCollectById(Long id);

    /**
     * 根据企业的ID和纪念卡的ID查询
     *
     * @param enterprise_id 企业的ID
     * @param card_id       纪念卡的ID
     * @return 返沪CarbonCollect
     */
    public CarbonCollect selectCarbonCollectByEnterpriseIdAndCardId(Long enterprise_id, Long card_id);
}
