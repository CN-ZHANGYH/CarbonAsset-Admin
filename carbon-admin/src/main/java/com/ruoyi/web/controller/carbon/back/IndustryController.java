package com.ruoyi.web.controller.carbon.back;

import com.ruoyi.carbon.domain.carbon.CarbonIndustry;
import com.ruoyi.carbon.service.industry.ICarbonIndustryService;
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
 * 行业分类单Controller
 *
 * @author 张宇豪
 * @date 2023-07-25
 */
@RestController
@RequestMapping("/carbon/industry")
public class IndustryController extends BaseController
{
    @Autowired
    private ICarbonIndustryService carbonIndustryService;

    /**
     * 查询行业分类单列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:industry:list')")
    @GetMapping("/list")
    public TableDataInfo list(CarbonIndustry carbonIndustry)
    {
        startPage();
        List<CarbonIndustry> list = carbonIndustryService.selectCarbonIndustryList(carbonIndustry);
        return getDataTable(list);
    }

    /**
     * 导出行业分类单列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:industry:export')")
    @Log(title = "行业分类单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarbonIndustry carbonIndustry)
    {
        List<CarbonIndustry> list = carbonIndustryService.selectCarbonIndustryList(carbonIndustry);
        ExcelUtil<CarbonIndustry> util = new ExcelUtil<CarbonIndustry>(CarbonIndustry.class);
        util.exportExcel(response, list, "行业分类单数据");
    }

    /**
     * 获取行业分类单详细信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:industry:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(carbonIndustryService.selectCarbonIndustryById(id));
    }

    /**
     * 新增行业分类单
     */
    @PreAuthorize("@ss.hasPermi('carbon:industry:add')")
    @Log(title = "行业分类单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarbonIndustry carbonIndustry)
    {
        return toAjax(carbonIndustryService.insertCarbonIndustry(carbonIndustry));
    }

    /**
     * 修改行业分类单
     */
    @PreAuthorize("@ss.hasPermi('carbon:industry:edit')")
    @Log(title = "行业分类单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarbonIndustry carbonIndustry)
    {
        return toAjax(carbonIndustryService.updateCarbonIndustry(carbonIndustry));
    }

    /**
     * 删除行业分类单
     */
    @PreAuthorize("@ss.hasPermi('carbon:industry:remove')")
    @Log(title = "行业分类单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(carbonIndustryService.deleteCarbonIndustryByIds(ids));
    }
}