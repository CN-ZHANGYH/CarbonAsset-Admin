package com.ruoyi.carbon.service.enterprise.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.domain.carbon.CarbonQualification;
import com.ruoyi.carbon.domain.user.UserKey;
import com.ruoyi.carbon.domain.vo.QualificationVo;
import com.ruoyi.carbon.factory.RawContractLoaderFactory;
import com.ruoyi.carbon.mapper.CarbonEnterpriseMapper;
import com.ruoyi.carbon.mapper.CarbonQualificationMapper;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceUploadQualificationInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceVerifyQualificationInputBO;
import com.ruoyi.carbon.service.carbon.CarbonAssetServiceService;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseService;
import com.ruoyi.carbon.service.enterprise.ICarbonQualificationService;
import com.ruoyi.carbon.utils.BlockTimestampUtil;
import com.ruoyi.carbon.utils.TCOSUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * 企业资质信息Service业务层处理
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@Service
public class CarbonQualificationServiceImpl implements ICarbonQualificationService
{
    @Autowired
    private CarbonQualificationMapper carbonQualificationMapper;

    @Autowired
    private RawContractLoaderFactory rawContractLoaderFactory;

    @Autowired
    private ICarbonEnterpriseService enterpriseService;

    @Autowired
    private CarbonAssetServiceService carbonAssetServiceService;

    @Autowired
    private CarbonEnterpriseMapper enterpriseMapper;

    @Autowired
    private TCOSUtil tcosUtil;

    private final String emptyAddress = "0x0000000000000000000000000000000000000000";

    /**
     * 查询企业资质信息
     *
     * @param qualificationId 企业资质信息主键
     * @return 企业资质信息
     */
    @Override
    public CarbonQualification selectCarbonQualificationByQualificationId(Long qualificationId)
    {
        return carbonQualificationMapper.selectCarbonQualificationByQualificationId(qualificationId);
    }

    /**
     * 查询企业资质信息列表
     *
     * @param carbonQualification 企业资质信息
     * @return 企业资质信息
     */
    @Override
    public List<CarbonQualification> selectCarbonQualificationList(CarbonQualification carbonQualification)
    {
        return carbonQualificationMapper.selectCarbonQualificationList(carbonQualification);
    }

    /**
     * 新增企业资质信息
     *
     * @param carbonQualification 企业资质信息
     * @return 结果
     *
     * 功能描述：
     * 1.监管机构可以添加企业的资质信息，并实现手动上传资质信息
     * 2.企业使用地址进行切换私钥的权限，然后调用合约的的上传资质的业务接口进行上传资质
     * 3.企业的资质显示待上链状态，同时企业需要同步到数据库的资质表单中存放备份的数据
     */
    @Override
    @Transactional
    public int insertCarbonQualification(CarbonQualification carbonQualification) throws Exception {
        // 查询是否有该企业
        CarbonEnterprise carbonEnterprise = enterpriseService.selectByEnterpriseName(carbonQualification.getQualificationName());
        if (Objects.isNull(carbonEnterprise)){
            return 0;
        }
        // 企业的资质上链操作
        CarbonAssetServiceUploadQualificationInputBO qualificationInputBO = new CarbonAssetServiceUploadQualificationInputBO();
        qualificationInputBO.set_enterpriseAddress(carbonEnterprise.getEnterpriseAddress());
        qualificationInputBO.set_qualificationName(carbonQualification.getQualificationName());
        qualificationInputBO.set_qualificationContent(carbonQualification.getQualificationContent());
        qualificationInputBO.set_qualificationIndustry(carbonQualification.getQualificationIndustry());
        qualificationInputBO.set_qualificationLeader(carbonQualification.getQualificationLeader());
        qualificationInputBO.set_qualificationUserName(carbonQualification.getQualificationUserName());
        List<Object> params = qualificationInputBO.toArgs();

        // 查询用户是否已经存在了资质
        CarbonQualification qualification = carbonQualificationMapper.selectCarbonQualificationByName(carbonQualification.getQualificationName());
        if (Objects.isNull(qualification)){
            // 调用合约业务
            TransactionResponse transactionResponse = rawContractLoaderFactory.GetTransactionResponse(carbonEnterprise.getPriavateKey(), "uploadQualification", params);
            if (transactionResponse.getReceiptMessages().equals("Success")) {
                int resultStatus = Objects.requireNonNull(JSON.parseArray(transactionResponse.getValues())).getIntValue(0);
                if (resultStatus == 200){
                    JSONArray resultObject = Objects.requireNonNull(JSON.parseArray(transactionResponse.getValues())).getJSONArray(2);
                    carbonQualification.setQualificationAddress(carbonQualification.getQualificationAddress());
                    carbonQualification.setQualificationId(resultObject.getIntValue(0));
                    carbonQualification.setQualificationEmissionLimit(resultObject.getBigInteger(9));
                    carbonQualification.setQualificationUploadTime(BlockTimestampUtil.convert(resultObject.getLongValue(6)));
                    carbonQualification.setQualificationVerifiedRegulator(resultObject.getString(8).equals(emptyAddress) ? "暂未认证" : carbonQualification.getQualificationContent());
                    return carbonQualificationMapper.insertCarbonQualification(carbonQualification);
                }
            }
            return 0;
        }
        return 0;
    }

