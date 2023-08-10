package com.ruoyi.carbon.service.resources.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.carbon.domain.carbon.CarbonEmissionResource;
import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.domain.carbon.CarbonQualification;
import com.ruoyi.carbon.domain.vo.*;
import com.ruoyi.carbon.factory.RawContractLoaderFactory;
import com.ruoyi.carbon.mapper.CarbonEmissionResourceMapper;
import com.ruoyi.carbon.mapper.CarbonEnterpriseMapper;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceEnterpriseEmissionInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceUploadEnterpriseEmissionInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceVerifyEnterpriseEmissionInputBO;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseAssetService;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseService;
import com.ruoyi.carbon.service.enterprise.ICarbonQualificationService;
import com.ruoyi.carbon.service.regulator.ICarbonRegulatorService;
import com.ruoyi.carbon.service.resources.ICarbonEmissionResourceService;
import com.ruoyi.carbon.service.transaction.ICarbonTransactionService;
import com.ruoyi.carbon.utils.BlockTimestampUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 企业排放资源Service业务层处理
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@Service
public class CarbonEmissionResourceServiceImpl implements ICarbonEmissionResourceService
{
    @Autowired
    private ICarbonEnterpriseService enterpriseService;

    @Autowired
    private ICarbonRegulatorService regulatorService;


    @Autowired
    private CarbonEnterpriseMapper enterpriseMapper;

    @Autowired
    private ICarbonQualificationService qualificationService;

    @Autowired
    private RawContractLoaderFactory rawContractLoaderFactory;

    @Autowired
    private CarbonEmissionResourceMapper carbonEmissionResourceMapper;

    @Autowired
    private ICarbonTransactionService transactionService;

    @Autowired
    private ICarbonEnterpriseAssetService enterpriseAssetService;

    /**
     * 查询企业排放资源
     *
     * @param emissionId 企业排放资源主键
     * @return 企业排放资源
     */
    @Override
    public CarbonEmissionResource selectCarbonEmissionResourceByEmissionId(Long emissionId)
    {
        return carbonEmissionResourceMapper.selectCarbonEmissionResourceByEmissionId(emissionId);
    }

    /**
     * 查询企业排放资源列表
     *
     * @param carbonEmissionResource 企业排放资源
     * @return 企业排放资源
     */
    @Override
    public List<CarbonEmissionResource> selectCarbonEmissionResourceList(CarbonEmissionResource carbonEmissionResource)
    {
        return carbonEmissionResourceMapper.selectCarbonEmissionResourceList(carbonEmissionResource);
    }

    /**
     * 新增企业排放资源
     *
     * @param carbonEmissionResource 企业排放资源
     * @return 结果
     */
    @Override
    public int insertCarbonEmissionResource(CarbonEmissionResource carbonEmissionResource)
    {
        return carbonEmissionResourceMapper.insertCarbonEmissionResource(carbonEmissionResource);
    }

    /**
     * 修改企业排放资源
     *
     * @param carbonEmissionResource 企业排放资源
     * @return 结果
     */
    @Override
    public int updateCarbonEmissionResource(CarbonEmissionResource carbonEmissionResource)
    {
        return carbonEmissionResourceMapper.updateCarbonEmissionResource(carbonEmissionResource);
    }

    /**
     * 批量删除企业排放资源
     *
     * @param emissionIds 需要删除的企业排放资源主键
     * @return 结果
     */
    @Override
    public int deleteCarbonEmissionResourceByEmissionIds(Long[] emissionIds)
    {
        return carbonEmissionResourceMapper.deleteCarbonEmissionResourceByEmissionIds(emissionIds);
    }

    /**
     * 删除企业排放资源信息
     *
     * @param emissionId 企业排放资源主键
     * @return 结果
     */
    @Override
    public int deleteCarbonEmissionResourceByEmissionId(Long emissionId)
    {
        return carbonEmissionResourceMapper.deleteCarbonEmissionResourceByEmissionId(emissionId);
    }

