package com.ruoyi.web.controller.carbon.back;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.souvenir.domain.CarbonCardCategory;
import com.ruoyi.souvenir.service.card.ICarbonCardCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 纪念卡分类数据Controller
 *
 * @author 张宇豪
 * @date 2023-07-28
 */
@RestController
@RequestMapping("/souvenir/category")
public class CardCategoryController extends BaseController
{
    @Autowired
    private ICarbonCardCategoryService carbonCardCategoryService;

    /**
     * 查询纪念卡分类数据列表
     */
    @PreAuthorize("@ss.hasPermi('souvenir:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(CarbonCardCategory carbonCardCategory)
    {
        startPage();
        List<CarbonCardCategory> list = carbonCardCategoryService.selectCarbonCardCategoryList(carbonCardCategory);
        return getDataTable(list);
    }

    /**
     * 导出纪念卡分类数据列表
     */
    @PreAuthorize("@ss.hasPermi('souvenir:category:export')")
    @Log(title = "纪念卡分类数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarbonCardCategory carbonCardCategory)
    {
        List<CarbonCardCategory> list = carbonCardCategoryService.selectCarbonCardCategoryList(carbonCardCategory);
        ExcelUtil<CarbonCardCategory> util = new ExcelUtil<CarbonCardCategory>(CarbonCardCategory.class);
        util.exportExcel(response, list, "纪念卡分类数据数据");
    }

    /**
     * 获取纪念卡分类数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('souvenir:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(carbonCardCategoryService.selectCarbonCardCategoryById(id));
    }

    /**
     * 新增纪念卡分类数据
     */
    @PreAuthorize("@ss.hasPermi('souvenir:category:add')")
    @Log(title = "纪念卡分类数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarbonCardCategory carbonCardCategory)
    {
        return toAjax(carbonCardCategoryService.insertCarbonCardCategory(carbonCardCategory));
    }

    /**
     * 修改纪念卡分类数据
     */
    @PreAuthorize("@ss.hasPermi('souvenir:category:edit')")
    @Log(title = "纪念卡分类数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarbonCardCategory carbonCardCategory)
    {
        return toAjax(carbonCardCategoryService.updateCarbonCardCategory(carbonCardCategory));
    }

    /**
     * 删除纪念卡分类数据
     */
    @PreAuthorize("@ss.hasPermi('souvenir:category:remove')")
    @Log(title = "纪念卡分类数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(carbonCardCategoryService.deleteCarbonCardCategoryByIds(ids));
    }

    @Log(title = "获取所有的纪念卡分类",businessType = BusinessType.OTHER)
    @GetMapping("/nameList")
    public AjaxResult getCategoryList(CarbonCardCategory carbonCardCategory) {
        return carbonCardCategoryService.selectCarbonCardCategoryNameList(carbonCardCategory);
    }
}
