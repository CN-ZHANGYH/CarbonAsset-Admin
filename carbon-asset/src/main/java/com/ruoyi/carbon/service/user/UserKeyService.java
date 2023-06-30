package com.ruoyi.carbon.service.user;

import com.ruoyi.carbon.domain.user.UserKey;

/**
 * @author zyh
 * @date 2023/7/8 19:49
 * @desc IntelliJ IDEA
 */
public interface UserKeyService {

    public UserKey newUserKeyAndAddress();

    public UserKey selectPrivateKeyByRegulatorAddress(String address);

    public UserKey selectPrivateKeyByAddress(String address);
}
