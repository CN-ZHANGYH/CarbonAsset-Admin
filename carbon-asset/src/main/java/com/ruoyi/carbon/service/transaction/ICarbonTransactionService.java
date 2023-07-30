package com.ruoyi.carbon.service.transaction;

import com.ruoyi.carbon.domain.carbon.CarbonTransaction;
import com.ruoyi.carbon.domain.vo.TransactionVo;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 交易碳额度记录Service接口
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
public interface ICarbonTransactionService
{
    /**
     * 查询交易碳额度记录
     *
     * @param transactionId 交易碳额度记录主键
     * @return 交易碳额度记录
     */
    public CarbonTransaction selectCarbonTransactionByTransactionId(Long transactionId);

    /**
     * 查询交易碳额度记录列表
     *
     * @param carbonTransaction 交易碳额度记录
     * @return 交易碳额度记录集合
     */
    public List<CarbonTransaction> selectCarbonTransactionList(CarbonTransaction carbonTransaction);

    /**
     * 新增交易碳额度记录
     *
     * @param carbonTransaction 交易碳额度记录
     * @return 结果
     */
    public int insertCarbonTransaction(CarbonTransaction carbonTransaction);

    /**
     * 修改交易碳额度记录
     *
     * @param carbonTransaction 交易碳额度记录
     * @return 结果
     */
    public int updateCarbonTransaction(CarbonTransaction carbonTransaction);

    /**
     * 批量删除交易碳额度记录
     *
     * @param transactionIds 需要删除的交易碳额度记录主键集合
     * @return 结果
     */
    public int deleteCarbonTransactionByTransactionIds(Long[] transactionIds);

    /**
     * 删除交易碳额度记录信息
     *
     * @param transactionId 交易碳额度记录主键
     * @return 结果
     */
    public int deleteCarbonTransactionByTransactionId(Long transactionId);

    public ArrayList<CarbonTransaction> selectTransactionListByAddress(String enterpriseAddress);

    public List<TransactionVo> selectTransactionOfWeek();

    public AjaxResult selectTransactionNewTxList();

}
