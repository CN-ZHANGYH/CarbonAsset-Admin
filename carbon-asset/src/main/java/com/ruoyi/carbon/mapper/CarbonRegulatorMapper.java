package com.ruoyi.carbon.mapper;

import com.ruoyi.carbon.domain.carbon.CarbonRegulator;

import java.util.List;

/**
 * 监管机构信息Mapper接口
 * 
 * @author 张宇豪
 * @date 2023-07-08
 */
public interface CarbonRegulatorMapper 
{
    /**
     * 查询监管机构信息
     * 
     * @param regulatorId 监管机构信息主键
     * @return 监管机构信息
     */
    public CarbonRegulator selectCarbonRegulatorByRegulatorId(Long regulatorId);

    /**
     * 查询监管机构信息列表
     * 
     * @param carbonRegulator 监管机构信息
     * @return 监管机构信息集合
     */
    public List<CarbonRegulator> selectCarbonRegulatorList(CarbonRegulator carbonRegulator);

    /**
     * 新增监管机构信息
     * 
     * @param carbonRegulator 监管机构信息
     * @return 结果
     */
    public int insertCarbonRegulator(CarbonRegulator carbonRegulator);

    /**
     * 修改监管机构信息
     * 
     * @param carbonRegulator 监管机构信息
     * @return 结果
     */
    public int updateCarbonRegulator(CarbonRegulator carbonRegulator);

    /**
     * 删除监管机构信息
     * 
     * @param regulatorId 监管机构信息主键
     * @return 结果
     */
    public int deleteCarbonRegulatorByRegulatorId(Long regulatorId);

    /**
     * 批量删除监管机构信息
     * 
     * @param regulatorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarbonRegulatorByRegulatorIds(Long[] regulatorIds);


    public CarbonRegulator selectRegulatorByName(String regulatorName);

    public CarbonRegulator selectRegulatorByAddress(String address);
}