    /**
     * 修改企业资质信息
     *
     * @param carbonQualification 企业资质信息
     * @return 结果
     */
    @Override
    public int updateCarbonQualification(CarbonQualification carbonQualification)
    {
        return carbonQualificationMapper.updateCarbonQualification(carbonQualification);
    }

    /**
     * 批量删除企业资质信息
     *
     * @param qualificationIds 需要删除的企业资质信息主键
     * @return 结果
     */
    @Override
    public int deleteCarbonQualificationByQualificationIds(Long[] qualificationIds)
    {
        return carbonQualificationMapper.deleteCarbonQualificationByQualificationIds(qualificationIds);
    }

    /**
     * 删除企业资质信息信息
     *
     * @param qualificationId 企业资质信息主键
     * @return 结果
     */
    @Override
    public int deleteCarbonQualificationByQualificationId(Long qualificationId)
    {
        return carbonQualificationMapper.deleteCarbonQualificationByQualificationId(qualificationId);
    }

    @Override
    @Transactional
    public AjaxResult verifyQualificationByRegulator(CarbonQualification carbonQualification) throws Exception {
        // 查看当前的资质是否存在
        Integer qualificationId = carbonQualification.getQualificationId();
        String enterpriseName = carbonQualification.getQualificationName();
        // 分别查询关联的企业和资质信息
        CarbonQualification qualification = this.selectCarbonQualificationByQualificationId(Long.valueOf(qualificationId));
        CarbonEnterprise carbonEnterprise = enterpriseService.selectByEnterpriseName(enterpriseName);

        if (!Objects.isNull(qualification) || !Objects.isNull(carbonEnterprise)){
            int extractededVerify = extractedVerify(carbonEnterprise,carbonQualification);
            if (extractededVerify > 0 ){
                return AjaxResult.success("审核成功",null);
            }
        }
        return AjaxResult.error("企业或资质不存在",null);
    }

    @Override
    public AjaxResult uploadQualification(MultipartFile file) {
        AjaxResult ajax = AjaxResult.success();
        String imgUrl = tcosUtil.uploadFile(file);
        if (StringUtils.isEmpty(imgUrl)) return AjaxResult.error("上传失败");
        ajax.put("imgUrl",imgUrl);
        return ajax;
    }

    @Override
    public List<QualificationVo> selectQualificationIsVerifyOfWeeek() {
        return carbonQualificationMapper.selectQualificationByVerifyListOfWeek();
    }

    /**
     * 监管机构确认企业资质上链
     *
     * @param carbonQualification 企业的资质信息
     * @return 返回结果
     */
    private int extractedVerify(CarbonEnterprise carbonEnterprise,CarbonQualification carbonQualification) {
        // 监管机构确认上链
        CarbonAssetServiceVerifyQualificationInputBO qualificationInputBO = new CarbonAssetServiceVerifyQualificationInputBO();
        qualificationInputBO.set_enterpriseAddress(carbonEnterprise.getEnterpriseAddress());
        qualificationInputBO.set_regulatorAddress(carbonQualification.getQualificationVerifiedRegulator());
        qualificationInputBO.set_isApprove(carbonQualification.getIsApprove());
        // 获取用户的地址信息
        UserKey userKey = rawContractLoaderFactory
                .GetRegulatorPrivateKey(carbonQualification.getQualificationVerifiedRegulator());
        try {
            // 发送带签名的交易
            TransactionResponse transactionResponse = rawContractLoaderFactory
                    .GetTransactionResponse(userKey.getPrivateKey(), "verifyQualification", qualificationInputBO.toArgs());
            // 进行链上数据的同步
            if (transactionResponse.getReceiptMessages().equals("Success")){
                JSONArray result = JSON.parseArray(transactionResponse.getValues()).getJSONArray(1);
                carbonQualification.setQualificationAuditTime(BlockTimestampUtil.convert(result.getLongValue(7)));
                carbonQualification.setQualificationVerifiedRegulator(result.getString(8));
                carbonQualification.setQualificationEmissionLimit(result.getBigInteger(9));

                // 异步执行两个数据库更新的任务
                this.auditQualificationOver(carbonQualification);
                this.updateEnterpriseVerified(carbonEnterprise);
                return 1;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Async("taskExecutor")
    public void updateEnterpriseVerified(CarbonEnterprise carbonEnterprise){
        carbonEnterprise.setEnterpriseVerified(1);
        enterpriseMapper.updateCarbonEnterprise(carbonEnterprise);
    }

    @Async("taskExecutor")
    public void auditQualificationOver(CarbonQualification carbonQualification){
        this.updateCarbonQualification(carbonQualification);
    }

}

