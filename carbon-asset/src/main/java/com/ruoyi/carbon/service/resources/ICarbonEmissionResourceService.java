package com.ruoyi.carbon.service.resources;

import com.ruoyi.carbon.domain.carbon.CarbonEmissionResource;
import com.ruoyi.carbon.domain.vo.EmissionVo;
import com.ruoyi.carbon.domain.vo.ResourceVo;
import com.ruoyi.carbon.domain.vo.VerifyVo;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业排放资源Service接口
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
public interface ICarbonEmissionResourceService
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
     * 批量删除企业排放资源
     *
     * @param emissionIds 需要删除的企业排放资源主键集合
     * @return 结果
     */
    public int deleteCarbonEmissionResourceByEmissionIds(Long[] emissionIds);

    /**
     * 删除企业排放资源信息
     *
     * @param emissionId 企业排放资源主键
     * @return 结果
     */
    public int deleteCarbonEmissionResourceByEmissionId(Long emissionId);


    /**
     * 企业申请碳排放
     *
     * @param resourceVo 申请实体类
     * @return 返回结果
     */
    public AjaxResult uploadEmissionResource(ResourceVo resourceVo);

    /**
     * 企业碳排放
     *
     * @param emissionVo 碳排放资源实体类
     * @return 返回结果
     */
    public AjaxResult enterpriseEmission(EmissionVo emissionVo);

    /**
     * 审核企业的碳排放
     *
     * @param verifyVo 审核参数的实体类
     * @return 返回结果
     */
    public AjaxResult verifyEnterpriseEmission(VerifyVo verifyVo);

    public ArrayList<CarbonEmissionResource> selectEmissionResourceByAddress(String enterpriseAddress);

    public List<CarbonEmissionResource> selectIsNotVerifyList(CarbonEmissionResource carbonEmissionResource);

}
