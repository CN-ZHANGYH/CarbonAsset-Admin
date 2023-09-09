package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.carbon.domain.vo.EmissionVo;
import com.ruoyi.carbon.domain.vo.RankingEmissionVo;
import com.ruoyi.carbon.domain.vo.ResourceVo;
import com.ruoyi.carbon.domain.vo.VerifyVo;
import com.ruoyi.carbon.service.resources.ICarbonEmissionResourceService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("企业碳排放管理")
@RestController
@RequestMapping("/emissions")
public class IsResourceController extends BaseController {


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

    @ApiOperation("查询排放排行")
    @GetMapping("/rankingByEmission")
    public AjaxResult getRankingByEmissionResource(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
        List<RankingEmissionVo> rankingEmissionVos = emissionResourceService.selectRankingByEmissionResource(page,pageSize);
        AjaxResult ajax = AjaxResult.success();
        if (rankingEmissionVos == null)
        {
            return ajax.put("msg","当前还没企业排放");
        }
        ajax.put("data", rankingEmissionVos);
        ajax.put("total",rankingEmissionVos.size());
        return ajax;
    }

    @ApiOperation("查询企业当前碳排放的排名")
    @GetMapping("/getResourceRanking")
    public AjaxResult getResourceRanking(@RequestParam("enterprise") String enterprise){
        return emissionResourceService.selectResourceRanking(enterprise);
    }



}
