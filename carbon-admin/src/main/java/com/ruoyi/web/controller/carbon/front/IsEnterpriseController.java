package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.domain.vo.BuyVo;
import com.ruoyi.carbon.domain.vo.EnterpriseVo;
import com.ruoyi.carbon.domain.vo.ForgetPassVo;
import com.ruoyi.carbon.domain.vo.SellVo;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;


/**
 * 企业资产管理
 */
@Api("企业资产管理")
@RestController
@RequestMapping("/enterprise")
public class IsEnterpriseController extends BaseController {


    @Autowired
    private ICarbonEnterpriseService enterpriseService;

    @ApiOperation("企业出售资产")
    @PostMapping("/sell")
    @Log(title = "企业出售资产",businessType = BusinessType.INSERT)
    public AjaxResult sellByEnterpriseAsset(@RequestBody SellVo sellVo) throws Exception {
        return enterpriseService.sellEnterpriseAsset(sellVo);
    }

    @ApiOperation("企业购买资产")
    @PostMapping("/buy")
    @Log(title = "企业购买资产",businessType = BusinessType.INSERT)
    public AjaxResult buyEnterpriseAsset(@RequestBody BuyVo buyVo) {
        return enterpriseService.buyEnterpriseAsset(buyVo);
    }

    @ApiOperation("企业更新余额")
    @PutMapping("/updateBalance")
    @Log(title = "企业更新余额",businessType = BusinessType.UPDATE)
    public AjaxResult updateBalance(@RequestBody CarbonEnterprise carbonEnterprise){
        int code = enterpriseService.updateCarbonEnterpriseBalance(carbonEnterprise);
        if (code > 0){
            return AjaxResult.success("充值成功");
        }
        return AjaxResult.error("充值失败");
    }

    @ApiOperation("查看企业的信息")
    @PostMapping("/info")
    @Log(title = "企业更新余额",businessType = BusinessType.UPDATE)
    public AjaxResult getEnterpriseInfo(@RequestParam("address") String address){
        return enterpriseService.selectEnterpriseInfoByBlockChain(address);
    }

    @ApiOperation("更新总的排放量")
    @PostMapping("/updateTotalEmission")
    @Log(title = "企业更新总排放量",businessType = BusinessType.UPDATE)
    public AjaxResult updateTotalEmission(@RequestParam("address") String address,
                                          @RequestParam("count")BigInteger count
    ) throws Exception {
        return enterpriseService.updateTotalEmission(address,count);
    }

    @ApiOperation("企业更新个人信息")
    @PostMapping("/updateInfo")
    @Log(title = "企业更新个人信息",businessType = BusinessType.UPDATE)
    public AjaxResult updateEnterpriseInfo(@RequestBody EnterpriseVo enterpriseVo){
        return enterpriseService.updateEnterpriseInfo(enterpriseVo);
    }


    @ApiOperation("上传头像")
    @PostMapping("/uploadAvatar")
    @Log(title = "上传头像",businessType = BusinessType.UPDATE)
    public AjaxResult updateAvatar(@RequestParam("file") MultipartFile file){
        return enterpriseService.updateAvatar(file);
    }

    @ApiOperation("更新企业账户密码")
    @PostMapping("/updatePass")
    @Log(title = "更新企业账户密码",businessType = BusinessType.UPDATE)
    public AjaxResult updatePassword(@RequestBody ForgetPassVo forgetPassVo){
        return enterpriseService.forgetUserPassword(forgetPassVo);
    }

    @ApiOperation("更新用户的头像")
    @PostMapping("/updateAvatar")
    @Log(title = "更新企业的的头像",businessType = BusinessType.UPDATE)
    public AjaxResult updateAvatarByName(@RequestParam("enterprise") String enterprise,@RequestParam("avatar") String avatar){
        return enterpriseService.updateAvatarByName(enterprise,avatar);
    }


    @ApiOperation("上传商品的图片")
    @PostMapping("/uploadProductImage")
    @Log(title = "上传商品的图片",businessType = BusinessType.UPDATE)
    public AjaxResult uploadProductImage(@RequestParam("file") MultipartFile file){
        return enterpriseService.uploadProductImage(file);
    }


}
