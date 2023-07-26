package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.domain.vo.BuyVo;
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

}