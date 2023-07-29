package com.ruoyi.web.controller.carbon.front;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.souvenir.domain.vo.CreditVo;
import com.ruoyi.souvenir.service.card.ICarbonCardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("积分接口")
@RestController
@RequestMapping("/credit")
public class IsCreditController {

    @Autowired
    private ICarbonCardService carbonCardService;

    @Log(title = "积分兑换纪念卡",businessType = BusinessType.OTHER)
    @PostMapping("/exchange")
    public AjaxResult creditExchange(@RequestBody CreditVo creditVo){
        return carbonCardService.creditedExchange(creditVo);
    }

}
