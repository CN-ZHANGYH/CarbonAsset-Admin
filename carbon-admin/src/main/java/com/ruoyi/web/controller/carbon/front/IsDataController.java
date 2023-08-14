package com.ruoyi.web.controller.carbon.front;

import com.ruoyi.carbon.service.enterprise.EnterpriseQueryService;
import com.ruoyi.carbon.service.resources.ICarbonEmissionResourceService;
import com.ruoyi.carbon.service.transaction.ICarbonTransactionService;
import com.ruoyi.carbon.service.type.ICarbonResourceTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/carbon/data")
public class IsDataController extends BaseController {

    @Autowired
    private ICarbonResourceTypeService resourceTypeService;

    @Autowired
    private ICarbonTransactionService transactionService;


    @Autowired
    private ICarbonEmissionResourceService emissionResourceService;

    @Autowired
    private EnterpriseQueryService enterpriseQueryService;


    /**
     * 查询所有的资源分类数据占比
     * @return 返回结果
     */
    @GetMapping("/rtList")
    public AjaxResult getResourceTypeCount(){
        return resourceTypeService.selectResourceTypeByCount();
    }


    /**
     * 查询碳足迹的数据
     * @return 返回结果
     */
    @GetMapping("/footprint")
    public AjaxResult getAllList(){
        return emissionResourceService.selectEmissionAndTxAndApplyAndQuaList();
    }


    /**
     * 查询最新的交易
     * @return 返回结果
     */
    @GetMapping("/newTxList")
    public AjaxResult getNewTxList(){
        return transactionService.selectTransactionNewTxList();
    }

    @GetMapping("/newTxListLimitFive")
    public AjaxResult getNewTxListLimitFive(){
        return transactionService.selectTransactionNewTxListLimitFive();
    }

    /**
     * 查询当前企业的交易和出售的统计
     * @param enterprise 企业名称
     * @return 返回结果
     */
    @GetMapping("/ownerTxAndSeList")
    public AjaxResult getEnterpriseTxAndSellerList(@RequestParam("enterprise") String enterprise){
        return transactionService.selectTransactionTxAndSellerList(enterprise);
    }


    @GetMapping("/ownerWorkProgress")
    public AjaxResult getEnterpriseWorkProgress(@RequestParam("enterprise") String enterprise){
        return enterpriseQueryService.selectEnterpriseWorkProgress(enterprise);
    }


    @GetMapping("/getTotalTxAndEmission")
    public AjaxResult selectTotalTxAndEmission(){
        return enterpriseQueryService.selectTotalTxAndEmission();
    }
}
