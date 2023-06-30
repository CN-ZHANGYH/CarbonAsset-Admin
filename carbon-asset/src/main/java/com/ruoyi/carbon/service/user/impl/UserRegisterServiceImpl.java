package com.ruoyi.carbon.service.user.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.domain.user.UserKey;
import com.ruoyi.carbon.mapper.CarbonEnterpriseMapper;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceRegisterEnterpriseInputBO;
import com.ruoyi.carbon.service.carbon.CarbonAssetServiceService;
import com.ruoyi.carbon.service.user.UserKeyService;
import com.ruoyi.carbon.service.user.UserRegisterService;
import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张宇豪
 * @date 2023/7/8 20:11
 * @desc 用户注册接口实现类
 */
@Service
@Slf4j
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    private UserKeyService userKeyService;

    @Autowired
    private CarbonAssetServiceService carbonAssetServiceService;

    @Autowired
    private CarbonEnterpriseMapper carbonEnterpriseMapper;

    @Override
    public CarbonEnterprise registerUser(SysUser sysUser) throws Exception {
        // 用户注册上链
        UserKey userKey = userKeyService.newUserKeyAndAddress();
        CarbonAssetServiceRegisterEnterpriseInputBO enterpriseInputBO = new CarbonAssetServiceRegisterEnterpriseInputBO();
        enterpriseInputBO.set_enterpriseAddress(userKey.getAddress());
        enterpriseInputBO.set_enterpriseName(sysUser.getNickName());
        TransactionResponse transactionResponse = carbonAssetServiceService.registerEnterprise(enterpriseInputBO);
        if (transactionResponse.getReturnMessage().equals("Success")){
            // 用户注册更新数据库信息
            CarbonEnterprise carbonEnterprise = new CarbonEnterprise();
            JSONArray jsonArray = JSON.parseArray(transactionResponse.getValues()).getJSONArray(1);
            carbonEnterprise.setEnterpriseId(jsonArray.getIntValue(0));
            carbonEnterprise.setEnterpriseAddress(jsonArray.getString(1));
            carbonEnterprise.setEnterpriseName(jsonArray.getString(2));
            carbonEnterprise.setEnterpriseBalance(jsonArray.getBigInteger(3));
            carbonEnterprise.setEnterpriseTotalEmission(jsonArray.getBigInteger(4));
            carbonEnterprise.setEnterpriseOverEmission(jsonArray.getBigInteger(5));
            carbonEnterprise.setEnterpriseCarbonCredits(jsonArray.getBigInteger(6));
            carbonEnterprise.setEnterpriseVerified(jsonArray.getBooleanValue(7) ? 1 : 0);
            carbonEnterprise.setUserType(jsonArray.getIntValue(8));
            carbonEnterprise.setQualificationId(jsonArray.getIntValue(9));
            carbonEnterprise.setPriavateKey(userKey.getPrivateKey());
            carbonEnterpriseMapper.insertCarbonEnterprise(carbonEnterprise);
            return carbonEnterprise;
        }
        return null;
    }
}
