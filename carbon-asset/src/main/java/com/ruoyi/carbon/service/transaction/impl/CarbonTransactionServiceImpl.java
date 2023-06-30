package com.ruoyi.carbon.service.transaction.impl;

import com.ruoyi.carbon.domain.carbon.CarbonTransaction;
import com.ruoyi.carbon.mapper.CarbonTransactionMapper;
import com.ruoyi.carbon.service.transaction.ICarbonTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 交易碳额度记录Service业务层处理
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@Service
public class CarbonTransactionServiceImpl implements ICarbonTransactionService
{
    @Autowired
    private CarbonTransactionMapper carbonTransactionMapper;

    /**
     * 查询交易碳额度记录
     *
     * @param transactionId 交易碳额度记录主键
     * @return 交易碳额度记录
     */
    @Override
    public CarbonTransaction selectCarbonTransactionByTransactionId(Long transactionId)
    {
        return carbonTransactionMapper.selectCarbonTransactionByTransactionId(transactionId);
    }

    /**
     * 查询交易碳额度记录列表
     *
     * @param carbonTransaction 交易碳额度记录
     * @return 交易碳额度记录
     */
    @Override
    public List<CarbonTransaction> selectCarbonTransactionList(CarbonTransaction carbonTransaction)
    {
        return carbonTransactionMapper.selectCarbonTransactionList(carbonTransaction);
    }

    /**
     * 新增交易碳额度记录
     *
     * @param carbonTransaction 交易碳额度记录
     * @return 结果
     */
    @Override
    public int insertCarbonTransaction(CarbonTransaction carbonTransaction)
    {
        return carbonTransactionMapper.insertCarbonTransaction(carbonTransaction);
    }

    /**
     * 修改交易碳额度记录
     *
     * @param carbonTransaction 交易碳额度记录
     * @return 结果
     */
    @Override
    public int updateCarbonTransaction(CarbonTransaction carbonTransaction)
    {
        return carbonTransactionMapper.updateCarbonTransaction(carbonTransaction);
    }

    /**
     * 批量删除交易碳额度记录
     *
     * @param transactionIds 需要删除的交易碳额度记录主键
     * @return 结果
     */
    @Override
    public int deleteCarbonTransactionByTransactionIds(Long[] transactionIds)
    {
        return carbonTransactionMapper.deleteCarbonTransactionByTransactionIds(transactionIds);
    }

    /**
     * 删除交易碳额度记录信息
     *
     * @param transactionId 交易碳额度记录主键
     * @return 结果
     */
    @Override
    public int deleteCarbonTransactionByTransactionId(Long transactionId)
    {
        return carbonTransactionMapper.deleteCarbonTransactionByTransactionId(transactionId);
    }

    @Override
    public ArrayList<CarbonTransaction> selectTransactionListByAddress(String enterpriseAddress) {

        return carbonTransactionMapper.selectTransactionListByAddress(enterpriseAddress);
    }
}
