package com.ruoyi.web.controller.carbon.back;

import com.ruoyi.carbon.domain.carbon.CarbonRegulator;
import com.ruoyi.carbon.service.regulator.ICarbonRegulatorService;
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
 * 监管机构信息Controller
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@RestController
@RequestMapping("/carbon/regulator")
public class RegulatorController extends BaseController
{
    @Autowired
    private ICarbonRegulatorService carbonRegulatorService;

    /**
     * 查询监管机构信息列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:regulator:list')")
    @GetMapping("/list")
    public TableDataInfo list(CarbonRegulator carbonRegulator)
    {
        startPage();
        List<CarbonRegulator> list = carbonRegulatorService.selectCarbonRegulatorList(carbonRegulator);
        return getDataTable(list);
    }

    /**
     * 导出监管机构信息列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:regulator:export')")
    @Log(title = "监管机构信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarbonRegulator carbonRegulator)
    {
        List<CarbonRegulator> list = carbonRegulatorService.selectCarbonRegulatorList(carbonRegulator);
        ExcelUtil<CarbonRegulator> util = new ExcelUtil<CarbonRegulator>(CarbonRegulator.class);
        util.exportExcel(response, list, "监管机构信息数据");
    }

    /**
     * 获取监管机构信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:regulator:query')")
    @GetMapping(value = "/{regulatorId}")
    public AjaxResult getInfo(@PathVariable("regulatorId") Long regulatorId)
    {
        return success(carbonRegulatorService.selectCarbonRegulatorByRegulatorId(regulatorId));
    }

    /**
     * 新增监管机构信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:regulator:add')")
    @Log(title = "监管机构信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarbonRegulator carbonRegulator) throws Exception {
        return toAjax(carbonRegulatorService.insertCarbonRegulator(carbonRegulator));
    }

    /**
     * 修改监管机构信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:regulator:edit')")
    @Log(title = "监管机构信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarbonRegulator carbonRegulator)
    {
        return toAjax(carbonRegulatorService.updateCarbonRegulator(carbonRegulator));
    }

    /**
     * 删除监管机构信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:regulator:remove')")
    @Log(title = "监管机构信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{regulatorIds}")
    public AjaxResult remove(@PathVariable Long[] regulatorIds)
    {
        return toAjax(carbonRegulatorService.deleteCarbonRegulatorByRegulatorIds(regulatorIds));
    }
}
