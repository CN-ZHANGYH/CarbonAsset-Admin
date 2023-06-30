package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.carbon.domain.carbon.CarbonIndustry;
import com.ruoyi.carbon.service.industry.ICarbonIndustryService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/industrys")
public class IsIndustryController {

    @Autowired
    private ICarbonIndustryService industryService;


    @GetMapping()
    public AjaxResult getIndustryNameList(CarbonIndustry carbonIndustry){
        return industryService.selectIndustryNameList(carbonIndustry);
    }
}
