package com.ruoyi.web.controller.carbon.back;

import com.ruoyi.carbon.domain.carbon.CarbonEnterpriseAsset;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseAssetService;
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

/**
 * 企业出售资产Controller
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@RestController
@RequestMapping("/carbon/asset")
public class EnterpriseAssetController extends BaseController
{
    @Autowired
    private ICarbonEnterpriseAssetService carbonEnterpriseAssetService;

    /**
     * 查询企业出售资产列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CarbonEnterpriseAsset carbonEnterpriseAsset)
    {
        startPage();
        List<CarbonEnterpriseAsset> list = carbonEnterpriseAssetService.selectCarbonEnterpriseAssetList(carbonEnterpriseAsset);
        return getDataTable(list);
    }

    /**
     * 导出企业出售资产列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:asset:export')")
    @Log(title = "企业出售资产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarbonEnterpriseAsset carbonEnterpriseAsset)
    {
        List<CarbonEnterpriseAsset> list = carbonEnterpriseAssetService.selectCarbonEnterpriseAssetList(carbonEnterpriseAsset);
        ExcelUtil<CarbonEnterpriseAsset> util = new ExcelUtil<CarbonEnterpriseAsset>(CarbonEnterpriseAsset.class);
        util.exportExcel(response, list, "企业出售资产数据");
    }

    /**
     * 获取企业出售资产详细信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:asset:query')")
    @GetMapping(value = "/{assetId}")
    public AjaxResult getInfo(@PathVariable("assetId") Long assetId)
    {
        return success(carbonEnterpriseAssetService.selectCarbonEnterpriseAssetByAssetId(assetId));
    }

    /**
     * 新增企业出售资产
     */
    @PreAuthorize("@ss.hasPermi('carbon:asset:add')")
    @Log(title = "企业出售资产", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarbonEnterpriseAsset carbonEnterpriseAsset)
    {
        return toAjax(carbonEnterpriseAssetService.insertCarbonEnterpriseAsset(carbonEnterpriseAsset));
    }

    /**
     * 修改企业出售资产
     */
    @PreAuthorize("@ss.hasPermi('carbon:asset:edit')")
    @Log(title = "企业出售资产", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarbonEnterpriseAsset carbonEnterpriseAsset)
    {
        return toAjax(carbonEnterpriseAssetService.updateCarbonEnterpriseAsset(carbonEnterpriseAsset));
    }

    /**
     * 删除企业出售资产
     */
    @PreAuthorize("@ss.hasPermi('carbon:asset:remove')")
    @Log(title = "企业出售资产", businessType = BusinessType.DELETE)
	@DeleteMapping("/{assetIds}")
    public AjaxResult remove(@PathVariable Long[] assetIds)
    {
        return toAjax(carbonEnterpriseAssetService.deleteCarbonEnterpriseAssetByAssetIds(assetIds));
    }
}
