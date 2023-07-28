package com.ruoyi.web.controller.carbon.back;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.souvenir.domain.CarbonCard;
import com.ruoyi.souvenir.service.ICarbonCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 纪念卡数据Controller
 *
 * @author 张宇豪
 * @date 2023-07-28
 */
@RestController
@RequestMapping("/souvenir/card")
public class
CardController extends BaseController
{
    @Autowired
    private ICarbonCardService carbonCardService;

    /**
     * 查询纪念卡数据列表
     */
    @PreAuthorize("@ss.hasPermi('souvenir:card:list')")
    @GetMapping("/list")
    public TableDataInfo list(CarbonCard carbonCard)
    {
        startPage();
        List<CarbonCard> list = carbonCardService.selectCarbonCardList(carbonCard);
        return getDataTable(list);
    }

    /**
     * 导出纪念卡数据列表
     */
    @PreAuthorize("@ss.hasPermi('souvenir:card:export')")
    @Log(title = "纪念卡数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarbonCard carbonCard)
    {
        List<CarbonCard> list = carbonCardService.selectCarbonCardList(carbonCard);
        ExcelUtil<CarbonCard> util = new ExcelUtil<CarbonCard>(CarbonCard.class);
        util.exportExcel(response, list, "纪念卡数据数据");
    }

    /**
     * 获取纪念卡数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('souvenir:card:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(carbonCardService.selectCarbonCardById(id));
    }

    /**
     * 新增纪念卡数据
     */
    @PreAuthorize("@ss.hasPermi('souvenir:card:add')")
    @Log(title = "纪念卡数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarbonCard carbonCard)
    {
        return toAjax(carbonCardService.insertCarbonCard(carbonCard));
    }

    /**
     * 修改纪念卡数据
     */
    @PreAuthorize("@ss.hasPermi('souvenir:card:edit')")
    @Log(title = "纪念卡数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarbonCard carbonCard)
    {
        return toAjax(carbonCardService.updateCarbonCard(carbonCard));
    }

    /**
     * 删除纪念卡数据
     */
    @PreAuthorize("@ss.hasPermi('souvenir:card:remove')")
    @Log(title = "纪念卡数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(carbonCardService.deleteCarbonCardByIds(ids));
    }


    /**
     * 上传纪念卡图标
     */
    @Log(title = "上传纪念卡图标",businessType = BusinessType.INSERT)
    @PostMapping("/upload")
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file) {
        return carbonCardService.uploadCardImg(file);
    }
}
