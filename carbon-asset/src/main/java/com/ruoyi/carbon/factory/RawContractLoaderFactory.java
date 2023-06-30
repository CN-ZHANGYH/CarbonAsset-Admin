package com.ruoyi.carbon.factory;

import com.ruoyi.carbon.domain.user.UserKey;
import com.ruoyi.carbon.service.user.UserKeyService;
import com.ruoyi.carbon.utils.IOUtil;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 张宇豪
 * @date 2023/7/8 22:03
 * @desc 合约调用构造交易函数工厂模式
 */

@Component
public class RawContractLoaderFactory {

    @Value("${system.contract.carbonAssetServiceAddress}")
    private String address;

    @Autowired
    private Client client;

    @Autowired
    private UserKeyService userKeyService;

    public static final String ABI = IOUtil.readResourceAsString("abi/CarbonAssetService.abi");

    /**
     * 获取用户的私钥
     * @param address 用户的地址
     * @return 返回用户的私钥
     */
    public UserKey GetUserPrivateKey(String address) {
        return userKeyService.selectPrivateKeyByAddress(address);
    }

    /**
     * 获取用户的私钥
     * @param address 监管机构的地址
     * @return 结果
     */
    public UserKey GetRegulatorPrivateKey(String address){return userKeyService.selectPrivateKeyByRegulatorAddress(address);}

    /**
     * 使用切换账户调用合约
     * @param cryptoKeyPair 密钥
     * @param funcName 函数名称
     * @param params 参数
     * @return TransactionResponse 交易响应
     * @throws Exception 异常类
     */
    public TransactionResponse GetTransactionResponse(String cryptoKeyPair, String funcName, List<Object> params) throws Exception {

        // 直接根据地址切换账户
        CryptoKeyPair keyPair = this.client.getCryptoSuite().getKeyPairFactory().createKeyPair(cryptoKeyPair);

        // 使用ByContractLoader调用合约方法
        AssembleTransactionProcessor transactionProcessor = TransactionProcessorFactory
                .createAssembleTransactionProcessor(this.client, keyPair, "CarbonAssetService",ABI, "");

        return transactionProcessor
                .sendTransactionAndGetResponseByContractLoader("CarbonAssetService",this.address,funcName,params);
    }

}
