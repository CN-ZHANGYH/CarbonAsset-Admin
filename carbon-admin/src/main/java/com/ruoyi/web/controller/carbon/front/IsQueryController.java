package com.ruoyi.web.controller.carbon.front;


import com.github.pagehelper.page.PageParams;
import com.ruoyi.carbon.domain.vo.QueryErPage;
import com.ruoyi.carbon.service.enterprise.EnterpriseQueryService;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseAssetService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 查询接口
 */
@Api("查询接口")
@RestController
@RequestMapping("/query")
public class IsQueryController {


    @Autowired
    private EnterpriseQueryService enterpriseQueryService;

    @Autowired
    private ICarbonEnterpriseAssetService enterpriseAssetService;


    @ApiOperation("查询企业的详细信息")
    @PostMapping("/enterpriseInfo")
    @Log(title = "查询企业的详细信息",businessType = BusinessType.OTHER)
    public AjaxResult queryEnterpriseInfo(@RequestParam("enterprise") String enterprise){
        return enterpriseQueryService.queryEnterpriseInfo(enterprise);
    }


    @ApiOperation("查询企业的资质信息")
    @PostMapping("/qualificationInfo")
    @Log(title = "查询企业的资质信息",businessType = BusinessType.OTHER)
    public AjaxResult queryQualificationInfo(@RequestParam("enterprise") String enterprise){
        return enterpriseQueryService.queryQualificationInfo(enterprise);
    }


    @ApiOperation("查询企业的所有交易")
    @GetMapping("/enterpriseTxList")
    @Log(title = "查询企业的所有交易",businessType = BusinessType.OTHER)
    public AjaxResult queryEnterpriseTxList(@RequestParam("enterprise") String enterprise){
        return enterpriseQueryService.queryEnterpriseTxList(enterprise);
    }

    @ApiOperation("查询企业的所有排放记录")
    @GetMapping("/enterpriseErList")
    @Log(title = "查询企业的所有排放记录",businessType = BusinessType.OTHER)
    public AjaxResult queryEnterpriseErList(@RequestParam("enterprise") String enterprise){
        return enterpriseQueryService.queryEnterpriseErList(enterprise);
    }


    @ApiOperation("查询企业的所有出售记录")
    @GetMapping("/enterpriseAList")
    @Log(title = "查询企业的所有出售记录",businessType = BusinessType.OTHER)
    public AjaxResult queryEnterpriseAssetList(@RequestParam("enterprise") String enterprise){
        return enterpriseQueryService.queryEnterpriseAList(enterprise);
    }


    @ApiOperation("根据企业账户地址查询最新的出售记录")
    @PostMapping("/enterpriseNewSellHistory")
    public AjaxResult queryEnterpriseNewSellerAssetLimitFive(@RequestParam("address") String address){
        return enterpriseAssetService.queryEnterpriseNewSellerAssetLimitFive(address);
    }


    @ApiOperation("分页查询企业排放记录")
    @PostMapping("/enterpriseErListByPage")
    public AjaxResult queryEnterpriseErListByPage(@RequestBody QueryErPage queryErPage){
        return enterpriseQueryService.queryEnterpriseErListByPage(queryErPage);
    }


}
