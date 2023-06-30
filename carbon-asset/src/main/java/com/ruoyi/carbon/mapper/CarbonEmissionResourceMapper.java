package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonEmissionResource;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业排放资源Mapper接口
 * 
 * @author 张宇豪
 * @date 2023-07-08
 */
public interface CarbonEmissionResourceMapper 
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

    public ArrayList<CarbonEmissionResource> selectEmissionResourceByAddress(String enterpriseAddress);
}
