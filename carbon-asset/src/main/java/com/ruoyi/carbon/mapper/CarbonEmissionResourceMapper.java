package com.ruoyi.carbon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.carbon.domain.carbon.CarbonEmissionResource;
import com.ruoyi.carbon.domain.vo.EmissionResourceVo;
import com.ruoyi.carbon.domain.vo.RankingEmissionVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;


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
            "ORDER BY date_table.date ASC;")
    public List<EmissionResourceVo> selectEmissionResourceOfWeek();


    public List<CarbonEmissionResource> selectEmissionResourceByEnterpriseId(@Param("enterpriseId") Integer enterpriseId);


    public List<CarbonEmissionResource> selectEmissionResourceList();


    /**
     * 查询企业碳排放的排行
     * @return 返回结果
     */

    @Select("SELECT ce.enterprise_address,\n" +
            "       ce.enterprise_name,\n" +
            "       ce.enterprise_carbon_credits,\n" +
            "       ce.enterprise_verified,\n" +
            "       ce.enterprise_over_emission,\n" +
            "       ce.enterprise_total_emission,\n" +
            "       user.avatar,\n" +
            "       cer.total_emissions\n" +
            "FROM carbon_enterprise ce JOIN\n" +
            "     (SELECT enterprise_id, SUM(emissions) AS total_emissions\n" +
            "      FROM carbon_emission_resource\n" +
            "      GROUP BY enterprise_id) cer ON ce.enterprise_id = cer.enterprise_id\n" +
            "    JOIN sys_user user ON ce.enterprise_name = user.nick_name limit #{page},#{pageSize}")
    public List<RankingEmissionVo> selectRankingByEmissionResource(@Param("page") Integer page, @Param("pageSize") Integer pageSize);

}