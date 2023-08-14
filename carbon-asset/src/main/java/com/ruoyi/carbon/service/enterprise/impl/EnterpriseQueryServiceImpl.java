package com.ruoyi.carbon.service.enterprise.impl;


import com.ruoyi.carbon.domain.carbon.*;
import com.ruoyi.carbon.domain.vo.EnterpriseVo;
import com.ruoyi.carbon.mapper.CarbonEmissionResourceMapper;
import com.ruoyi.carbon.mapper.CarbonEnterpriseMapper;
import com.ruoyi.carbon.mapper.CarbonQualificationMapper;
import com.ruoyi.carbon.service.enterprise.EnterpriseQueryService;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseAssetService;
import com.ruoyi.carbon.service.resources.ICarbonEmissionResourceService;
import com.ruoyi.carbon.service.transaction.ICarbonTransactionService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EnterpriseQueryServiceImpl implements EnterpriseQueryService {

    @Autowired
    private CarbonEnterpriseMapper carbonEnterpriseMapper;

    @Autowired
    private CarbonEmissionResourceMapper carbonEmissionResourceMapper;

    @Autowired
    private CarbonQualificationMapper qualificationMapper;


    @Autowired
    private ICarbonTransactionService transactionService;


    @Autowired
    private ICarbonEnterpriseAssetService enterpriseAssetService;

    @Autowired
    private ICarbonEmissionResourceService emissionResourceService;



    /**
     * 查询企业用户的链上实时的信息
     *
     * @param enterpriseName 企业的的账户地址
     * @return 返回结果
     */
    @Override
    public AjaxResult queryEnterpriseInfo(String enterpriseName) {
        if (StringUtils.isEmpty(enterpriseName))
        {
            return AjaxResult.error("当前的用户地址为空");
        }
        EnterpriseVo enterprise = carbonEnterpriseMapper.selectUserWithEnterpriseEnterpriseName(enterpriseName);
        if (Objects.isNull(enterprise))
        {
            return AjaxResult.error("查询失败");
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("enterprise",enterprise);
        return ajax;
    }

    /**
     * 企业通过自己的资质ID进行查询
     *
     * @param enterprise 资质ID
     * @return 返回结果
     */
    @Override
    public AjaxResult queryQualificationInfo(String enterprise) {
        if (StringUtils.isEmpty(enterprise))
        {
            return AjaxResult.error("当前的用户地址为空");
        }
        EnterpriseVo enterpriseVo = carbonEnterpriseMapper.selectUserWithEnterpriseEnterpriseName(enterprise);
        if (Objects.isNull(enterpriseVo))
        {
            return AjaxResult.error("该企业不存在");
        }
        CarbonQualification qualification = qualificationMapper
                .selectCarbonQualificationByQualificationId(Long.valueOf(enterpriseVo.getQualification_id()));
        if (Objects.isNull(qualification))
        {
            return AjaxResult.error("当前企业未认证");
        }
        return AjaxResult.success("qualification",qualification);
    }

    /**
     * 查询企业的交易历史记录
     * @param enterpriseName 企业名称
     * @return 返回结果
     */
    @Override
    public AjaxResult queryEnterpriseTxList(String enterpriseName) {
        // 通过企业的名称查询企业的交易历史
        CarbonEnterprise enterprise = carbonEnterpriseMapper.selectCarbonEnterpriseByName(enterpriseName);
        if (Objects.isNull(enterprise))
        {
            return AjaxResult.error("该企业不存在");
        }
        ArrayList<CarbonTransaction> txList = transactionService.selectTransactionListByAddress(enterprise.getEnterpriseAddress());
        if (txList.size() > 0){
            AjaxResult ajax = AjaxResult.success();
            ajax.put("data",txList);
            return ajax;
        }
        return AjaxResult.error("该企业还未交易");
    }

    @Override
    public AjaxResult queryEnterpriseErList(String enterpriseName) {
        CarbonEnterprise enterprise = carbonEnterpriseMapper.selectCarbonEnterpriseByName(enterpriseName);
        if (Objects.isNull(enterprise))
        {
            return AjaxResult.error("该企业不存在");
        }
        CarbonEmissionResource carbonEmissionResource = new CarbonEmissionResource();
        carbonEmissionResource.setEnterpriseId(Long.valueOf(enterprise.getEnterpriseId()));
        List<CarbonEmissionResource> erList = emissionResourceService.selectCarbonEmissionResourceList(carbonEmissionResource);
        if (erList.size() > 0){
            AjaxResult ajax = AjaxResult.success();
            ajax.put("data",erList);
            return ajax;
        }
        return AjaxResult.error("该企业还未排放");
    }

    @Override
    public AjaxResult queryEnterpriseAList(String enterprise) {
        if (StringUtils.isEmpty(enterprise))
        {
            return AjaxResult.error("当前用户地址为空");
        }
        CarbonEnterprise carbonEnterprise = carbonEnterpriseMapper.selectCarbonEnterpriseByName(enterprise);
        if (Objects.isNull(carbonEnterprise))
        {
            return AjaxResult.error("该企业不存在");
        }
        List<CarbonEnterpriseAsset> enterpriseAssets = enterpriseAssetService.selectCarbonEnterpriseAssetByAddress(carbonEnterprise.getEnterpriseAddress());
        if (Objects.isNull(enterpriseAssets))
        {
            return AjaxResult.error("当前企业还没出售资产");
        }
        AjaxResult ajax = AjaxResult.success("查询成功");
        ajax.put("data",enterpriseAssets);
        return ajax;
    }

    @Override
    public AjaxResult selectEnterpriseWorkProgress(String enterprise) {
        if (StringUtils.isEmpty(enterprise))
        {
            return AjaxResult.error("企业不能为空");
        }
        CarbonEnterprise carbonEnterprise = carbonEnterpriseMapper.selectCarbonEnterpriseByName(enterprise);
        if (Objects.isNull(carbonEnterprise))
        {
            return AjaxResult.error("当前的企业不存在");
        }
        // 查询当前的所有碳排放审批进度
        double applyEmissionResourceProgress  = carbonEmissionResourceMapper
                .selectEnterpriseIsApplyEmissionResourceProgress(carbonEnterprise.getEnterpriseId());

        // 查询当前的所有出售的碳额度售空进度
        double sellerAssetProgress = enterpriseAssetService
                .selectSellerListIsOverProgress(carbonEnterprise.getEnterpriseId());

        // 查询所有的已完成碳排放的进度
        double overEmissionResourceProgress = carbonEmissionResourceMapper
                .selectOverEmissionResourceProgress(carbonEnterprise.getEnterpriseId());

        ArrayList<Double> results = new ArrayList<>();
        results.add(applyEmissionResourceProgress);
        results.add(sellerAssetProgress);
        results.add(overEmissionResourceProgress);
        return AjaxResult.success().put("data",results);
    }

    @Override
    public AjaxResult selectTotalTxAndEmission() {
        // 查询近一年的每一个月的所有碳排放数据
        List<Integer> emData = carbonEmissionResourceMapper.selectEmissionResourceMonthOfYear();
        // 查询近一年的每一个月的总交易量
        List<Integer> txData = transactionService.selectTxMonthOfYear();
        // 查看近一年的每一个月的企业认证总数
        List<Integer> quaData = qualificationMapper.selectEnterpriseVerifyMonthOfYear();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("emData",emData);
        ajax.put("txData",txData);
        ajax.put("quData",quaData);
        return ajax;
    }


}
