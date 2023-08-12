package com.ruoyi.carbon.service.user;

import com.ruoyi.common.core.domain.AjaxResult;

public interface SignInService {

    AjaxResult userSignInToCredit(String address) throws Exception;

    AjaxResult signCountByAddress(String address);
}
