package com.ruoyi.web.controller.carbon.back;

import com.ruoyi.carbon.domain.carbon.CarbonQualification;
import com.ruoyi.carbon.service.enterprise.ICarbonQualificationService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 企业资质信息Controller
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@Api("企业资质管理")
@RestController
@RequestMapping("/carbon/qualification")
public class QualificationController extends BaseController
{
    @Autowired
    private ICarbonQualificationService carbonQualificationService;

    /**
     * 查询企业资质信息列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:qualification:list')")
    @GetMapping("/list")
    public TableDataInfo list(CarbonQualification carbonQualification)
    {
        startPage();
        List<CarbonQualification> list = carbonQualificationService.selectCarbonQualificationList(carbonQualification);
        return getDataTable(list);
    }

    /**
     * 导出企业资质信息列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:qualification:export')")
    @Log(title = "企业资质信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarbonQualification carbonQualification)
    {
        List<CarbonQualification> list = carbonQualificationService.selectCarbonQualificationList(carbonQualification);
        ExcelUtil<CarbonQualification> util = new ExcelUtil<CarbonQualification>(CarbonQualification.class);
        util.exportExcel(response, list, "企业资质信息数据");
    }

    /**
     * 获取企业资质信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:qualification:query')")
    @GetMapping(value = "/{qualificationId}")
    public AjaxResult getInfo(@PathVariable("qualificationId") Long qualificationId)
    {
        return success(carbonQualificationService.selectCarbonQualificationByQualificationId(qualificationId));
    }

    /**
     * 新增企业资质信息
     */
    @ApiOperation("监管机构新增资质")
    @PreAuthorize("@ss.hasPermi('carbon:qualification:add')")
    @Log(title = "企业资质信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarbonQualification carbonQualification) throws Exception {
        return toAjax(carbonQualificationService.insertCarbonQualification(carbonQualification));
    }

    /**
     * 修改企业资质信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:qualification:edit')")
    @Log(title = "企业资质信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarbonQualification carbonQualification)
    {
        return toAjax(carbonQualificationService.updateCarbonQualification(carbonQualification));
    }

    /**
     * 删除企业资质信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:qualification:remove')")
    @Log(title = "企业资质信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/qualificationIds")
    public AjaxResult remove(@PathVariable Long[] qualificationIds)
    {
        return toAjax(carbonQualificationService.deleteCarbonQualificationByQualificationIds(qualificationIds));
    }

    @ApiOperation("企业审核资质")
    @Log(title = "审核企业的资质",businessType = BusinessType.UPDATE)
    @PostMapping("/verifyQualification")
    public AjaxResult verifyQualification(@RequestBody CarbonQualification carbonQualification) throws Exception {
        return carbonQualificationService.verifyQualificationByRegulator(carbonQualification);
    }

    @ApiOperation("企业上传资质")
    @Log(title = "企业上传资质",businessType = BusinessType.INSERT)
    @PostMapping("/upload")
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file) {
        return carbonQualificationService.uploadQualification(file);
    }


}
