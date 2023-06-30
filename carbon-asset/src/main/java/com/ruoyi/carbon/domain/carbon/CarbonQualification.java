package com.ruoyi.carbon.domain.carbon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

/**
 * 企业资质信息对象 carbon_qualification
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonQualification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资质ID */
    private Integer qualificationId;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String qualificationName;

    /** 社会信用代码 */
    @Excel(name = "社会信用代码")
    private String qualificationContent;

    /** 法定代表人 */
    @Excel(name = "法定代表人")
    private String qualificationLeader;

    /** 注册地址 */
    @Excel(name = "注册地址")
    private String qualificationAddress;

    /** 所属的行业 */
    @Excel(name = "所属的行业")
    private String qualificationIndustry;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String qualificationUserName;

    /** 联系人邮箱 */
    @Excel(name = "联系人邮箱")
    private String qualificationUserEmail;

    /** 资质的图片认证URL*/
    @Excel(name = "资质的图片认证URL")
    private String qualificationUrl;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String qualificationUploadTime;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String qualificationAuditTime;

    /** 审核的监管机构地址 */
    @Excel(name = "审核的监管机构地址")
    private String qualificationVerifiedRegulator;

    /** 监管机构是否审核通过*/
    @Transient
    private Boolean isApprove;

    /** 碳排放额度 */
    @Excel(name = "碳排放额度")
    private Long qualificationEmissionLimit;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("qualificationId", getQualificationId())
            .append("qualificationName", getQualificationName())
            .append("qualificationContent", getQualificationContent())
            .append("qualificationLeader", getQualificationLeader())
            .append("qualificationAddress", getQualificationAddress())
            .append("qualificationIndustry", getQualificationIndustry())
            .append("qualificationUserName", getQualificationUserName())
            .append("qualificationUserEmail", getQualificationUserEmail())
            .append("qualificationUrl", getQualificationUrl())
            .append("qualificationUploadTime", getQualificationUploadTime())
            .append("qualificationAuditTime", getQualificationAuditTime())
            .append("qualificationVerifiedRegulator", getQualificationVerifiedRegulator())
            .append("qualificationEmissionLimit", getQualificationEmissionLimit())
            .toString();
    }
}
