package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.carbon.domain.carbon.CarbonEmissionResource;
import com.ruoyi.carbon.domain.carbon.CarbonEnterpriseAsset;
import com.ruoyi.carbon.domain.carbon.CarbonTransaction;
import com.ruoyi.carbon.service.enterprise.EnterpriseQueryService;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseAssetService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 查询接口
 */
@Api("查询接口")
@RestController
@RequestMapping("/query")
public class IsQueryController extends BaseController {


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
    public TableDataInfo queryEnterpriseTxList(@RequestParam("enterprise") String enterprise){
        startPage();
        List<CarbonTransaction> txList = (List<CarbonTransaction>) enterpriseQueryService.queryEnterpriseTxList(enterprise).get("data");
        if (Objects.isNull(txList))
        {
            TableDataInfo nullDataInfo = new TableDataInfo();
            nullDataInfo.setMsg("当前没有交易记录");
            nullDataInfo.setCode(200);
            nullDataInfo.setTotal(0);
            return nullDataInfo;
        }
        return getDataTable(txList);
    }

    @ApiOperation("查询企业的所有排放记录")
    @GetMapping("/enterpriseErList")
    @Log(title = "查询企业的所有排放记录",businessType = BusinessType.OTHER)
    public TableDataInfo queryEnterpriseErList(@RequestParam("enterprise") String enterprise){
        startPage();
        List<CarbonEmissionResource> emissionResources = (List<CarbonEmissionResource>) enterpriseQueryService.queryEnterpriseErList(enterprise).get("data");
        if (Objects.isNull(emissionResources))
        {
            TableDataInfo nullDataInfo = new TableDataInfo();
            nullDataInfo.setMsg("当前没有排放记录");
            nullDataInfo.setCode(200);
            nullDataInfo.setTotal(0);
            return nullDataInfo;
        }

        return getDataTable(emissionResources);
    }


    @ApiOperation("查询企业的所有出售记录")
    @GetMapping("/enterpriseAList")
    @Log(title = "查询企业的所有出售记录",businessType = BusinessType.OTHER)
    public TableDataInfo queryEnterpriseAssetList(@RequestParam("enterprise") String enterprise){
        startPage();
        List<CarbonEnterpriseAsset> enterpriseAssets = (List<CarbonEnterpriseAsset>) enterpriseQueryService.queryEnterpriseAList(enterprise).get("data");
        if (Objects.isNull(enterpriseAssets))
        {
            TableDataInfo nullDataInfo = new TableDataInfo();
            nullDataInfo.setMsg("当前没有出售记录");
            nullDataInfo.setCode(200);
            nullDataInfo.setTotal(0);
            return nullDataInfo;
        }
        return getDataTable(enterpriseAssets);
    }


    @ApiOperation("根据企业账户地址查询最新的出售记录")
    @PostMapping("/enterpriseNewSellHistory")
    public AjaxResult queryEnterpriseNewSellerAssetLimitFive(@RequestParam("address") String address){
        return enterpriseAssetService.queryEnterpriseNewSellerAssetLimitFive(address);
    }

}
