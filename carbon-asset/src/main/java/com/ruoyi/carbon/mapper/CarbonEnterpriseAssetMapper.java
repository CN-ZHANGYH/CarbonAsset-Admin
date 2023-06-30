package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonEnterpriseAsset;

import java.util.List;

/**
 * 企业出售资产Mapper接口
 * 
 * @author 张宇豪
 * @date 2023-07-08
 */
public interface CarbonEnterpriseAssetMapper 
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

}