    /**
     * 企业申请碳排放
     *
     * @param resourceVo 申请实体类
     * @return 返回结果
     */
    @Override
    @Transactional
    public AjaxResult uploadEmissionResource(ResourceVo resourceVo) {
        if (StringUtils.isEmpty(resourceVo.getEnterpriseAddress())){
            return AjaxResult.error("地址不能为空");
        }

        CarbonEnterprise enterprise = enterpriseService.selectByAddress(resourceVo.getEnterpriseAddress());
        if (Objects.isNull(enterprise)){
            return AjaxResult.error("该用户不存在");
        }

        CarbonAssetServiceUploadEnterpriseEmissionInputBO uploadInfo = new CarbonAssetServiceUploadEnterpriseEmissionInputBO();
        uploadInfo.set_enterpriseAddr(resourceVo.getEnterpriseAddress());
        uploadInfo.set_emissionEmission(resourceVo.getEmissionEmission());
        uploadInfo.set_description(resourceVo.getDescription());
        uploadInfo.set_emissionWay(resourceVo.getEmissionWay());
        List<Object> params = uploadInfo.toArgs();
        try {
            TransactionResponse transactionResponse = rawContractLoaderFactory
                    .GetTransactionResponse(enterprise.getPriavateKey(), "uploadEnterpriseEmission", params);
            if (transactionResponse.getReturnMessage().equals("Success")){
                JSONArray result = JSON.parseArray(transactionResponse.getValues()).getJSONArray(1);
                CarbonEmissionResource emissionResource = new CarbonEmissionResource();
                emissionResource.setEmissionId(result.getLongValue(0));
                emissionResource.setEnterpriseId(result.getLongValue(1));
                emissionResource.setEnterpriseAddress(result.getString(2));
                emissionResource.setEmissions(result.getBigInteger(3));
                emissionResource.setDescription(result.getString(4));
                emissionResource.setEmissionWay(result.getString(5));
                emissionResource.setIsApprove(result.getBoolean(6) ? 1 : 0);
                emissionResource.setResourceType(resourceVo.getResourceType());

                int status = carbonEmissionResourceMapper.insertCarbonEmissionResource(emissionResource);
                if (status > 0){
                    return AjaxResult.success("申请成功");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error("申请失败");
    }

    /**
     * 企业碳排放
     *
     * @param emissionVo 碳排放资源实体类
     * @return 返回结果
     *
     * @desc
     * 这里的业务说明： 用户需要根据资源的ID进行碳排放，必须当前的资源ID是申请了100kg进行资源的碳排放，在通过申请之后才是可以正确的排放资源，
     * 这里需要涉及的是企业的信息是否需要更新，比如当前的企业是排放完成以后，总的排放量是否会减少，以及完成的排放量是否会增加，如果会，则是需要涉及到更新
     * 企业的信息表，这里是需要进行事务的管理的，比如如果排放失败是需要回滚业务的数据，排放成功则是需要更新企业的表，并且提交事务。
     */
    @Override
    public AjaxResult enterpriseEmission(EmissionVo emissionVo) {
        if (StringUtils.isEmpty(emissionVo.getEnterpriseAddress()))
        {
            return AjaxResult.error("当前用户不能为空");
        }

        CarbonEmissionResource emissionResource = this.selectCarbonEmissionResourceByEmissionId(emissionVo.getEmissionId());
        if (Objects.isNull(emissionResource))
        {
            return AjaxResult.error("当前资源订单不存在");
        }

        CarbonEnterprise enterprise = enterpriseService.selectByAddress(emissionVo.getEnterpriseAddress());
        if (Objects.isNull(enterprise))
        {
            return AjaxResult.error("当前企业不存在");
        }

        Integer qualificationId = enterprise.getQualificationId();
        CarbonQualification qualification = qualificationService
                .selectCarbonQualificationByQualificationId(Long.valueOf(qualificationId));
        if (Objects.isNull(qualification))
        {
            return AjaxResult.error("当前企业未认证");
        }
        CarbonAssetServiceEnterpriseEmissionInputBO emissionInfo = new CarbonAssetServiceEnterpriseEmissionInputBO();
        emissionInfo.set_emmissionid(BigInteger.valueOf(emissionVo.getEmissionId()));
        emissionInfo.set_enterpriseAddr(emissionVo.getEnterpriseAddress());
        emissionInfo.set_emissionEmission(emissionVo.getEmissionLimit());
        List<Object> params = emissionInfo.toArgs();
        try
        {
            TransactionResponse transactionResponse = rawContractLoaderFactory
                    .GetTransactionResponse(enterprise.getPriavateKey(), "enterpriseEmission", params);
            if (transactionResponse.getReturnMessage().equals("Success"))
            {
                Boolean flag = (Boolean) JSON.parseArray(transactionResponse.getValues()).get(1);
                if (flag)
                {
                    // TODO: 合约需要返回时间
                    emissionResource.setEmissionTime(BlockTimestampUtil.convert(System.currentTimeMillis()));
                    this.carbonEmissionResourceMapper.updateCarbonEmissionResource(emissionResource);
                    UpdateEnterpriseInfo(emissionVo, enterprise);

                    // 更新一下碳额度
                    BigInteger oldEmissionLimit = qualification.getQualificationEmissionLimit();
                    qualification.setQualificationEmissionLimit(oldEmissionLimit.subtract(emissionVo.getEmissionLimit()));
                    qualificationService.updateCarbonQualification(qualification);
                    return AjaxResult.success("排放成功");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error("排放失败");
    }

    @Async
    public void UpdateEnterpriseInfo(EmissionVo emissionVo, CarbonEnterprise enterprise) {
        BigInteger totalEmission = enterprise.getEnterpriseTotalEmission();
        BigInteger newTotalEmission = totalEmission.subtract(emissionVo.getEmissionLimit());
        enterprise.setEnterpriseTotalEmission(newTotalEmission);
        BigInteger overEmission = enterprise.getEnterpriseOverEmission();
        BigInteger newOverEmission = overEmission.add(emissionVo.getEmissionLimit());
        enterprise.setEnterpriseOverEmission(newOverEmission);
        enterpriseMapper.updateCarbonEnterprise(enterprise);
    }

    /**
     * 审核企业的碳排放
     *
     * @param verifyVo 审核参数的实体类
     * @return
     */
    @Override
    public AjaxResult verifyEnterpriseEmission(VerifyVo verifyVo) {
        if (StringUtils.isEmpty(verifyVo.getRegulatorAddress()) || StringUtils.isEmpty(verifyVo.getEnterpriseAddress()))
        {
            return AjaxResult.error("用户不能为空");
        }

        // 切换为机构地址的私钥
        String privateKey = rawContractLoaderFactory
                .GetRegulatorPrivateKey(verifyVo.getRegulatorAddress())
                .getPrivateKey();

        CarbonEmissionResource emissionResource = this.selectCarbonEmissionResourceByEmissionId(verifyVo.getEmissionId());
        if (Objects.isNull(emissionResource))
        {
            return AjaxResult.error("没有该申请记录");
        }
        if (emissionResource.getIsApprove() == 1)
        {
            return AjaxResult.error("当前已完成审核");
        }
        CarbonAssetServiceVerifyEnterpriseEmissionInputBO verifyInfo = new CarbonAssetServiceVerifyEnterpriseEmissionInputBO();
        verifyInfo.set_regularAddress(verifyVo.getRegulatorAddress());
        verifyInfo.set_enterpriseAddr(verifyVo.getEnterpriseAddress());
        verifyInfo.set_emmissionid(BigInteger.valueOf(verifyVo.getEmissionId()));
        verifyInfo.set_isApprove(verifyVo.getIsApprove());
        List<Object> params = verifyInfo.toArgs();

        try {
            TransactionResponse transactionResponse = rawContractLoaderFactory
                    .GetTransactionResponse(privateKey, "verifyEnterpriseEmission", params);
            if (transactionResponse.getReturnMessage().equals("Success")){
                Boolean flag = (Boolean) JSON.parseArray(transactionResponse.getValues()).get(1);
                if (flag)
                {
                    emissionResource.setIsApprove(1);
                    carbonEmissionResourceMapper.updateCarbonEmissionResource(emissionResource);
                    return AjaxResult.success("审核成功");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error("审核失败");
    }

    @Override
    public ArrayList<CarbonEmissionResource> selectEmissionResourceByAddress(String enterpriseAddress) {
        return carbonEmissionResourceMapper.selectEmissionResourceByAddress(enterpriseAddress);
    }

    @Override
    public List<CarbonEmissionResource> selectIsNotVerifyList() {
        List<CarbonEmissionResource> emissionResources = carbonEmissionResourceMapper.selectEmissionResourceList();
        System.out.println(emissionResources.size());
        return emissionResources.stream()
                .filter(emissionResource -> emissionResource.getIsApprove() == 0)
                .collect(Collectors.toList());
    }

    @Override
    public AjaxResult selectEmissionAndTxAndApplyAndQuaList() {
        // 查询周一到周日每天的碳排放总量
        List<EmissionResourceVo> emissionResourceVos = carbonEmissionResourceMapper.selectEmissionResourceOfWeek();
        List<Integer> resourceValues = emissionResourceVos.stream().map(EmissionResourceVo::getValue).collect(Collectors.toList());
        // 查询近一周的每天交易总量
        List<TransactionVo> transactionVos = transactionService.selectTransactionOfWeek();
        List<Integer> transactionValues = transactionVos.stream().map(TransactionVo::getValue).collect(Collectors.toList());
        // 查询近一周的每天认证情况
        List<QualificationVo> qualificationVos = qualificationService.selectQualificationIsVerifyOfWeeek();
        List<Integer> qualificationValues = qualificationVos.stream().map(QualificationVo::getValue).collect(Collectors.toList());
        // 查询近一周的每天出售情况
        List<AssetVo> assetVos = enterpriseAssetService.selectEnterpriseAssetByListOfWeek();
        List<Integer> assetValues = assetVos.stream().map(AssetVo::getValue).collect(Collectors.toList());

        ArrayList<List<Integer>> arrayList = new ArrayList<>();
        arrayList.add(resourceValues);
        arrayList.add(transactionValues);
        arrayList.add(qualificationValues);
        arrayList.add(assetValues);

        return AjaxResult.success(arrayList);
    }

    @Override
    public List<CarbonEmissionResource> selectEnterpriseIsNotApplyEmissionResource(String enterprise) {
        CarbonEnterprise carbonEnterprise = enterpriseService.selectByEnterpriseName(enterprise);
        if (Objects.isNull(carbonEnterprise)){
            return null;
        }
        List<CarbonEmissionResource> carbonEmissionResources = carbonEmissionResourceMapper.selectEmissionResourceByEnterpriseId(carbonEnterprise.getEnterpriseId());
        System.out.println(carbonEmissionResources.size());
        return carbonEmissionResources.stream()
                .filter(carbonEmissionResource -> carbonEmissionResource.getIsApprove() != 1)
                .collect(Collectors.toList());

    }

    @Override
    public List<CarbonEmissionResource> selectEnterpriseIsApplyEmissioResource(String enterprise) {
        CarbonEnterprise carbonEnterprise = enterpriseService.selectByEnterpriseName(enterprise);
        if (Objects.isNull(carbonEnterprise)){
            return null;
        }
        List<CarbonEmissionResource> carbonEmissionResources = carbonEmissionResourceMapper.selectEmissionResourceByEnterpriseId(carbonEnterprise.getEnterpriseId());
        System.out.println(carbonEmissionResources.size());
        return carbonEmissionResources.stream()
                .filter(carbonEmissionResource -> carbonEmissionResource.getIsApprove() == 1)
                .collect(Collectors.toList());
    }
}
