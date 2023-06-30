package com.ruoyi.web.controller.carbon.back;

import com.ruoyi.carbon.domain.carbon.EmissionMethod;
import com.ruoyi.carbon.service.method.IEmissionMethodService;
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
 * 排放方式的Controller
 * 
 * @author 张宇豪
 * @date 2023-07-25
 */
@RestController
@RequestMapping("/carbon/method")
public class EmissionMethodController extends BaseController
{
    @Autowired
    private IEmissionMethodService emissionMethodService;

    /**
     * 查询排放方式的列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:method:list')")
    @GetMapping("/list")
    public TableDataInfo list(EmissionMethod emissionMethod)
    {
        startPage();
        List<EmissionMethod> list = emissionMethodService.selectEmissionMethodList(emissionMethod);
        return getDataTable(list);
    }

    /**
     * 导出排放方式的列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:method:export')")
    @Log(title = "排放方式的", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EmissionMethod emissionMethod)
    {
        List<EmissionMethod> list = emissionMethodService.selectEmissionMethodList(emissionMethod);
        ExcelUtil<EmissionMethod> util = new ExcelUtil<EmissionMethod>(EmissionMethod.class);
        util.exportExcel(response, list, "排放方式的数据");
    }

    /**
     * 获取排放方式的详细信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:method:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(emissionMethodService.selectEmissionMethodById(id));
    }

    /**
     * 新增排放方式的
     */
    @PreAuthorize("@ss.hasPermi('carbon:method:add')")
    @Log(title = "排放方式的", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EmissionMethod emissionMethod)
    {
        return toAjax(emissionMethodService.insertEmissionMethod(emissionMethod));
    }

    /**
     * 修改排放方式的
     */
    @PreAuthorize("@ss.hasPermi('carbon:method:edit')")
    @Log(title = "排放方式的", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EmissionMethod emissionMethod)
    {
        return toAjax(emissionMethodService.updateEmissionMethod(emissionMethod));
    }

    /**
     * 删除排放方式的
     */
    @PreAuthorize("@ss.hasPermi('carbon:method:remove')")
    @Log(title = "排放方式的", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(emissionMethodService.deleteEmissionMethodByIds(ids));
    }
}
