package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.domain.carbon.CarbonEnterpriseAsset;
import com.ruoyi.carbon.domain.vo.EnterpriseVo;
import com.ruoyi.common.core.domain.AjaxResult;
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




}
