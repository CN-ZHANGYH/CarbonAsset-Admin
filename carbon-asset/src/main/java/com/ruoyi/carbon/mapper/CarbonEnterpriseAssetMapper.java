package com.ruoyi.carbon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.carbon.domain.carbon.CarbonEnterpriseAsset;
import com.ruoyi.carbon.domain.vo.AssetVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 企业出售资产Mapper接口
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
public interface CarbonEnterpriseAssetMapper extends BaseMapper<CarbonEnterpriseAsset>
{
    /**
     * 查询企业出售资产
     *
     * @param assetId 企业出售资产主键
     * @return 企业出售资产
     */
    public CarbonEnterpriseAsset selectCarbonEnterpriseAssetByAssetId(Long assetId);

    /**
     * 查询企业出售资产列表
     *
     * @param carbonEnterpriseAsset 企业出售资产
     * @return 企业出售资产集合
     */
    public List<CarbonEnterpriseAsset> selectCarbonEnterpriseAssetList(CarbonEnterpriseAsset carbonEnterpriseAsset);

    /**
     * 新增企业出售资产
     *
     * @param carbonEnterpriseAsset 企业出售资产
     * @return 结果
     */
    public int insertCarbonEnterpriseAsset(CarbonEnterpriseAsset carbonEnterpriseAsset);

    /**
     * 修改企业出售资产
     *
     * @param carbonEnterpriseAsset 企业出售资产
     * @return 结果
     */
    public int updateCarbonEnterpriseAsset(CarbonEnterpriseAsset carbonEnterpriseAsset);

    /**
     * 删除企业出售资产
     *
     * @param assetId 企业出售资产主键
     * @return 结果
     */
    public int deleteCarbonEnterpriseAssetByAssetId(Long assetId);

    /**
     * 批量删除企业出售资产
     *
     * @param assetIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarbonEnterpriseAssetByAssetIds(Long[] assetIds);

    public List<CarbonEnterpriseAsset> selectCarbonEnterpriseAssetByAddress(String enterpriseAddress);

    @Select("\n" +
            "SELECT\n" +
            "    CASE WHEN WEEKDAY(date_table.date) = 0 THEN '周一'\n" +
            "         WHEN WEEKDAY(date_table.date) = 1 THEN '周二'\n" +
            "         WHEN WEEKDAY(date_table.date) = 2 THEN '周三'\n" +
            "         WHEN WEEKDAY(date_table.date) = 3 THEN '周四'\n" +
            "         WHEN WEEKDAY(date_table.date) = 4 THEN '周五'\n" +
            "         WHEN WEEKDAY(date_table.date) = 5 THEN '周六'\n" +
            "         WHEN WEEKDAY(date_table.date) = 6 THEN '周日'\n" +
            "        END AS week,\n" +
            "    IFNULL(SUM(carbon_enterprise_asset.asset_quantity), 0) AS value\n" +
            "FROM\n" +
            "    (SELECT\n" +
            "             CURDATE() - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date\n" +
            "     FROM\n" +
            "         (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a\n" +
            "             CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b\n" +
            "             CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c) AS date_table\n" +
            "        LEFT JOIN carbon_enterprise_asset ON DATE(carbon_enterprise_asset.time) = DATE(date_table.date)\n" +
            "WHERE\n" +
            "        date_table.date >= CURDATE() - INTERVAL 6 DAY\n" +
            "GROUP BY date_table.date\n" +
            "ORDER BY date_table.date ASC;\n")
    public List<AssetVo> selectEnterpriseAssetByListOfWeek();


    public List<CarbonEnterpriseAsset> queryEnterpriseNewSellerAssetLimitFive(String address);

}
