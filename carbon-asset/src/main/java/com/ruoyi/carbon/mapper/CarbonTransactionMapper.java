package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonTransaction;
import com.ruoyi.carbon.domain.vo.TransactionVo;
import com.ruoyi.carbon.domain.vo.TxDataVo;
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

    public List<CarbonTransaction> selectTransactionListOfNew();

    @Select("SELECT\n" +
            "    SUM(CASE WHEN WEEKDAY(transaction_time) = 0 THEN transaction_quantity ELSE 0 END) AS monday,\n" +
            "    SUM(CASE WHEN WEEKDAY(transaction_time) = 1 THEN transaction_quantity ELSE 0 END) AS tuesday,\n" +
            "    SUM(CASE WHEN WEEKDAY(transaction_time) = 2 THEN transaction_quantity ELSE 0 END) AS wednesday,\n" +
            "    SUM(CASE WHEN WEEKDAY(transaction_time) = 3 THEN transaction_quantity ELSE 0 END) AS thursday,\n" +
            "    SUM(CASE WHEN WEEKDAY(transaction_time) = 4 THEN transaction_quantity ELSE 0 END) AS friday,\n" +
            "    SUM(CASE WHEN WEEKDAY(transaction_time) = 5 THEN transaction_quantity ELSE 0 END) AS saturday,\n" +
            "    SUM(CASE WHEN WEEKDAY(transaction_time) = 6 THEN transaction_quantity ELSE 0 END) AS sunday\n" +
            "FROM\n" +
            "    carbon_transaction\n" +
            "WHERE\n" +
            "                buyer_id = #{buyerId}\n" +
            "GROUP BY\n" +
            "    buyer_id;")
    public List<TxDataVo> selectTransactionTxList(@Param("buyerId") Integer buyerId);

    @Select("SELECT\n" +
            "    IFNULL(SUM(ct.transaction_quantity), 0) AS total_transactions\n" +
            "FROM (\n" +
            "         SELECT 1 AS month\n" +
            "         UNION SELECT 2\n" +
            "         UNION SELECT 3\n" +
            "         UNION SELECT 4\n" +
            "         UNION SELECT 5\n" +
            "         UNION SELECT 6\n" +
            "         UNION SELECT 7\n" +
            "         UNION SELECT 8\n" +
            "         UNION SELECT 9\n" +
            "         UNION SELECT 10\n" +
            "         UNION SELECT 11\n" +
            "         UNION SELECT 12\n" +
            "     ) AS months\n" +
            "         LEFT JOIN carbon_transaction ct ON MONTH(ct.transaction_time) = months.month\n" +
            "    AND (ct.transaction_time IS NULL OR (ct.transaction_time IS NOT NULL))\n" +
            "GROUP BY months.month\n" +
            "ORDER BY months.month;\n")
    public List<Integer> selectTransactionMonthOfYear();

}
