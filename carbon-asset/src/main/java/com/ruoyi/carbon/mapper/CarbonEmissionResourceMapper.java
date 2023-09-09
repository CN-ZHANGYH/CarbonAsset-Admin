package com.ruoyi.carbon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.carbon.domain.carbon.CarbonEmissionResource;
import com.ruoyi.carbon.domain.vo.EmissionResourceVo;
import com.ruoyi.carbon.domain.vo.RankingEmissionVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;


public interface CarbonEmissionResourceMapper extends BaseMapper<CarbonEmissionResource>
{
    /**
     * 查询企业排放资源
     *
     * @param emissionId 企业排放资源主键
     * @return 企业排放资源
     */
    public CarbonEmissionResource selectCarbonEmissionResourceByEmissionId(Long emissionId);

    /**
     * 查询企业排放资源列表
     *
     * @param carbonEmissionResource 企业排放资源
     * @return 企业排放资源集合
     */
    public List<CarbonEmissionResource> selectCarbonEmissionResourceList(CarbonEmissionResource carbonEmissionResource);

    /**
     * 新增企业排放资源
     *
     * @param carbonEmissionResource 企业排放资源
     * @return 结果
     */
    public int insertCarbonEmissionResource(CarbonEmissionResource carbonEmissionResource);

    /**
     * 修改企业排放资源
     *
     * @param carbonEmissionResource 企业排放资源
     * @return 结果
     */
    public int updateCarbonEmissionResource(CarbonEmissionResource carbonEmissionResource);

    /**
     * 删除企业排放资源
     *
     * @param emissionId 企业排放资源主键
     * @return 结果
     */
    public int deleteCarbonEmissionResourceByEmissionId(Long emissionId);

    /**
     * 批量删除企业排放资源
     *
     * @param emissionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarbonEmissionResourceByEmissionIds(Long[] emissionIds);

    @Select("SELECT * FROM carbon.carbon_emission_resource where enterprise_address = #{enterpriseAddress}")
    public ArrayList<CarbonEmissionResource> selectEmissionResourceByAddress(String enterpriseAddress);


    @Select("SELECT\n" +
            "    CASE WHEN WEEKDAY(date_table.date) = 0 THEN '周一'\n" +
            "         WHEN WEEKDAY(date_table.date) = 1 THEN '周二'\n" +
            "         WHEN WEEKDAY(date_table.date) = 2 THEN '周三'\n" +
            "         WHEN WEEKDAY(date_table.date) = 3 THEN '周四'\n" +
            "         WHEN WEEKDAY(date_table.date) = 4 THEN '周五'\n" +
            "         WHEN WEEKDAY(date_table.date) = 5 THEN '周六'\n" +
            "         WHEN WEEKDAY(date_table.date) = 6 THEN '周日'\n" +
            "        END AS week,\n" +
            "    IFNULL(SUM(carbon.carbon_emission_resource.emissions), 0) AS value\n" +
            "FROM\n" +
            "    (SELECT\n" +
            "             CURDATE() - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date\n" +
            "     FROM\n" +
            "         (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a\n" +
            "             CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b\n" +
            "             CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c) AS date_table\n" +
            "        LEFT JOIN carbon.carbon_emission_resource ON DATE(carbon.carbon_emission_resource.emission_time) = DATE(date_table.date)\n" +
            "WHERE\n" +
            "        date_table.date >= CURDATE() - INTERVAL 6 DAY\n" +
            "GROUP BY date_table.date\n" +
            "ORDER BY date_table.date ASC")
    public List<EmissionResourceVo> selectEmissionResourceOfWeek();


    public List<CarbonEmissionResource> selectEmissionResourceByEnterpriseId(@Param("enterpriseId") Integer enterpriseId);


    public List<CarbonEmissionResource> selectEmissionResourceList();


    /**
     * 查询企业碳排放的排行
     * @return 返回结果
     */

