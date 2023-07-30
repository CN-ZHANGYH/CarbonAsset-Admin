package com.ruoyi.carbon.service.enterprise;

import com.ruoyi.carbon.domain.carbon.CarbonQualification;
import com.ruoyi.carbon.domain.vo.QualificationVo;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 企业资质信息Service接口
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
public interface ICarbonQualificationService
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
    public int insertCarbonQualification(CarbonQualification carbonQualification) throws Exception;

    /**
     * 修改企业资质信息
     *
     * @param carbonQualification 企业资质信息
     * @return 结果
     */
    public int updateCarbonQualification(CarbonQualification carbonQualification);

    /**
     * 批量删除企业资质信息
     *
     * @param qualificationIds 需要删除的企业资质信息主键集合
     * @return 结果
     */
    public int deleteCarbonQualificationByQualificationIds(Long[] qualificationIds);

    /**
     * 删除企业资质信息信息
     *
     * @param qualificationId 企业资质信息主键
     * @return 结果
     */
    public int deleteCarbonQualificationByQualificationId(Long qualificationId);

    /**
     * 监管机构审核企业资质上链
     *
     * @param carbonQualification
     * @return 结果
     */
    public AjaxResult verifyQualificationByRegulator(CarbonQualification carbonQualification) throws Exception;


    /**
     * 企业上传资质文件
     *
     * @param file 图片文件
     * @return 返回结果
     */
    public AjaxResult uploadQualification(MultipartFile file);

    public List<QualificationVo> selectQualificationIsVerifyOfWeeek();


}
