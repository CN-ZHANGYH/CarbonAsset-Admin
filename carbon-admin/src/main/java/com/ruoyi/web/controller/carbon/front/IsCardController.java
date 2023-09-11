package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.souvenir.service.card.ICarbonCardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class IsCardController {

    @Autowired
    private ICarbonCardService carbonCardService;


    @Log(title = "查询用户所有纪念卡",businessType = BusinessType.OTHER)
    @GetMapping("/enterprise")
    public AjaxResult getCardListByEnterpriseName(@RequestParam("name") String name){
        return carbonCardService.selectCarbonCardListByEnterprise(name);
    }


    @ApiOperation("收藏纪念卡")
    @GetMapping("/collect")
    public AjaxResult enterpriseCollectCard(@RequestParam("enterprise_id") Integer enterprise_id,
                                            @RequestParam("card_id") Integer card_id){
        return carbonCardService.enterpriseCollectCard(enterprise_id,card_id);
    }

    @ApiOperation("查看已收藏的纪念卡")
    @PostMapping("/hasCardList")
    public AjaxResult enterpriseHasCardList(@RequestParam("enterprise") String enterprise){
        return carbonCardService.selectEnterpriseHasCardList(enterprise);
    }


    @ApiOperation("查询个人的商店信息")
    @PostMapping("/info")
    public AjaxResult getEnterpriseShopInfo(@RequestParam("enterprise") String enterprise)
    {
        return carbonCardService.getEnterpriseShopInfo(enterprise);
    }
}
