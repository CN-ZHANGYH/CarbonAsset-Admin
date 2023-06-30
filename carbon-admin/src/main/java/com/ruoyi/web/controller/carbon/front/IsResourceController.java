package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.carbon.domain.vo.EmissionVo;
import com.ruoyi.carbon.domain.vo.ResourceVo;
import com.ruoyi.carbon.domain.vo.VerifyVo;
import com.ruoyi.carbon.service.resources.ICarbonEmissionResourceService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("企业碳排放管理")
@RestController
@RequestMapping("/emissions")
public class IsResourceController {


    @Autowired
    private ICarbonEmissionResourceService emissionResourceService;

    @ApiOperation("企业碳排放")
    @PostMapping("/executed")
    @Log(title = "企业碳排放",businessType = BusinessType.UPDATE)
    public AjaxResult enterpriseEmission(@RequestBody EmissionVo emissionVo) {
        return emissionResourceService.enterpriseEmission(emissionVo);
    }

    @ApiOperation("企业申请碳排放")
    @PostMapping("/upload")
    @Log(title = "企业申请碳排放",businessType = BusinessType.INSERT)
    public AjaxResult uploadEmissionResource(@RequestBody ResourceVo resourceVo){
        return emissionResourceService.uploadEmissionResource(resourceVo);
    }


    @ApiOperation("审核企业的碳排放")
    @PostMapping("/verify")
    @Log(title = "审核企业的碳排放",businessType = BusinessType.UPDATE)
    public AjaxResult verifyEnterpriseEmission(@RequestBody VerifyVo verifyVo){
        return emissionResourceService.verifyEnterpriseEmission(verifyVo);
    }
}