    @Select("SELECT\n" +
            "    (@row_number:=@row_number + 1) as enterprise_id,\n" +
            "    sub_query.enterprise_name,\n" +
            "    sub_query.enterprise_address,\n" +
            "    sub_query.enterprise_carbon_credits,\n" +
            "    sub_query.enterprise_verified,\n" +
            "    sub_query.enterprise_over_emission,\n" +
            "    sub_query.enterprise_total_emission,\n" +
            "    sub_query.avatar,\n" +
            "    sub_query.total_emissions\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            ce.enterprise_id,\n" +
            "            ce.enterprise_name,\n" +
            "            ce.enterprise_address,\n" +
            "            ce.enterprise_carbon_credits,\n" +
            "            ce.enterprise_verified,\n" +
            "            ce.enterprise_over_emission,\n" +
            "            ce.enterprise_total_emission,\n" +
            "            user.avatar,\n" +
            "            cer.total_emissions\n" +
            "        FROM carbon_enterprise ce\n" +
            "                 JOIN\n" +
            "             (\n" +
            "                 SELECT\n" +
            "                     enterprise_id,\n" +
            "                     SUM(emissions) AS total_emissions\n" +
            "                 FROM carbon_emission_resource\n" +
            "                 GROUP BY enterprise_id\n" +
            "             ) cer ON ce.enterprise_id = cer.enterprise_id\n" +
            "                 JOIN\n" +
            "             sys_user user ON ce.enterprise_name = user.nick_name\n" +
            "        ORDER BY\n" +
            "            cer.total_emissions DESC\n" +
            "        LIMIT\n" +
            "            #{page}, #{pageSize}\n" +
            "    ) as sub_query\n" +
            "        CROSS JOIN\n" +
            "    (SELECT @row_number := 0) r;\n")
    public List<RankingEmissionVo> selectRankingByEmissionResource(@Param("page") Integer page, @Param("pageSize") Integer pageSize);

    @Select("SELECT\n" +
            "    enterprise_id\n" +
            "FROM\n" +
            "    carbon_emission_resource\n" +
            "GROUP BY\n" +
            "    enterprise_id\n" +
            "ORDER BY\n" +
            "    SUM(emissions) DESC;")
    public List<Integer> selectResourceRanking();

    @Select("SELECT\n" +
            "    ROUND(COUNT(CASE WHEN is_approve = 1 THEN 1 ELSE NULL END) / COUNT(*) * 100, 1) AS approve_percentage\n" +
            "FROM\n" +
            "    carbon_emission_resource\n" +
            "WHERE\n" +
            "                enterprise_id = #{enterpriseId}\n" +
            "GROUP BY\n" +
            "    enterprise_id")
    OptionalDouble selectEnterpriseIsApplyEmissionResourceProgress(@Param("enterpriseId") Integer enterpriseId);

    @Select("SELECT\n" +
            "    ROUND(COUNT(CASE WHEN emission_time IS NOT NULL THEN 1 ELSE NULL END) / COUNT(*) * 100, 1) AS non_zero_emissions_percentage\n" +
            "FROM\n" +
            "    carbon_emission_resource\n" +
            "WHERE\n" +
            "                enterprise_id = #{enterpriseId}\n" +
            "GROUP BY\n" +
            "    enterprise_id;")
    OptionalDouble selectOverEmissionResourceProgress(@Param("enterpriseId") Integer enterpriseId);

    @Select("SELECT\n" +
            "    IFNULL(SUM(emissions), 0) AS total_emissions\n" +
            "FROM (\n" +
            "         SELECT 2023 AS year, 1 AS month\n" +
            "         UNION SELECT 2023, 2\n" +
            "         UNION SELECT 2023, 3\n" +
            "         UNION SELECT 2023, 4\n" +
            "         UNION SELECT 2023, 5\n" +
            "         UNION SELECT 2023, 6\n" +
            "         UNION SELECT 2023, 7\n" +
            "         UNION SELECT 2023, 8\n" +
            "         UNION SELECT 2023, 9\n" +
            "         UNION SELECT 2023, 10\n" +
            "         UNION SELECT 2023, 11\n" +
            "         UNION SELECT 2023, 12\n" +
            "     ) AS months\n" +
            "         LEFT JOIN carbon_emission_resource ON YEAR(emission_time) = months.year AND MONTH(emission_time) = months.month\n" +
            "WHERE emission_time IS NULL OR (emission_time IS NOT NULL AND MONTH(emission_time) = months.month)\n" +
            "GROUP BY months.year, months.month\n" +
            "ORDER BY months.year, months.month;")
    List<Integer> selectEmissionResourceMonthOfYear();


    List<CarbonEmissionResource> searchEnterpriseResourceEmissionRecord(@Param("enterpriseAddress") String enterpriseAddress, @Param("method") String method);
}
