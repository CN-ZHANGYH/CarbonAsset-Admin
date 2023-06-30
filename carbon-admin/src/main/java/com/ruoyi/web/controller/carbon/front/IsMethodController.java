package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.carbon.domain.carbon.EmissionMethod;
import com.ruoyi.carbon.service.method.IEmissionMethodService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emissionMethods")
public class IsMethodController extends BaseController {

    @Autowired
    private IEmissionMethodService emissionMethodService;


    @GetMapping
    public AjaxResult getEmissionMethods(EmissionMethod emissionMethod){
        return emissionMethodService.selectEmissionMethods(emissionMethod);
    }

}
