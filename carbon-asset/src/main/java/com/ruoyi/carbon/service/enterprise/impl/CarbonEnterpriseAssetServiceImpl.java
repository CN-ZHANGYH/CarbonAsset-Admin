package com.ruoyi.carbon.service.enterprise.impl;

import com.ruoyi.carbon.domain.carbon.CarbonEnterpriseAsset;
import com.ruoyi.carbon.domain.vo.AssetVo;
import com.ruoyi.carbon.domain.vo.TxDataVo;
import com.ruoyi.carbon.mapper.CarbonEnterpriseAssetMapper;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseAssetService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业出售资产Service业务层处理
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@Service
public class CarbonEnterpriseAssetServiceImpl implements ICarbonEnterpriseAssetService
{
    @Autowired
    private CarbonEnterpriseAssetMapper carbonEnterpriseAssetMapper;

    /**
     * 查询企业出售资产
     *
     * @param assetId 企业出售资产主键
     * @return 企业出售资产
     */
    @Override
    public CarbonEnterpriseAsset selectCarbonEnterpriseAssetByAssetId(Long assetId)
    {
        return carbonEnterpriseAssetMapper.selectCarbonEnterpriseAssetByAssetId(assetId);
    }

    /**
     * 查询企业出售资产列表
     *
     * @param carbonEnterpriseAsset 企业出售资产
     * @return 企业出售资产
     */
    @Override
    public List<CarbonEnterpriseAsset> selectCarbonEnterpriseAssetList(CarbonEnterpriseAsset carbonEnterpriseAsset)
    {
        return carbonEnterpriseAssetMapper.selectCarbonEnterpriseAssetList(carbonEnterpriseAsset);
    }

    /**
     * 新增企业出售资产
     *
     * @param carbonEnterpriseAsset 企业出售资产
     * @return 结果
     */
    @Override
    public int insertCarbonEnterpriseAsset(CarbonEnterpriseAsset carbonEnterpriseAsset)
    {
        return carbonEnterpriseAssetMapper.insertCarbonEnterpriseAsset(carbonEnterpriseAsset);
    }

    /**
     * 修改企业出售资产
     *
     * @param carbonEnterpriseAsset 企业出售资产
     * @return 结果
     */
    @Override
    public int updateCarbonEnterpriseAsset(CarbonEnterpriseAsset carbonEnterpriseAsset)
    {
        return carbonEnterpriseAssetMapper.updateCarbonEnterpriseAsset(carbonEnterpriseAsset);
    }

    /**
     * 批量删除企业出售资产
     *
     * @param assetIds 需要删除的企业出售资产主键
     * @return 结果
     */
    @Override
    public int deleteCarbonEnterpriseAssetByAssetIds(Long[] assetIds)
    {
        return carbonEnterpriseAssetMapper.deleteCarbonEnterpriseAssetByAssetIds(assetIds);
    }

    /**
     * 删除企业出售资产信息
     *
     * @param assetId 企业出售资产主键
     * @return 结果
     */
    @Override
    public int deleteCarbonEnterpriseAssetByAssetId(Long assetId)
    {
        return carbonEnterpriseAssetMapper.deleteCarbonEnterpriseAssetByAssetId(assetId);
    }

    @Override
    public List<CarbonEnterpriseAsset> selectCarbonEnterpriseAssetByAddress(String address) {
        return carbonEnterpriseAssetMapper.selectCarbonEnterpriseAssetByAddress(address);
    }

    @Override
    public List<AssetVo> selectEnterpriseAssetByListOfWeek() {
        return carbonEnterpriseAssetMapper.selectEnterpriseAssetByListOfWeek();
    }

    @Override
    public AjaxResult queryEnterpriseNewSellerAssetLimitFive(String address) {
        List<CarbonEnterpriseAsset> enterpriseAssets = carbonEnterpriseAssetMapper.queryEnterpriseNewSellerAssetLimitFive(address);
        System.out.println(enterpriseAssets);
        if (enterpriseAssets.size() <= 0)
        {
            return AjaxResult.error("最新暂无出售记录");
        }
        AjaxResult ajax = AjaxResult.success();
        return ajax.put("data", enterpriseAssets);
    }

    @Override
    public List<TxDataVo> selectEnterpriseAssetSellList(Integer enterpriseId) {
        return carbonEnterpriseAssetMapper.selectEnterpriseAssetSellList(enterpriseId);
    }

    @Override
    public double selectSellerListIsOverProgress(Integer enterpriseId) {
        return carbonEnterpriseAssetMapper.selectSellerListIsOverProgress(enterpriseId);
    }


}
