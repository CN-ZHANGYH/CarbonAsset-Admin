package com.ruoyi.carbon.service.transaction.impl;

import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.domain.carbon.CarbonTransaction;
import com.ruoyi.carbon.domain.vo.TransactionVo;
import com.ruoyi.carbon.domain.vo.TxDataVo;
import com.ruoyi.carbon.mapper.CarbonTransactionMapper;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseAssetService;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseService;
import com.ruoyi.carbon.service.transaction.ICarbonTransactionService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private ICarbonEnterpriseService enterpriseService;

    @Autowired
    private ICarbonEnterpriseAssetService enterpriseAssetService;

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

    @Override
    public List<TransactionVo> selectTransactionOfWeek() {
        return carbonTransactionMapper.selectTransactionOfWeek();
    }

    @Override
    public AjaxResult selectTransactionNewTxList() {
        List<CarbonTransaction> carbonTransactions = carbonTransactionMapper.selectTransactionListOfNew();
        if (carbonTransactions.size() < 0)
        {
            return AjaxResult.error("当前还没有交易");
        }
        return AjaxResult.success(carbonTransactions);
    }

    @Override
    public AjaxResult selectTransactionTxAndSellerList(String enterprise) {

        CarbonEnterprise carbonEnterprise = enterpriseService.selectByEnterpriseName(enterprise);
        if (Objects.isNull(carbonEnterprise))
        {
            return AjaxResult.error("当前企业不存在");
        }
        List<TxDataVo> txDataVos = carbonTransactionMapper.selectTransactionTxList(carbonEnterprise.getEnterpriseId());
        List<TxDataVo> sellDataVos = enterpriseAssetService.selectEnterpriseAssetSellList(carbonEnterprise.getEnterpriseId());
        ArrayList<Integer> txData = new ArrayList<>();
        ArrayList<Integer> sellerData = new ArrayList<>();
        if (txDataVos.size() == 0 || sellDataVos.size() == 0)
        {
            return AjaxResult.error("查询失败");
        }
        getTxOrSellerDataList(txDataVos,txData);
        getTxOrSellerDataList(sellDataVos,sellerData);
        AjaxResult success = AjaxResult.success();
        success.put("tData",txData);
        success.put("sData",sellerData);
        return success;
    }

    private static void getTxOrSellerDataList(List<TxDataVo> txDataVos, ArrayList<Integer> txData) {
        txDataVos.stream().forEach(txDataVo -> {
            txData.add(txDataVo.getMonday());
            txData.add(txDataVo.getTuesday());
            txData.add(txDataVo.getWednesday());
            txData.add(txDataVo.getThursday());
            txData.add(txDataVo.getFriday());
            txData.add(txDataVo.getSaturday());
            txData.add(txDataVo.getSunday());
        });
    }

    @Override
    public List<Integer> selectTxMonthOfYear() {
        return carbonTransactionMapper.selectTransactionMonthOfYear();
    }

    @Override
    public AjaxResult selectTransactionNewTxListLimitFive() {
        List<CarbonTransaction> carbonTransactions = carbonTransactionMapper.selectTransactionListOfNewLimitFive();
        if (carbonTransactions.size() < 0)
        {
            return AjaxResult.error("当前还没有交易");
        }
        return AjaxResult.success(carbonTransactions);
    }

    @Override
    public List<CarbonTransaction> searchEnterpriseTxRecord(Long buyerId, Long quality) {
        if (buyerId == 0 || quality == 0){
            return null;
        }
        return carbonTransactionMapper.searchEnterpriseTxRecord(buyerId,quality);
    }
}
