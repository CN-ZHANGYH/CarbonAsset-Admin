package com.ruoyi.carbon.service.system;

import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.BlockNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zyh
 * @date 2023/7/8 18:31
 * @desc IntelliJ IDEA
 */

@Service
public class DataViewService {

    @Autowired
    private static Client client;

    public void GetBlockNumber() {
        BlockNumber blockNumber = client.getBlockNumber();
        System.out.println(blockNumber);
    }
}
