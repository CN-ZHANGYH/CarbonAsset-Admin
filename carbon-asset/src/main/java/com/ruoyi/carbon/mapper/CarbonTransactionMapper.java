package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonTransaction;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * 交易碳额度记录Mapper接口
 * 
 * @author 张宇豪
 * @date 2023-07-08
 */
public interface CarbonTransactionMapper 
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
     * 删除交易碳额度记录
     * 
     * @param transactionId 交易碳额度记录主键
     * @return 结果
     */
    public int deleteCarbonTransactionByTransactionId(Long transactionId);

    /**
     * 批量删除交易碳额度记录
     * 
     * @param transactionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarbonTransactionByTransactionIds(Long[] transactionIds);

    public ArrayList<CarbonTransaction> selectTransactionListByAddress(@Param("address") String address);
}
