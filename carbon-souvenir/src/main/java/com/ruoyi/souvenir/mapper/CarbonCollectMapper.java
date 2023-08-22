package com.ruoyi.souvenir.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonCollect;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 收藏Mapper接口
 *
 * @author 张宇豪
 * @date 2023-08-21
 */
public interface CarbonCollectMapper
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
     * 删除收藏
     *
     * @param id 收藏主键
     * @return 结果
     */
    public int deleteCarbonCollectById(Long id);

    /**
     * 批量删除收藏
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarbonCollectByIds(Long[] ids);

    @Select("SELECT id, enterprise_id, card_id, create_time " +
            "FROM carbon.carbon_collect WHERE enterprise_id = #{enterprise_id} AND card_id = #{card_id} limit 1")
    CarbonCollect selectCarbonCollectByEnterpriseIdAndCardId(@Param("enterprise_id") Long enterprise_id, @Param("card_id") Long card_id);
}
