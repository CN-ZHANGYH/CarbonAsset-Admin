package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.souvenir.service.card.ICarbonCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
