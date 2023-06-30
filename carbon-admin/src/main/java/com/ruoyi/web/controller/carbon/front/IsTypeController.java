package com.ruoyi.web.controller.carbon.front;

import com.ruoyi.carbon.domain.carbon.CarbonResourceType;
import com.ruoyi.carbon.service.type.ICarbonResourceTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/types")
public class IsTypeController extends BaseController {


    @Autowired
    private ICarbonResourceTypeService resourceTypeService;

    @GetMapping
    public AjaxResult getResourceTypeNameList(CarbonResourceType resourceType){
        return  resourceTypeService.selectResourceTypeNameList(resourceType);
    }
}
