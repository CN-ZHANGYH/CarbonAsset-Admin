package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.domain.vo.EnterpriseVo;
import com.ruoyi.carbon.domain.vo.RankingCreditVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * 企业信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-07-08
 */
public interface CarbonEnterpriseMapper
{
    /**
     * 查询企业信息
     *
     * @param enterpriseId 企业信息主键
     * @return 企业信息
     */
    public CarbonEnterprise selectCarbonEnterpriseByEnterpriseId(Long enterpriseId);

    /**
     * 查询企业信息列表
     *
     * @param carbonEnterprise 企业信息
     * @return 企业信息集合
     */
    public List<CarbonEnterprise> selectCarbonEnterpriseList(CarbonEnterprise carbonEnterprise);

    /**
     * 新增企业信息
     *
     * @param carbonEnterprise 企业信息
     * @return 结果
     */
    public int insertCarbonEnterprise(CarbonEnterprise carbonEnterprise);

    /**
     * 修改企业信息
     *
     * @param carbonEnterprise 企业信息
     * @return 结果
     */
    public int updateCarbonEnterprise(CarbonEnterprise carbonEnterprise);

    /**
     * 删除企业信息
     *
     * @param enterpriseId 企业信息主键
     * @return 结果
     */
    public int deleteCarbonEnterpriseByEnterpriseId(Long enterpriseId);

    /**
     * 批量删除企业信息
     *
     * @param enterpriseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarbonEnterpriseByEnterpriseIds(Long[] enterpriseIds);

    public CarbonEnterprise selectCarbonEnterpriseByAddress(String address);

    public CarbonEnterprise selectCarbonEnterpriseByName(String enterpriseName);

    public List<CarbonEnterprise> selectEnterpriseListByAddress(HashMap<String,Object> map);

    public EnterpriseVo selectUserWithEnterpriseEnterpriseName(String enterprise);


    @Select("SELECT\n" +
            "    (@row_number:=@row_number + 1) as enterprise_id,\n" +
            "    sub_query.enterprise_name,\n" +
            "    sub_query.enterprise_address,\n" +
            "    sub_query.enterprise_verified,\n" +
            "    sub_query.enterprise_carbon_credits,\n" +
            "    sub_query.avatar\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            e.enterprise_name,\n" +
            "            e.enterprise_address,\n" +
            "            e.enterprise_verified,\n" +
            "            e.enterprise_carbon_credits,\n" +
            "            su.avatar\n" +
            "        FROM\n" +
            "            carbon_enterprise e\n" +
            "                JOIN\n" +
            "            sys_user su ON e.enterprise_name = su.nick_name\n" +
            "        ORDER BY\n" +
            "            e.enterprise_carbon_credits DESC\n" +
            "        LIMIT\n" +
            "            #{page}, #{pageSize}\n" +
            "    ) as sub_query\n" +
            "        CROSS JOIN\n" +
            "    (SELECT @row_number := 0) r;\n")
    public List<RankingCreditVo> selectRankingByCredit(@Param("page") Integer page, @Param("pageSize") Integer pageSize);


    @Select("select e.enterprise_name FROM carbon_enterprise e JOIN sys_user su ON e.enterprise_name = su.nick_name order by e.enterprise_carbon_credits desc")
    public List<String> selectEnterpriseRanking();
}
