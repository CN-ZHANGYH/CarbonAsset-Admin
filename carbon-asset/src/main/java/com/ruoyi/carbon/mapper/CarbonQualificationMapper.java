package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonQualification;

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

}
