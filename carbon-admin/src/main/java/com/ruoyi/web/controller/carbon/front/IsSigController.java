package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.carbon.service.user.SignInService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("用户签到管理")
@RestController
public class IsSigController extends BaseController {


    @Autowired
    private SignInService signInService;

    @PostMapping("/addSign")
    public AjaxResult sginIn(@RequestParam("address") String address) throws Exception {
        return signInService.userSignInToCredit(address);
    }
}
