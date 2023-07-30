package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonTransaction;
import com.ruoyi.carbon.domain.vo.TransactionVo;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("SELECT\n" +
            "    CASE WHEN WEEKDAY(date_table.date) = 0 THEN '周一'\n" +
            "         WHEN WEEKDAY(date_table.date) = 1 THEN '周二'\n" +
            "         WHEN WEEKDAY(date_table.date) = 2 THEN '周三'\n" +
            "         WHEN WEEKDAY(date_table.date) = 3 THEN '周四'\n" +
            "         WHEN WEEKDAY(date_table.date) = 4 THEN '周五'\n" +
            "         WHEN WEEKDAY(date_table.date) = 5 THEN '周六'\n" +
            "         WHEN WEEKDAY(date_table.date) = 6 THEN '周日'\n" +
            "        END AS week,\n" +
            "    IFNULL(SUM(transaction_quantity), 0) AS value\n" +
            "FROM\n" +
            "    (SELECT\n" +
            "             CURDATE() - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date\n" +
            "     FROM\n" +
            "         (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a\n" +
            "             CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b\n" +
            "             CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c) AS date_table\n" +
            "        LEFT JOIN carbon_transaction ON DATE(carbon_transaction.transaction_time) = DATE(date_table.date)\n" +
            "WHERE\n" +
            "        date_table.date >= CURDATE() - INTERVAL 6 DAY\n" +
            "GROUP BY date_table.date\n" +
            "ORDER BY date_table.date ASC;")
    public List<TransactionVo> selectTransactionOfWeek();

    @Select("SELECT *\n" +
            "FROM carbon_transaction\n" +
            "ORDER BY transaction_time DESC\n" +
            "LIMIT 10;")
    public List<CarbonTransaction> selectTransactionListOfNew();
}
