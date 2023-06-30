package com.ruoyi.carbon.service.user;

import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * @author zyh
 * @date 2023/7/8 20:11
 * @desc IntelliJ IDEA
 */
public interface UserRegisterService {

    public CarbonEnterprise registerUser(SysUser sysUser) throws Exception;

}
