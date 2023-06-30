package com.ruoyi.carbon.service.enterprise;

import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.domain.vo.BuyVo;
import com.ruoyi.carbon.domain.vo.SellVo;
import com.ruoyi.common.core.domain.AjaxResult;

import java.math.BigInteger;
import java.util.List;

/**
 * 企业信息Service接口
 *
 * @author ruoyi
 * @date 2023-07-08
 */
public interface ICarbonEnterpriseService
{
    /**
     * 查询企业信息
     *
     * @param enterpriseId 企业信息主键
     * @return 企业信息
     */
    public CarbonEnterprise selectCarbonEnterpriseByEnterpriseId(Long enterpriseId);

    /**
     * 查询企业信息列表
     *
     * @param carbonEnterprise 企业信息
     * @return 企业信息集合
     */
    public List<CarbonEnterprise> selectCarbonEnterpriseList(CarbonEnterprise carbonEnterprise);

    /**
     * 新增企业信息
     *
     * @param carbonEnterprise 企业信息
     * @return 结果
     */
    public int insertCarbonEnterprise(CarbonEnterprise carbonEnterprise) throws Exception;

    /**
     * 修改企业信息
     *
     * @param carbonEnterprise 企业信息
     * @return 结果
     */
    public int updateCarbonEnterprise(CarbonEnterprise carbonEnterprise);

    /**
     * 批量删除企业信息
     *
     * @param enterpriseIds 需要删除的企业信息主键集合
     * @return 结果
     */
    public int deleteCarbonEnterpriseByEnterpriseIds(Long[] enterpriseIds);

    /**
     * 删除企业信息信息
     *
     * @param enterpriseId 企业信息主键
     * @return 结果
     */
    public int deleteCarbonEnterpriseByEnterpriseId(Long enterpriseId);


    public CarbonEnterprise selectByAddress(String address);

    public CarbonEnterprise selectByEnterpriseName(String enterpriseName);

    public AjaxResult sellEnterpriseAsset(SellVo sellVo);

    public AjaxResult buyEnterpriseAsset(BuyVo buyVo);

    public List<CarbonEnterprise> selectEnterpriseListByAddress(String buyAddress,String sellerAddress);

    public int updateCarbonEnterpriseBalance(CarbonEnterprise carbonEnterprise);

    public AjaxResult selectEnterpriseInfoByBlockChain(String address);

    public AjaxResult updateTotalEmission(String address, BigInteger count) throws Exception;


}
