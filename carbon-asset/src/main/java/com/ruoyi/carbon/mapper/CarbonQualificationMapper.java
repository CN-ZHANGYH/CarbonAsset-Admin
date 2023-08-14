package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonQualification;
import com.ruoyi.carbon.domain.vo.QualificationVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 企业资质信息Mapper接口
 * 
 * @author 张宇豪
 * @date 2023-07-08
 */
public interface CarbonQualificationMapper 
{
    /**
     * 查询企业资质信息
     * 
     * @param qualificationId 企业资质信息主键
     * @return 企业资质信息
     */
    public CarbonQualification selectCarbonQualificationByQualificationId(Long qualificationId);

    /**
     * 查询企业资质信息列表
     * 
     * @param carbonQualification 企业资质信息
     * @return 企业资质信息集合
     */
    public List<CarbonQualification> selectCarbonQualificationList(CarbonQualification carbonQualification);

    /**
     * 新增企业资质信息
     * 
     * @param carbonQualification 企业资质信息
     * @return 结果
     */
    public int insertCarbonQualification(CarbonQualification carbonQualification);

    /**
     * 修改企业资质信息
     * 
     * @param carbonQualification 企业资质信息
     * @return 结果
     */
    public int updateCarbonQualification(CarbonQualification carbonQualification);

    /**
     * 删除企业资质信息
     * 
     * @param qualificationId 企业资质信息主键
     * @return 结果
     */
    public int deleteCarbonQualificationByQualificationId(Long qualificationId);

    /**
     * 批量删除企业资质信息
     * 
     * @param qualificationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarbonQualificationByQualificationIds(Long[] qualificationIds);

    public CarbonQualification selectCarbonQualificationByName(String name);

    @Select("SELECT\n" +
            "    CASE WHEN WEEKDAY(date_table.date) = 0 THEN '周一'\n" +
            "         WHEN WEEKDAY(date_table.date) = 1 THEN '周二'\n" +
            "         WHEN WEEKDAY(date_table.date) = 2 THEN '周三'\n" +
            "         WHEN WEEKDAY(date_table.date) = 3 THEN '周四'\n" +
            "         WHEN WEEKDAY(date_table.date) = 4 THEN '周五'\n" +
            "         WHEN WEEKDAY(date_table.date) = 5 THEN '周六'\n" +
            "         WHEN WEEKDAY(date_table.date) = 6 THEN '周日'\n" +
            "        END AS week,\n" +
            "    IFNULL(COUNT(carbon_qualification.qualification_name), 0) AS value\n" +
            "FROM\n" +
            "    (SELECT\n" +
            "             CURDATE() - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date\n" +
            "     FROM\n" +
            "         (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a\n" +
            "             CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b\n" +
            "             CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c) AS date_table\n" +
            "        LEFT JOIN carbon_qualification ON DATE(carbon_qualification.qualification_upload_time) = DATE(date_table.date)\n" +
            "WHERE\n" +
            "        date_table.date >= CURDATE() - INTERVAL 6 DAY\n" +
            "GROUP BY date_table.date\n" +
            "ORDER BY date_table.date ASC;\n")
    public List<QualificationVo> selectQualificationByVerifyListOfWeek();

    @Select("SELECT\n" +
            "    IFNULL(COUNT(cq.qualification_id), 0) AS total_qualified_companies\n" +
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
            "         LEFT JOIN carbon_qualification cq ON MONTH(cq.qualification_audit_time) = months.month\n" +
            "    AND (cq.qualification_audit_time IS NULL OR (cq.qualification_audit_time IS NOT NULL))\n" +
            "GROUP BY months.month\n" +
            "ORDER BY months.month;\n")
    public List<Integer> selectEnterpriseVerifyMonthOfYear();

}
