package com.ruoyi.web.controller.carbon.back;

import com.ruoyi.carbon.domain.carbon.CarbonEmissionResource;
import com.ruoyi.carbon.service.resources.ICarbonEmissionResourceService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 企业排放资源Controller
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@RestController
@RequestMapping("/carbon/resource")
public class EmissionResourceController extends BaseController
{
    @Autowired
    private ICarbonEmissionResourceService carbonEmissionResourceService;

    /**
     * 查询企业排放资源列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:resource:list')")
    @GetMapping("/list")
    public TableDataInfo list(CarbonEmissionResource carbonEmissionResource)
    {
        startPage();
        List<CarbonEmissionResource> list = carbonEmissionResourceService.selectCarbonEmissionResourceList(carbonEmissionResource);
        return getDataTable(list);
    }

    /**
     * 导出企业排放资源列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:resource:export')")
    @Log(title = "企业排放资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarbonEmissionResource carbonEmissionResource)
    {
        List<CarbonEmissionResource> list = carbonEmissionResourceService.selectCarbonEmissionResourceList(carbonEmissionResource);
        ExcelUtil<CarbonEmissionResource> util = new ExcelUtil<CarbonEmissionResource>(CarbonEmissionResource.class);
        util.exportExcel(response, list, "企业排放资源数据");
    }

    /**
     * 获取企业排放资源详细信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:resource:query')")
    @GetMapping(value = "/{emissionId}")
    public AjaxResult getInfo(@PathVariable("emissionId") Long emissionId)
    {
        return success(carbonEmissionResourceService.selectCarbonEmissionResourceByEmissionId(emissionId));
    }

    /**
     * 新增企业排放资源
     */
    @PreAuthorize("@ss.hasPermi('carbon:resource:add')")
    @Log(title = "企业排放资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarbonEmissionResource carbonEmissionResource)
    {
        return toAjax(carbonEmissionResourceService.insertCarbonEmissionResource(carbonEmissionResource));
    }

    /**
     * 修改企业排放资源
     */
    @PreAuthorize("@ss.hasPermi('carbon:resource:edit')")
    @Log(title = "企业排放资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarbonEmissionResource carbonEmissionResource)
    {
        return toAjax(carbonEmissionResourceService.updateCarbonEmissionResource(carbonEmissionResource));
    }

    /**
     * 删除企业排放资源
     */
    @PreAuthorize("@ss.hasPermi('carbon:resource:remove')")
    @Log(title = "企业排放资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{emissionIds}")
    public AjaxResult remove(@PathVariable Long[] emissionIds)
    {
        return toAjax(carbonEmissionResourceService.deleteCarbonEmissionResourceByEmissionIds(emissionIds));
    }

    @PreAuthorize("@ss.hasPermi('carbon:resource:list')")
    @Log(title = "查询资源申请未审批的企业",businessType = BusinessType.OTHER)
    @GetMapping("/isNotVerifyList")
    public TableDataInfo selectIsNotVerifyList(CarbonEmissionResource carbonEmissionResource){
        startPage();
        List<CarbonEmissionResource> list = carbonEmissionResourceService.selectCarbonEmissionResourceList(carbonEmissionResource);
        List<CarbonEmissionResource> resources = list.stream().filter(emissionResource -> emissionResource.getIsApprove() != 1).collect(Collectors.toList());
        System.out.println(list.size());
        return getDataTable(resources);
    }
}
