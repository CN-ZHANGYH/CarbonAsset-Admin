package com.ruoyi.web.controller.carbon.front;

import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.souvenir.domain.vo.CreditVo;
import com.ruoyi.souvenir.service.card.ICarbonCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("积分接口")
@RestController
@RequestMapping("/credit")
public class IsCreditController {

    @Autowired
    private ICarbonCardService carbonCardService;

    @Autowired
    private ICarbonEnterpriseService  carbonEnterpriseService;

    @Log(title = "积分兑换纪念卡",businessType = BusinessType.OTHER)
    @PostMapping("/exchange")
    public AjaxResult creditExchange(@RequestBody CreditVo creditVo){
        return carbonCardService.creditedExchange(creditVo);
    }


    @ApiOperation("根据积分进行排行")
    @GetMapping("/rankingByCredit")
    public AjaxResult getRankingByCredit(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize){
        return carbonEnterpriseService.selectRankingByCredit(page,pageSize);
    }

    @ApiOperation("查询企业当前的排名")
    @GetMapping("/getERanking")
    public AjaxResult getEnterpriseRanking(@RequestParam("enterprise") String enterprise){
        return carbonEnterpriseService.selectEnterpriseRanking(enterprise);
    }


}
