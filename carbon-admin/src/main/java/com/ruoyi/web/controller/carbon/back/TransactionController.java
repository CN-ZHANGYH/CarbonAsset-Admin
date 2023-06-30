package com.ruoyi.web.controller.carbon.back;

import com.ruoyi.carbon.domain.carbon.CarbonTransaction;
import com.ruoyi.carbon.service.transaction.ICarbonTransactionService;
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
 * 交易碳额度记录Controller
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@RestController
@RequestMapping("/carbon/transaction")
public class TransactionController extends BaseController
{
    @Autowired
    private ICarbonTransactionService carbonTransactionService;

    /**
     * 查询交易碳额度记录列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:transaction:list')")
    @GetMapping("/list")
    public TableDataInfo list(CarbonTransaction carbonTransaction)
    {
        startPage();
        List<CarbonTransaction> list = carbonTransactionService.selectCarbonTransactionList(carbonTransaction);
        return getDataTable(list);
    }

    /**
     * 导出交易碳额度记录列表
     */
    @PreAuthorize("@ss.hasPermi('carbon:transaction:export')")
    @Log(title = "交易碳额度记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarbonTransaction carbonTransaction)
    {
        List<CarbonTransaction> list = carbonTransactionService.selectCarbonTransactionList(carbonTransaction);
        ExcelUtil<CarbonTransaction> util = new ExcelUtil<CarbonTransaction>(CarbonTransaction.class);
        util.exportExcel(response, list, "交易碳额度记录数据");
    }

    /**
     * 获取交易碳额度记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('carbon:transaction:query')")
    @GetMapping(value = "/{transactionId}")
    public AjaxResult getInfo(@PathVariable("transactionId") Long transactionId)
    {
        return success(carbonTransactionService.selectCarbonTransactionByTransactionId(transactionId));
    }

    /**
     * 新增交易碳额度记录
     */
    @PreAuthorize("@ss.hasPermi('carbon:transaction:add')")
    @Log(title = "交易碳额度记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarbonTransaction carbonTransaction)
    {
        return toAjax(carbonTransactionService.insertCarbonTransaction(carbonTransaction));
    }

    /**
     * 修改交易碳额度记录
     */
    @PreAuthorize("@ss.hasPermi('carbon:transaction:edit')")
    @Log(title = "交易碳额度记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarbonTransaction carbonTransaction)
    {
        return toAjax(carbonTransactionService.updateCarbonTransaction(carbonTransaction));
    }

    /**
     * 删除交易碳额度记录
     */
    @PreAuthorize("@ss.hasPermi('carbon:transaction:remove')")
    @Log(title = "交易碳额度记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{transactionIds}")
    public AjaxResult remove(@PathVariable Long[] transactionIds)
    {
        return toAjax(carbonTransactionService.deleteCarbonTransactionByTransactionIds(transactionIds));
    }
}
