package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.carbon.domain.carbon.CarbonEmissionResource;
import com.ruoyi.carbon.domain.carbon.CarbonEnterpriseAsset;
import com.ruoyi.carbon.domain.carbon.CarbonTransaction;
import com.ruoyi.carbon.service.enterprise.EnterpriseQueryService;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseAssetService;
import com.ruoyi.carbon.service.resources.ICarbonEmissionResourceService;
import com.ruoyi.carbon.service.transaction.ICarbonTransactionService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oshi.driver.unix.aix.Ls;

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

    @Autowired
    private ICarbonEmissionResourceService emissionResourceService;

    @Autowired
    private ICarbonTransactionService transactionService;


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
            return IsNullTableInfoMsg("当前没有交易记录");
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
            return IsNullTableInfoMsg("当前没有排放记录");
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
            return IsNullTableInfoMsg("当前没有出售记录");
        }
        return getDataTable(enterpriseAssets);
    }


    @ApiOperation("根据企业账户地址查询最新的出售记录")
    @PostMapping("/enterpriseNewSellHistory")
    public AjaxResult queryEnterpriseNewSellerAssetLimitFive(@RequestParam("address") String address){
        return enterpriseAssetService.queryEnterpriseNewSellerAssetLimitFive(address);
    }


    @ApiOperation("查询企业所有未审批的碳排放申请")
    @GetMapping("/enterpriseIsNotApplyEmission")
    public TableDataInfo getEnterpriseIsNotApplyEmission(@RequestParam("enterprise") String enterprise){
        startPage();
        List<CarbonEmissionResource> carbonEmissionResources = emissionResourceService.selectEnterpriseIsNotApplyEmissionResource(enterprise);
        if (Objects.isNull(carbonEmissionResources))
        {
            return IsNullTableInfoMsg("当前还没有申请");
        }
        return getDataTable(carbonEmissionResources);
    }


    @ApiOperation("查询企业所有审批的碳排放申请")
    @GetMapping("/enterpriseIsApplyEmission")
    public TableDataInfo getEnterpriseIsApplyEmission(@RequestParam("enterprise") String enterprise){
        startPage();
        List<CarbonEmissionResource> carbonEmissionResources = emissionResourceService.selectEnterpriseIsApplyEmissioResource(enterprise);
        if (Objects.isNull(carbonEmissionResources))
        {
            return IsNullTableInfoMsg("当前还没有申请");
        }
        return getDataTable(carbonEmissionResources);
    }


    @ApiOperation("根据排放方式查询企业排放记录")
    @PostMapping("/searchResEmiRecord")
    public TableDataInfo searchEnterpriseResourceEmissionRecord(@RequestParam("enterprise") String enterprise,
                                                                @RequestParam("method") String method) {
        startPage();
        List<CarbonEmissionResource> carbonEmissionResources = emissionResourceService.searchEnterpriseResourceEmissionRecord(enterprise,method);
        if (Objects.isNull(carbonEmissionResources))
        {
            return IsNullTableInfoMsg("没有该记录");
        }
        return getDataTable(carbonEmissionResources);

    }



    @ApiOperation("根据出售数量查询企业的所有出售记录")
    @PostMapping("/searchSellRecord")
    public TableDataInfo searchEnterpriseSellerRecord(@RequestParam("enterprise") String enterprise,
                                                      @RequestParam("quality") Integer quality){
        startPage();
        List<CarbonEnterpriseAsset> enterpriseAssets = enterpriseAssetService.searchEnterpriseSellerRecord(enterprise,quality);
        if (Objects.isNull(enterpriseAssets))
        {
            return IsNullTableInfoMsg("没有该记录");
        }
        return getDataTable(enterpriseAssets);
    }



    @ApiOperation("根据交易数量查询企业所有的交易记录")
    @PostMapping("/searchTxRecord")
    public TableDataInfo searchEnterpriseTxRecord(@RequestParam("buyerId") Long buyerId,
                                                  @RequestParam("quality") Long quality){
        startPage();
        List<CarbonTransaction> transactions = transactionService.searchEnterpriseTxRecord(buyerId,quality);
        if (Objects.isNull(transactions))
        {
            return IsNullTableInfoMsg("没有该记录");
        }
        return getDataTable(transactions);
    }



    private static TableDataInfo IsNullTableInfoMsg(String msg) {
        TableDataInfo nullDataInfo = new TableDataInfo();
        nullDataInfo.setTotal(0);
        nullDataInfo.setCode(200);
        nullDataInfo.setMsg(msg);
        return nullDataInfo;
    }
}
