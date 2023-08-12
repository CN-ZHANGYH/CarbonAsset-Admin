package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.carbon.service.user.SignInService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("用户签到管理")
@RestController
public class IsSigController extends BaseController {


    @Autowired
    private SignInService signInService;

    /**
     * 用户签到
     * @param enterprise 用户的企业名称
     * @return 返回结果
     * @throws Exception
     */
    @ApiOperation("用户签到")
    @PostMapping("/addSign")
    public AjaxResult sgnIn(@RequestParam("enterprise") String enterprise) throws Exception {
        return signInService.userSignInToCredit(enterprise);
    }


    /**
     * 统计用户签到的情况
     * @param enterprise 用户的企业名称
     * @return
     */
    @ApiOperation("查询用户的连续签到次数和总次数")
    @PostMapping("/signCount")
    public AjaxResult signCount(@RequestParam("enterprise") String enterprise){
        return signInService.signCountByAddress(enterprise);
    }




}
