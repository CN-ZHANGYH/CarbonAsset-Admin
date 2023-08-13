package com.ruoyi.web.controller.carbon.front;

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


    /**
     * 查询所有的资源分类数据占比
     * @return
     */
    @GetMapping("/rtList")
    public AjaxResult getResourceTypeCount(){
        return resourceTypeService.selectResourceTypeByCount();
    }


    /**
     * 查询碳足迹的数据
     */

    @GetMapping("/footprint")
    public AjaxResult getAllList(){
        return emissionResourceService.selectEmissionAndTxAndApplyAndQuaList();
    }


    @GetMapping("/newTxList")
    public AjaxResult getNewTxList(){
        return transactionService.selectTransactionNewTxList();
    }

    @GetMapping("/ownerTxAndSeList")
    public AjaxResult getEnterpriseTxAndSellerList(@RequestParam("enterprise") String enterprise){
        return transactionService.selectTransactionTxAndSellerList(enterprise);
    }
}
