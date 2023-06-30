package com.ruoyi.web.controller.carbon.back;

import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 企业信息Controller
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@Api("企业用户管理")
@RestController
@RequestMapping("/carbon/enterprise")
public class EnterpriseController extends BaseController
{
    @Autowired
    private ICarbonEnterpriseService carbonEnterpriseService;

    /**
     * 查询企业信息列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:enterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(CarbonEnterprise carbonEnterprise)
    {
        startPage();
        List<CarbonEnterprise> list = carbonEnterpriseService.selectCarbonEnterpriseList(carbonEnterprise);
        return getDataTable(list);
    }

    /**
     * 导出企业信息列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:enterprise:export')")
    @Log(title = "企业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarbonEnterprise carbonEnterprise)
    {
        List<CarbonEnterprise> list = carbonEnterpriseService.selectCarbonEnterpriseList(carbonEnterprise);
        ExcelUtil<CarbonEnterprise> util = new ExcelUtil<CarbonEnterprise>(CarbonEnterprise.class);
        util.exportExcel(response, list, "企业信息数据");
    }

    /**
     * 获取企业信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:enterprise:query')")
    @GetMapping(value = "/{enterpriseId}")
    public AjaxResult getInfo(@PathVariable("enterpriseId") Long enterpriseId)
    {
        return success(carbonEnterpriseService.selectCarbonEnterpriseByEnterpriseId(enterpriseId));
    }

    /**
     * 新增企业信息
     */

    @ApiOperation("新增企业信息")
    @ApiImplicitParam(name = "CarbonEnterprise", value = "企业实体类", required = true, dataType = "Class")
    @PreAuthorize("@ss.hasPermi('carbon:enterprise:add')")
    @Log(title = "企业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarbonEnterprise carbonEnterprise) throws Exception {
        return toAjax(carbonEnterpriseService.insertCarbonEnterprise(carbonEnterprise));
    }

    /**
     * 修改企业信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:enterprise:edit')")
    @Log(title = "企业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarbonEnterprise carbonEnterprise)
    {
        return toAjax(carbonEnterpriseService.updateCarbonEnterprise(carbonEnterprise));
    }

    /**
     * 删除企业信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:enterprise:remove')")
    @Log(title = "企业信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{enterpriseIds}")
    public AjaxResult remove(@PathVariable Long[] enterpriseIds)
    {
        return toAjax(carbonEnterpriseService.deleteCarbonEnterpriseByEnterpriseIds(enterpriseIds));
    }
}
