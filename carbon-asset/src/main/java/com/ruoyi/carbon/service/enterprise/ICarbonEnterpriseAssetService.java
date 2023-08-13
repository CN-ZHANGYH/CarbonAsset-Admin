package com.ruoyi.carbon.service.enterprise;

import com.ruoyi.carbon.domain.carbon.CarbonEnterpriseAsset;
import com.ruoyi.carbon.domain.vo.AssetVo;
import com.ruoyi.carbon.domain.vo.TxDataVo;
import com.ruoyi.common.core.domain.AjaxResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 企业出售资产Service接口
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
public interface ICarbonEnterpriseAssetService
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
     * 批量删除企业出售资产
     *
     * @param assetIds 需要删除的企业出售资产主键集合
     * @return 结果
     */
    public int deleteCarbonEnterpriseAssetByAssetIds(Long[] assetIds);

    /**
     * 删除企业出售资产信息
     *
     * @param assetId 企业出售资产主键
     * @return 结果
     */
    public int deleteCarbonEnterpriseAssetByAssetId(Long assetId);


    public List<CarbonEnterpriseAsset> selectCarbonEnterpriseAssetByAddress(String address);

    public List<AssetVo> selectEnterpriseAssetByListOfWeek();


    AjaxResult queryEnterpriseNewSellerAssetLimitFive(String address);

    List<TxDataVo> selectEnterpriseAssetSellList(Integer enterpriseId);
}
