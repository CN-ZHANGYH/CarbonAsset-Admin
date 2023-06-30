package com.ruoyi.carbon.service.user.impl;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.factory.RawContractLoaderFactory;
import com.ruoyi.carbon.mapper.CarbonEnterpriseMapper;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceSignInInputBO;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseService;
import com.ruoyi.carbon.service.user.SignInService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Objects;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private ICarbonEnterpriseService enterpriseService;

    @Autowired
    private CarbonEnterpriseMapper enterpriseMapper;

    @Autowired
    private RawContractLoaderFactory rawContractLoaderFactory;


    @Override
    public AjaxResult userSignInToCredit(String address) throws Exception {
        CarbonEnterprise enterprise = enterpriseService.selectByAddress(address);
        if (Objects.isNull(enterprise)) return AjaxResult.error("当前用户不存在");
        CarbonAssetServiceSignInInputBO signAddress = new CarbonAssetServiceSignInInputBO();
        signAddress.set_enterpriseAddress(address);

        TransactionResponse transactionResponse = rawContractLoaderFactory
                .GetTransactionResponse(enterprise.getPriavateKey(), "signIn", signAddress.toArgs());
        if (transactionResponse.getReturnMessage().equals("Success")){
            int credit = JSON.parseArray(transactionResponse.getValues()).getIntValue(1);

            // 假装计算
            BigInteger oldCredit = enterprise.getEnterpriseCarbonCredits();
            enterprise.setEnterpriseCarbonCredits(oldCredit.add(new BigInteger(String.valueOf(50))));
            int status = enterpriseMapper.updateCarbonEnterprise(enterprise);
            if (status > 0)
            {
                AjaxResult ajax = AjaxResult.success("签到成功");
                ajax.put("credit_number",credit);
                return ajax;
            }
            return AjaxResult.error("签到积分获取异常");
        }
        return AjaxResult.error("签到失败");
    }
}
