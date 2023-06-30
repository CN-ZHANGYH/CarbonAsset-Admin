package com.ruoyi.carbon.service.user.impl;


import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.domain.carbon.CarbonRegulator;
import com.ruoyi.carbon.domain.user.UserKey;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseService;
import com.ruoyi.carbon.service.regulator.ICarbonRegulatorService;
import com.ruoyi.carbon.service.user.UserKeyService;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 张宇豪
 * @date 2023/7/8 19:49
 * @desc 生成用户的私钥以及地址信息
 */

@Service
public class UserKeyServiceImpl implements UserKeyService {


    @Autowired
    private ICarbonEnterpriseService enterpriseService;

    @Autowired
    private ICarbonRegulatorService regulatorService;


    /**
     * 生成新的用户地址以及私钥信息
     * @return UserKey 用户信息实体类
     */
    @Override
    public UserKey newUserKeyAndAddress() {
        CryptoSuite cryptoSuite = new CryptoSuite(CryptoType.ECDSA_TYPE);
        CryptoKeyPair keyPair = cryptoSuite.createKeyPair();
        UserKey userKey = new UserKey();
        userKey.setAddress(keyPair.getAddress());
        userKey.setPrivateKey(keyPair.getHexPrivateKey());
        userKey.setPublicKey(keyPair.getHexPublicKey());
        return userKey;
    }

    @Override
    public UserKey selectPrivateKeyByRegulatorAddress(String address) {
        UserKey userKey = new UserKey();
        CarbonRegulator carbonRegulator = regulatorService.selectRegulatorByAddress(address);
        if (!Objects.isNull(carbonRegulator)){
            userKey.setAddress(carbonRegulator.getRegulatorAddress());
            userKey.setPrivateKey(carbonRegulator.getPrivateKey());
            return userKey;
        }
        return null;

    }

    /**
     * 获取企业的私钥
     * @param address 用户的地址
     * @return 结果
     */
    @Override
    public UserKey selectPrivateKeyByAddress(String address) {
        UserKey userKey = new UserKey();
        CarbonEnterprise carbonEnterprise = enterpriseService.selectByAddress(address);
        if (!Objects.isNull(carbonEnterprise)) {
            userKey.setAddress(carbonEnterprise.getEnterpriseAddress());
            userKey.setPrivateKey(carbonEnterprise.getPriavateKey());
            return userKey;
        }
        return null;
    }
}
