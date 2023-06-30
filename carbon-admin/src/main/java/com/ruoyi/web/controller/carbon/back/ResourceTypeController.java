package com.ruoyi.web.controller.carbon.back;

import com.ruoyi.carbon.domain.carbon.CarbonResourceType;
import com.ruoyi.carbon.service.type.ICarbonResourceTypeService;
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
 * 资源类型数据Controller
 * 
 * @author 张宇豪
 * @date 2023-07-25
 */
@RestController
@RequestMapping("/carbon/type")
public class ResourceTypeController extends BaseController
{
    @Autowired
    private ICarbonResourceTypeService carbonResourceTypeService;

    /**
     * 查询资源类型数据列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(CarbonResourceType carbonResourceType)
    {
        startPage();
        List<CarbonResourceType> list = carbonResourceTypeService.selectCarbonResourceTypeList(carbonResourceType);
        return getDataTable(list);
    }

    /**
     * 导出资源类型数据列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:type:export')")
    @Log(title = "资源类型数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarbonResourceType carbonResourceType)
    {
        List<CarbonResourceType> list = carbonResourceTypeService.selectCarbonResourceTypeList(carbonResourceType);
        ExcelUtil<CarbonResourceType> util = new ExcelUtil<CarbonResourceType>(CarbonResourceType.class);
        util.exportExcel(response, list, "资源类型数据数据");
    }

    /**
     * 获取资源类型数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(carbonResourceTypeService.selectCarbonResourceTypeById(id));
    }

    /**
     * 新增资源类型数据
     */
    @PreAuthorize("@ss.hasPermi('carbon:type:add')")
    @Log(title = "资源类型数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarbonResourceType carbonResourceType)
    {
        return toAjax(carbonResourceTypeService.insertCarbonResourceType(carbonResourceType));
    }

    /**
     * 修改资源类型数据
     */
    @PreAuthorize("@ss.hasPermi('carbon:type:edit')")
    @Log(title = "资源类型数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarbonResourceType carbonResourceType)
    {
        return toAjax(carbonResourceTypeService.updateCarbonResourceType(carbonResourceType));
    }

    /**
     * 删除资源类型数据
     */
    @PreAuthorize("@ss.hasPermi('carbon:type:remove')")
    @Log(title = "资源类型数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(carbonResourceTypeService.deleteCarbonResourceTypeByIds(ids));
    }
}
